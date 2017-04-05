import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Dictionary {

	private Entry[] entries; // Array to store the entries
	private int words; // to store number of individual words
	// change this to location of .csv and text files
	private String path = "D:/Desktop/dictionary/res/";

	/*
	 * Constructor Takes integer wordCount which represents size of entries
	 * array. Initializes entries array to be of size wordCount and # of words
	 * to 0. Initializes each entry to null.
	 */
	public Dictionary(int wordCount) throws FileNotFoundException {
		entries = new Entry[wordCount];
		words = 0;
		for (int i = 0; i < entries.length; i++)
			entries[i] = null;
	}

	/*
	 * Overridden constructor Takes string s which is assumed to be "all"
	 * Initializes entries array at size 120,000 and # of words at 0.
	 * Initializes each entry to null
	 */
	public Dictionary(String s) {
		entries = new Entry[120000];
		words = 0;
		for (int i = 0; i < entries.length; i++)
			entries[i] = null;
	}

	/*
	 * Takes an integer wordCount which is the number of words to run each test
	 * on.
	 * 
	 * Runs the required tests which were specified to us in the 'exercises.txt'
	 * file. After each test is successfully run, runtime and success rate will
	 * be printed.
	 */
	public void runTests(int wordCount) throws FileNotFoundException {
		fromCSV("add", wordCount, "dictionary.csv");
		fromCSV("edit", wordCount, "definitions.csv");
		fromFile("search", wordCount, "words.txt");
		fromFile("delete", wordCount, "words.txt");
	}

	/*
	 * Prints the whole dictionary
	 */
	public void view() {
		System.out.print("\n# of words: " + words + "\n---------------------");
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] == null)
				System.out.print("\n" + i + ". " + "null");
			else
				System.out.print("\n" + i + ". " + entries[i].toString());
		}
	}

	/*
	 * Takes a string key and value which are word and definition
	 * 
	 * Converts key to a hash and checks if the dictionary at that location is
	 * not null and if the key of the entry at that location is the same as the
	 * given key. If so, concatenate the given value to the entries' value
	 * string. return true. Else, check if # of words is more than the length of
	 * entries array. If so, resize entries array. create a new entry at the
	 * calculated hash with the given key and value pair. Increment # of words
	 * by 1 and return true. if
	 */
	public boolean add(String key, String value) {
		int hash = convertHash(key);
		if (entries[hash] != null && entries[hash].getKey().equals(key)) {
			entries[hash].setValue(entries[hash].getValue() + "\n   -> " + value);
			return true;
		} else {
			if (words > entries.length)
				resizeEntries();
			entries[hash] = new Entry(key, "\n   -> " + value, hash);
			words++;
			return true;
		}
	}

	/*
	 * Takes a string key which is a word Takes a boolean view to tell it to
	 * print or not
	 * 
	 * Converts the key to a hash and checks if the dictionary at that location
	 * is null. If so, check if view is true. IF so, print an error message.
	 * Then return false. If entry was not null and the key at that entry was
	 * the same as the given key. If it is, make that entry null.
	 */
	public boolean delete(String key, boolean view) {
		int hash = convertHash(key);
		if (entries[hash] != null && entries[hash].getKey().equals(key)) {
			entries[hash] = null;
			words--;
			return true;
		}
		if (view)
			System.out.println("\nword not found");
		return false;

	}

	/*
	 * Takes a string key which is a word Takes a boolean view to tell it to
	 * print or not
	 * 
	 * Converts the key to a hash and checks if the dictionary at that location
	 * is null. If so, check if view is true. If so, print an error message.
	 * Then return false. If entry was not null and the key at that entry was
	 * the same as the given key, check if view is true or not. If true, print
	 * the key and value of that entry. Then return true.
	 */
	public boolean search(String key, boolean view) {
		int hash = convertHash(key);
		if (entries[hash] != null && entries[hash].getKey().equals(key)) {
			if (view)
				System.out.println("\n" + key + ": " + entries[hash].getValue());
			return true;
		}
		if (view)
			System.out.println("\nword not found");
		return false;
	}

	/*
	 * Takes a string key and value which are word and definition
	 * 
	 * Converts the key to a hash and checks if the dictionary at that location
	 * is null. If so, return false. If it is not null and it contains the key
	 * we are looking for, replace that entries value with the new value.
	 */
	public boolean edit(String key, String value) {
		int hash = convertHash(key);
		if (entries[hash] != null && entries[hash].getKey().equals(key)) {
			entries[hash].setValue("\n   -> " + value);
			return true;
		}
		return false;
	}

	/*
	 * Takes a string indicating the required function. Takes the number of
	 * words required by the function. Takes the name of the file to read from.
	 * 
	 * Searches or deletes words from .txt files to the dictionary. Counts the
	 * number of successful searches or deletions and prints it. Counts the time
	 * taken to complete the task and prints it. Does not print the searched
	 * word's definitions as this would add an exponential amounts of time to
	 * the runtime.
	 */
	public void fromFile(String function, int wordCount, String fName) throws FileNotFoundException {
		int success = 0;
		long runTime;
		if (function.equals("search")) {
			runTime = System.nanoTime();
			Scanner fileIn = new Scanner(new File(path + fName));
			if (wordCount == -1) {
				System.out.print("Searching all entries from '" + fName + "'     ... ");
				while (fileIn.hasNextLine()) {
					if (search(fileIn.nextLine(), false))
						success++;
				}
			} else {
				System.out.print("Searching " + wordCount + " entries from '" + fName + "'     ... ");
				for (int i = 0; i < wordCount; i++) {
					if (search(fileIn.nextLine(), false))
						success++;
				}
			}
			fileIn.close();
			runTime = System.nanoTime() - runTime;
			System.out.print("done");
			System.out.println("\n -> Successful searches: " + success + ", runtime: " + ((double)runTime/1000000) + "ms");
		} else if (function.equals("delete")) {
			runTime = System.nanoTime();
			Scanner fileIn = new Scanner(new File(path + fName));
			if (wordCount == -1) {
				System.out.print("Deleting all entries from '" + fName + "'      ... ");
				while (fileIn.hasNextLine()) {
					if (delete(fileIn.nextLine(), false))
						success++;
				}
			} else {
				System.out.print("Deleting " + wordCount + " entries from '" + fName + "'      ... ");
				for (int i = 0; i < wordCount; i++) {
					if (delete(fileIn.nextLine(), false))
						success++;
				}
			}
			fileIn.close();
			runTime = System.nanoTime() - runTime;
			System.out.print("done");
			System.out.println("\n -> Successful deletions: " + success + ", runtime: " + ((double)runTime/1000000) + "ms");
		} else {
			System.out.println("invalid fromFile operation");
		}
	}

	/*
	 * Takes a string indicating the required function. Takes the number of
	 * words required by the function. Takes the name of the file to read from.
	 * 
	 * Adds or edits words from .csv files to the dictionary. Counts the number
	 * of successful additions or edits and prints it. Counts the time taken to
	 * complete the task and prints it.
	 */
	public void fromCSV(String function, int wordCount, String fName) throws FileNotFoundException {
		int success = 0;
		long runTime;
		if (function.equals("add")) {
			runTime = System.nanoTime();
			Scanner csvIn = new Scanner(new File(path + fName));
			String[] kv;
			if (wordCount == -1) {
				System.out.print("\nAdding all entries from '" + fName + "'   ... ");
				while (csvIn.hasNextLine()) {
					kv = csvIn.nextLine().split("\",\"");
					if (add(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")))
						success++;
				}
			} else {
				System.out.print("\nAdding " + wordCount + " entries from '" + fName + "'   ... ");
				for (int i = 0; i < wordCount; i++) {
					kv = csvIn.nextLine().split("\",\"");
					if (add(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")))
						success++;
				}
			}
			csvIn.close();
			runTime = System.nanoTime() - runTime;
			System.out.print("done");
			System.out.println("\n -> Successful additions: " + success + ", runtime: " + ((double)runTime/1000000) + "ms");
		} else if (function.equals("edit")) {
			runTime = System.nanoTime();
			Scanner csvIn = new Scanner(new File(path + fName));
			String[] kv;
			if (wordCount == -1) {
				System.out.print("Editing all entries from '" + fName + "' ... ");
				while (csvIn.hasNextLine()) {
					kv = csvIn.nextLine().split("\",\"");
					if (edit(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")))
						success++;
				}
			} else {
				System.out.print("Editing " + wordCount + " entries from '" + fName + "' ... ");
				for (int i = 0; i < wordCount; i++) {
					kv = csvIn.nextLine().split("\",\"");
					if (edit(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")))
						success++;
				}
			}
			csvIn.close();
			runTime = System.nanoTime() - runTime;
			System.out.print("done");
			System.out.println("\n -> Successful edits: " + success + ", runtime: " + ((double)runTime/1000000) + "ms");
		} else {
			System.out.println("invalid fromCSV operation");
		}
	}

	/*
	 * resize entries array by 100
	 */
	public void resizeEntries() {
		entries = Arrays.copyOf(entries, entries.length + 100);
	}

	/*
	 * Takes a string value as a key.
	 * 
	 * Returns the hash of that string if collision occurs, checks if collision
	 * is between the same string. If so, return that hash. Else, add 1 to the
	 * hash.
	 * 
	 */
	public int convertHash(String s) {
		// generate a hash
		int hash = 7;
		for (int i = 0; i < s.length(); i++)
			hash = (hash * 31 + s.charAt(i)) % entries.length;
		// check for collisions and resolve them
		while (entries[hash] != null) {
			if (entries[hash].getKey().equals(s))
				break;
			hash = (hash + 1) % entries.length;
		}
		return hash;
	}

	/*
	 * return number of words in dictionary
	 */
	public int getWords() {
		return words;
	}
}
