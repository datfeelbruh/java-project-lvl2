package hexlet.code;

public class Pair {
    private final Object value;
    private final Object oldValue;
    private final String modification;

    Pair(Object value, Object oldValue, String modification) {
        this.value = value;
        this.oldValue = oldValue;
        this.modification = modification;
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
