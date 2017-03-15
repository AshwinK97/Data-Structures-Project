import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.print.DocFlavor.URL;

public class Dictionary {

	Entry[] entries; // Array to store the entries

	public Dictionary(int wordCount) throws FileNotFoundException {
		entries = new Entry[wordCount]; // initialize entries array to number of starting entries
		for (int i=0; i<wordCount; i++)
			entries[i] = null; // initialize all entries to be null

		fromCSV("add", wordCount, "dictionary.csv");
		// fromCSV("edit", wordCount, "definitions.csv");
		// fromFile("search", wordCount, "words.txt");
		// fromFile("delete", wordCount, "words.txt");
	}

	public Dictionary(String s) {
		if (s.equals("all")) {
			// TODO load all words from dictionary
		}
	}

	public String toString() { // returns a string containing the whole dictionary in CSV format
		String s = null;
		for (int i=0; i<entries.length; i++)
			s += "[" + entries[i].getHash() + "] " + entries[i].getKey() + ", " + entries[i].getValue() + "\n";
		return s;
	}

	public void add(String key, String value) { // checks if key exists, if not adds entry to array
		int hash = convertHash(key);
		if (entries[hash] != null)
			System.out.println("error - key already exists");
		else
			entries[hash] = new Entry(key, value, hash); // check for resizing array
	}

	public void delete(String key) {
		// TODO check if key exists, if true then delete entry
	}

	public String search(String key) {
		int hash = convertHash(key);
		if (entries[hash] != null)
			return entries[hash].getValue();
		return "key not found";
	}

	public void update(String key, String value) {
		// TODO check if key already exists, if it does change it's value
	}

	public void fromFile(String function, int wordCount, String fName) throws FileNotFoundException {
		if (function.equals("search")) {
			System.out.print("Searching " + wordCount + " entries from '" + fName + "' ... ");
			// TODO add words
		} else if (function.equals("delete")) {
			System.out.print("Deleting " + wordCount + " entries from '" + fName + "' ... ");
			// TODO delete words
		} else {
			System.out.println("invalid fromFile operation");
		}
	}

	public void fromCSV(String function, int wordCount, String fName) throws FileNotFoundException {
		if (function.equals("add")) {
			System.out.print("Adding " + wordCount + " entries from '" + fName + "' ... ");
			// Scanner csv = new Scanner(new File("c:/Users/studentID/Documents/EclipseProjects/Dictionary/src/dictionary.csv")); // change this to load from root directory
			Scanner csv = new Scanner(new File("dictionary.csv")); // change this to load from root directory
			csv.useDelimiter(",");
			for (int i=0; i<wordCount; i++)
				add(csv.next(), csv.next());
			csv.close();
		} else if (function.equals("edit")) {
			System.out.print("Editing " + wordCount + " entries from '" + fName + "' ... ");
			// TODO edit words
		} else {
			System.out.println("invalid fromCSV operation");
		}
		System.out.println("done");
	}

	public void resizeEntries(int newSize) {
		// TODO resize entries[] when not enough space to insert new entry
	}

	public int convertHash(String s) {
		// generate a hash
		int hash = 7;
		for (int i=0; i<s.length(); i++)
			hash = (hash*31 + s.charAt(i)) % entries.length;
		// check for collisions and resolve them
		while(entries[hash]!=null)
			hash = (hash + 1) % entries.length;
		return hash;
	}
}
