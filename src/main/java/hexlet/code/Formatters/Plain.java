package hexlet.code.Formatters;

import hexlet.code.Pair;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Plain {
    public static String toFormat(Map<String, Pair> diff) throws Exception {
        Set<String> allKeys = new TreeSet<>(diff.keySet());
        StringBuilder plainBuilder = new StringBuilder();

        for (String key : allKeys) {
            String mod = diff.get(key).getModification();
            String keyFormatted = "'" + key + "'";
            String value = getPlainValue(diff.get(key).getValue());
            String oldValue = getPlainValue(diff.get(key).getOldValue());
            switch (mod) {
                case ("deleted") -> {
                    plainBuilder.append("Property ");
                    plainBuilder.append(keyFormatted);
                    plainBuilder.append(" was removed");
                    plainBuilder.append("\n");
                }
                case ("added") -> {
                    plainBuilder.append("Property ");
                    plainBuilder.append(keyFormatted);
                    plainBuilder.append(" was added with value: ");
                    plainBuilder.append(value);
                    plainBuilder.append("\n");
                }
                case ("changed") -> {
                    plainBuilder.append("Property ");
                    plainBuilder.append(keyFormatted);
                    plainBuilder.append(" was updated. From ");
                    plainBuilder.append(oldValue);
                    plainBuilder.append(" to ");
                    plainBuilder.append(value);
                    plainBuilder.append("\n");
                }
                case ("unchanged") -> { }
                default -> throw new Exception("Modification json(yml) field not found");
            }
        }
        return plainBuilder.toString().trim();
    }

    private static String getPlainValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof ArrayList<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
