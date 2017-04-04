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
		// TODO load all words from dictionary
	}
	
	public void runTest(int wordCount) throws FileNotFoundException {
		fromCSV("add", wordCount, "dictionary.csv");
		fromCSV("edit", wordCount, "definitions.csv");
//		fromFile("search", wordCount, "words.txt");
//		fromFile("delete", wordCount, "words.txt");
	}

	public String toString() { // returns a string containing the whole dictionary
		String s = "\n# of words: " + words + "\n---------------------";
		for (int i=0; i<entries.length; i++) {
			if (entries[i] == null)
				s += "\n" + i + ". " + "null";
			else
				s += "\n" + i + ". " + entries[i].toString();
		}
		return s;
	}

	public void add(String key, String value) {
		int hash = convertHash(key);
		if (entries[hash]!=null && entries[hash].getKey().equals(key)) { // if key already exists
			entries[hash].setValue(entries[hash].getValue() + "\n   -> " + value); // add value to that key
		}
		else {
			if (words > entries.length) // in case there is no room for more words
				resizeEntries(); // add 100 places to the array
			entries[hash] = new Entry(key, "\n   -> " + value, hash); // if key doesn't exist, create new entry
			words++;
		}
	}

	public void delete(String key) {
		int hash = convertHash(key);
		if (entries[hash]!=null && entries[hash].getKey().equals(key)) { // if key exists, make that entry null
			entries[hash] = null;
			words--;
		} else
			System.out.println("\nword not found");
	}

	public String search(String key) {
		int hash = convertHash(key);
		if (entries[hash] != null && entries[hash].getKey().equals(key))
			return "\n" + key + ": " + entries[hash].getValue();
		return "\nword not found";
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
		if (function.equals("search")) {
			System.out.print("Searching " + wordCount + " entries from '" + fName + "' ... ");
			// TODO add words from file
		} else if (function.equals("delete")) {
			System.out.print("Deleting " + wordCount + " entries from '" + fName + "' ... ");
			// TODO delete words from file
		} else {
			System.out.println("invalid fromFile operation");
		}
	}

	public void fromCSV(String function, int wordCount, String fName) throws FileNotFoundException {
		if (function.equals("add")) {
			System.out.print("Adding " + wordCount + " entries from '" + fName + "' ... ");
			Scanner csvIn = new Scanner(new File("D:/Desktop/dictionary/res/dictionary.csv"));
			String[] kv;
			for (int i=0; i<wordCount; i++) {
				kv = csvIn.nextLine().split("\",\"");
				add(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")); // remove quotes from strings before adding
			}
			csvIn.close();
		} else if (function.equals("edit")) {
			System.out.print("Editing " + wordCount + " entries from '" + fName + "' ... ");
			Scanner csvIn = new Scanner(new File("D:/Desktop/dictionary/res/definitions.csv"));
			String[] kv;
			for (int i=0; i<wordCount; i++) {
				kv = csvIn.nextLine().split("\",\"");
				edit(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")); // remove quotes from strings before adding
			}
			csvIn.close();
		} else {
			System.out.println("invalid fromCSV operation");
		}
		System.out.println("done");
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
