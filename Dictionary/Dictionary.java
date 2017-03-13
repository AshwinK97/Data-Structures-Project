package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {
	
	Entry[] entries; // Array to store the entries
	
	public Dictionary(int wordCount) throws FileNotFoundException {
		addFromCSV(wordCount, "dictionary.csv");
		// fromCSV("edit", wordCount, "definitions.csv");
		// fromFile("search", wordCount, "words.txt");
		// fromFile("delete", wordCount, "words.txt");
	}
	
	public String toString() {
		String s = null;
		for (int i=0; i<entries.length; i++)
			s += "[" + entries[i].getHash() + "] " +entries[i].getKey() + ", " + entries[i].getValue() + "\n";
		return s;
	}
	
	public boolean add(String key, String value) {
		// TODO check if key already exists, if not add new entry
		return false; // return true if successfully added a new entry
	}
	
	public String search(String key) {
		// TODO return value of corresponding key from dictionary
		return null;
	}
	
	public boolean update(String key, String value) {
		// TODO check if key already exists, if it does change it's value
		return false;
	}
	
	public void fromFile(String function, int size, String fName) throws FileNotFoundException {
		// TODO perform specified function using words from file
	}
	
	public void addFromCSV(int wordCount, String fName) throws FileNotFoundException { // check for collisions
		entries = new Entry[wordCount]; // initialize entries array to number of starting entries
		for (int i=0; i<wordCount; i++)
			entries[i] = new Entry("null", "null", -1);
		Scanner csv = new Scanner(new File("C:/Users/100584423/Desktop/dictionary/dictionary.csv"));
        csv.useDelimiter(",");
        for (int i=0; i<wordCount; i++) {
        	Entry temp = new Entry(csv.next(), csv.next(), convertHash(csv.next()));
        	entries[temp.getHash()] = temp;
        }
        csv.close();
	}
	
	public void resizeEntries(int newSize) {
		// TODO resize entries[] when not enough space to insert new entry
	}
	
	public int convertHash(String s) { // need to check for collisions
		int hash = 7;
		for (int i=0; i<s.length(); i++)
			hash = (hash*31 + s.charAt(i)) % entries.length;
		return hash;
	}
}
