package dictionary;

public class Entry {
	
	private String key, value;
	private int hash;
	
	public Entry(String key, String value, int hash) {
		this.key = key;
		this.value = value;
		this.hash = hash;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public int getHash() {
		return hash;
	}
}
