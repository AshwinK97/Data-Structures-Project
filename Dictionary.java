import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Dictionary {

	private Entry[] entries; // Array to store the entries
	private int words;

	public Dictionary(int wordCount) throws FileNotFoundException {
		entries = new Entry[wordCount]; // initialize size of entries array
		words = 0;
		for (int i=0; i<entries.length; i++)
			entries[i] = null; // initialize all entries to be null
	}

	public Dictionary(String s) {
		entries = new Entry[120000]; // initialize size of entries array
		words = 0;
		for (int i=0; i<entries.length; i++)
			entries[i] = null; // initialize all entries to be null
	}
	
	public void runTests(int wordCount) throws FileNotFoundException {
		fromCSV("add", wordCount, "dictionary.csv");
		fromCSV("edit", wordCount, "definitions.csv");
		fromFile("search", wordCount, "words.txt");
		fromFile("delete", wordCount, "words.txt");
	}

	public void view() { // returns a string containing the whole dictionary
		System.out.print("\n# of words: " + words + "\n---------------------");
		for (int i=0; i<entries.length; i++) {
			if (entries[i] == null)
				System.out.print("\n" + i + ". " + "null");
			else
				System.out.print("\n" + i + ". " + entries[i].toString());
		}
	}

	public boolean add(String key, String value) {
		int hash = convertHash(key);
		if (entries[hash]!=null && entries[hash].getKey().equals(key)) { // if key already exists
			entries[hash].setValue(entries[hash].getValue() + "\n   -> " + value); // add value to that key
			return true;
		}
		else {
			if (words > entries.length) // in case there is no room for more words
				resizeEntries(); // add 100 places to the array
			entries[hash] = new Entry(key, "\n   -> " + value, hash); // if key doesn't exist, create new entry
			words++;
			return true;
		}
	}

	public boolean delete(String key, boolean view) {
		int hash = convertHash(key);
		if (entries[hash]!=null && entries[hash].getKey().equals(key)) { // if key exists, make that entry null
			entries[hash] = null;
			words--;
			return true;
		}
		if (view)
			System.out.println("\nword not found");
		return false;
			
	}

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

	public boolean edit(String key, String value) {
		int hash = convertHash(key);
		if (entries[hash] != null && entries[hash].getKey().equals(key)) {
			entries[hash].setValue("\n   -> " + value);
			return true;
		}
		return false;
	}

	public void fromFile(String function, int wordCount, String fName) throws FileNotFoundException {
		int success = 0;
		if (function.equals("search")) {
			Scanner fileIn = new Scanner(new File("C:/Users/100584423/Desktop/res/" + fName));
			if (wordCount==-1) {
				System.out.print("Searching all entries from '" + fName + "'     ... ");
				while (fileIn.hasNextLine()) {
					if (search(fileIn.nextLine(), false))
						success++;
				}
			} else {
				System.out.print("Searching " + wordCount + " entries from '" + fName + "'     ... ");
				for (int i=0; i<wordCount; i++) {
					if (search(fileIn.nextLine(), false))
						success++;
				}
			}
			fileIn.close();
			System.out.print("done");
			System.out.println("\n -> Successful searches: " + success + " runtime: ");
		} else if (function.equals("delete")) {
			Scanner fileIn = new Scanner(new File("C:/Users/100584423/Desktop/res/" + fName));
			if (wordCount==-1) {
				System.out.print("Deleting all entries from '" + fName + "'      ... ");
				while (fileIn.hasNextLine()) {
					if (delete(fileIn.nextLine(), false))
						success++;
				}
			} else {
				System.out.print("Deleting " + wordCount + " entries from '" + fName + "'      ... ");
				for (int i=0; i<wordCount; i++) {
					if (delete(fileIn.nextLine(), false))
						success++;
				}
			}
			fileIn.close();
			System.out.print("done");
			System.out.println("\n -> Successful deletions: " + success + " runtime: ");
		} else {
			System.out.println("invalid fromFile operation");
		}
	}

	public void fromCSV(String function, int wordCount, String fName) throws FileNotFoundException {
		int success = 0;
		if (function.equals("add")) {
			Scanner csvIn = new Scanner(new File("C:/Users/100584423/Desktop/res/" + fName));
			String[] kv;
			if (wordCount==-1) {
				System.out.print("Adding all entries from '" + fName + "'   ... ");
				while (csvIn.hasNextLine()) {
					kv = csvIn.nextLine().split("\",\"");
					if (add(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")))
						success++;
				}
			} else {
				System.out.print("Adding " + wordCount + " entries from '" + fName + "'   ... ");
				for (int i=0; i<wordCount; i++) {
					kv = csvIn.nextLine().split("\",\"");
					if (add(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")))
						success++;
				}
			}
			csvIn.close();
			System.out.print("done");
			System.out.println("\n -> Successful additions: " + success + " runtime: ");
		} else if (function.equals("edit")) {
			Scanner csvIn = new Scanner(new File("C:/Users/100584423/Desktop/res/" + fName));
			String[] kv;
			if (wordCount==-1) {
				System.out.print("Editing all entries from '" + fName + "' ... ");
				while (csvIn.hasNextLine()) {
					kv = csvIn.nextLine().split("\",\"");
					if (edit(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")))
						success++;
				}
			} else {
				System.out.print("Editing " + wordCount + " entries from '" + fName + "' ... ");
				for (int i=0; i<wordCount; i++) {
					kv = csvIn.nextLine().split("\",\"");
					if (edit(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")))
						success++;
				}
			}
			csvIn.close();
			System.out.print("done");
			System.out.println("\n -> Successful edits: " + success + " runtime: ");
		} else {
			System.out.println("invalid fromCSV operation");
		}
	}

	public void resizeEntries() { // add 100 indexes to array
		entries = Arrays.copyOf(entries, entries.length+100);
	}

	public int convertHash(String s) {
		// generate a hash
		int hash = 7;
		for (int i=0; i<s.length(); i++)
			hash = (hash*31 + s.charAt(i)) % entries.length;
		// check for collisions and resolve them
		while(entries[hash]!=null) {
			if (entries[hash].getKey().equals(s)) // if s is same as key, return location of that key
				break;
			hash = (hash + 1) % entries.length; // if collision occurs, add +1 to hash
		}
		return hash;
	}
	
	public int getWords() {
		return words;
	}
}
