
class Entry {

    private String key, value;

    // initialzed entry
    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // empty entry
    public Entry() {
        key = null;
        value = null;
    }

    // returns value of 'key'
    public String getKey() {
        return key;
    }

    // return value of 'value'
    public String getValue() {
        return value;
    }

    // set value of 'key'
    public void setKey(String key) {
        this.key = key;
    }

    // set value of 'value'
    public void setValue(String value) {
        this.value = value;
    }
}
