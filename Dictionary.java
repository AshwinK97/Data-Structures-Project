import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {

	Entry[] entries; // Array to store the entries

	public Dictionary(int wordCount) throws FileNotFoundException {
		entries = new Entry[(int)(wordCount + 10*Math.log10(wordCount))]; // initialize entries array to entries + 10*log10(entries)
		for (int i=0; i<entries.length; i++)
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
		String s = "";
		for (int i=0; i<entries.length; i++) {
			if (entries[i] == null)
				s += i + ". " + "null  --  null \n";
			else
				s += i + ". " + entries[i].getKey() + "  --  " + entries[i].getValue() + " \n";
		}
		return s;
	}

	public void add(String key, String value) { // if key exists, adds value to that key, if not create new entry
		int hash = convertHash(key);
		if (entries[hash]!=null && entries[hash].getKey().equals(key)) {
//			System.out.println(entries[hash].getKey());
			entries[hash].setValue(entries[hash].getValue() + " " + value);
		}
		else {
			entries[hash] = new Entry(key, value, hash); // check for resizing array
		}
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

	public void edit(String key, String value) {
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
			Scanner csvIn = new Scanner(new File("D:/Desktop/dictionary/dictionary.csv"));
			String[] kv;
			for (int i=0; i<wordCount; i++) {
				kv = csvIn.nextLine().split("\",\"");
//				System.out.println(kv[0] + " - " + kv[1] + "\n");
				add(kv[0].replaceAll("\"", ""), kv[1].replaceAll("\"", "")); // remove quotes from strings before adding
			}
			csvIn.close();
		} else if (function.equals("edit")) {
			System.out.print("Editing " + wordCount + " entries from '" + fName + "' ... ");
			// TODO edit words
		} else {
			System.out.println("invalid fromCSV operation");
		}
		System.out.println("done\n");
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
		while(entries[hash]!=null) {
			if (entries[hash].getKey().equals(s)) // if s is same as key, return location of that key
				break;
			hash = (hash + 1) % entries.length; // add +1 to hash
		}
		return hash;
	}
}
