package hexlet.code.formatters;


import hexlet.code.DiffMap;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

public class Plain {
    public static String formatting(Map<String, DiffMap> diff) throws Exception {
        Set<String> allKeys = new TreeSet<>(diff.keySet());
        StringBuilder plainBuilder = new StringBuilder();

        for (String key : allKeys) {
            String mod = diff.get(key).getModification();
            String keyFormatted = "'" + key + "'";
            String value = getPlainValue(diff.get(key).getValue());
            String newValue = getPlainValue(diff.get(key).getNewValue());
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
                    plainBuilder.append(newValue);
                    plainBuilder.append("\n");
                }
                case ("changed") -> {
                    plainBuilder.append("Property ");
                    plainBuilder.append(keyFormatted);
                    plainBuilder.append(" was updated. From ");
                    plainBuilder.append(value);
                    plainBuilder.append(" to ");
                    plainBuilder.append(newValue);
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
        if (value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
