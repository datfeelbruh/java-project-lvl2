package hexlet.code;

public final class DiffMap {
    private final Object value;
    private Object newValue;
    private final String modification;

    public DiffMap(Object v1, Object v2, String mod) {
        this.value = v1;
        this.newValue = v2;
        this.modification = mod;
    }

    public DiffMap(Object v1, String mod) {
        this.value = v1;
        this.modification = mod;
    }

    public Object getValue() {
        return value;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getModification() {
        return modification;
    }
}
