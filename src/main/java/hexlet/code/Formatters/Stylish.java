package hexlet.code.Formatters;

import hexlet.code.Pair;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Stylish {
    public static String toFormat(Map<String, Pair> diff) throws Exception {
        Set<String> allKeys = new TreeSet<>(diff.keySet());
        StringBuilder stylishBuilder = new StringBuilder();
        stylishBuilder.append("{\n");

        for (String key : allKeys) {
            Object value = diff.get(key).getStringValue();
            Object oldValue = diff.get(key).getStringOldValue();
            String mod = diff.get(key).getModification();

            switch (mod) {
                case ("deleted") -> {
                    stylishBuilder.append(" ".repeat(2));
                    stylishBuilder.append("- ");
                    stylishBuilder.append(key);
                    stylishBuilder.append(": ");
                    stylishBuilder.append(value);
                }
                case ("added") -> {
                    stylishBuilder.append(" ".repeat(2));
                    stylishBuilder.append("+ ");
                    stylishBuilder.append(key);
                    stylishBuilder.append(": ");
                    stylishBuilder.append(value);
                }
                case ("changed") -> {
                    stylishBuilder.append(" ".repeat(2));
                    stylishBuilder.append("- ");
                    stylishBuilder.append(key);
                    stylishBuilder.append(": ");
                    stylishBuilder.append(oldValue);
                    stylishBuilder.append("\n");
                    stylishBuilder.append(" ".repeat(2));
                    stylishBuilder.append("+ ");
                    stylishBuilder.append(key);
                    stylishBuilder.append(": ");
                    stylishBuilder.append(value);
                }
                case ("unchanged") -> {
                    //CHECKSTYLE:OFF
                    stylishBuilder.append(" ".repeat(4));
                    //CHECKSTYLE:ON
                    stylishBuilder.append(key);
                    stylishBuilder.append(": ");
                    stylishBuilder.append(value);
                }
                default -> throw new Exception("Modification json(yml) field not found");
            }
            stylishBuilder.append("\n");
        }
        stylishBuilder.append("}");
        return stylishBuilder.toString();
    }
}
