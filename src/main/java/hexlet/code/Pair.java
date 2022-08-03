package hexlet.code;

public final class Pair {
    private final Object value;
    private final Object oldValue;
    private final String modification;

    public Pair(Object firstFieldValue, Object secondFieldValue, String modificationStatus) {
        this.value = firstFieldValue;
        this.oldValue = secondFieldValue;
        this.modification = modificationStatus;
    }

    public Object getValue() {
        return value;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public String getModification() {
        return modification;
    }

    public String getStringValue() {
        return String.valueOf(value);
    }

    public String getStringOldValue() {
        return String.valueOf(oldValue);
    }

}
