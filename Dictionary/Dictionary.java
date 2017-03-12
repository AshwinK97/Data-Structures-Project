package dictionary;

public class Dictionary {
	
	Entry[] entries; // Array to store the entries
	
	public Dictionary(String fName) {
		entries = new Entry[200000];
		System.out.println(entries.length);
		System.out.println("index of apple " + convertHash("apple"));
	}
	
	public boolean add(String key, String value) {
		// TODO check if key already exists, if not add new entry
		return false; // return true if successfully added a new entry
	}
	
	public String search(String key) {
		// TODO return value of corresponding key from dictionary
		return null;
	}
	
	public boolean update(String)
	
	public void Load(int size) {
		// TODO load entries[] from file
	}
	
	public int convertHash(String s) { // tested with up to 25 characters
		int hash = 7;
		for (int i=0; i<s.length(); i++)
			hash = (hash*31 + s.charAt(i)) % entries.length;
		return hash;
	}
}
