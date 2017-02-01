import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Dictionary {

    private int size;
    List<Entry> entries;

    // creates empty dictionary
    public Dictionary() {
        entries = new ArrayList<Entry>();
    }

    // creates dictionary with entries from specified file
    public Dictionary(String fname) {
        entries = importFromFile(fname);
        // TODO: load entries from key-value pairs in file
    }

    // returns the specified entry and the corresponding value
    public String check(String key) {
        String str = "";
        for (Entry i : entries) {
            if (i.getKey().equals(key))
                str = key + " - " + i.getValue();
        }
        if (str.equals(""))
            return " <key not found> ";
        return str;
    }

    // returns the whole dictionary as a table
    public String read() {
        String str = "\r\nword - definition\r\n=================\r\n";
        for (Entry i : entries)
            str += i.getKey() + " - " + i.getValue() + "\r\n";
        return str;
    }

    // checks if key is already in dictionary, if not then adds as new entry
    public void addEntry(String key, String value) {
        for (Entry i : entries) {
            if (i.getKey().equals(key)) {
                System.out.println(" <key already exists> s");
                return;
            }
        }
        entries.add(new Entry(key, value));
    }

    public void removeEntry(String key) {
        // TODO: remove entry from dictionary based on key given
    }

    public void editEntry(String key) {
        // TODO: edit key and value for specifed key
    }

    // load dictionary data from a text file
    // first line should be key, second line should be value, and so on..
    public ArrayList<Entry> importFromFile(String fname) {
        ArrayList<Entry> buffer = new ArrayList<Entry>();
        try {
            File inFile = new File(fname);
            Scanner input = new Scanner(inFile);
            while(input.hasNext())
                buffer.add(new Entry(input.nextLine().toLowerCase(), input.nextLine().toLowerCase()));
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(" <file not found>");
        }
        return buffer;
    }

    // write dictionary data to a text file
    public void exportToFile() {
        // TODO: export entries to file
    }
}
