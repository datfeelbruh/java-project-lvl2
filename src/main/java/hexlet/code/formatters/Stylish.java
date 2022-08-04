package hexlet.code.formatters;


import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Stylish {
    public static String formatting(Map<String, Map<String, Object>> diff) throws Exception {
        Set<String> allKeys = new TreeSet<>(diff.keySet());
        StringBuilder stylishBuilder = new StringBuilder();
        stylishBuilder.append("{\n");

        for (String key : allKeys) {
            Object value = diff.get(key).get("value");
            Object newValue = diff.get(key).get("newValue");
            String mod = (String) diff.get(key).get("modification");

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
                    stylishBuilder.append(newValue);
                }
                case ("changed") -> {
                    stylishBuilder.append(" ".repeat(2));
                    stylishBuilder.append("- ");
                    stylishBuilder.append(key);
                    stylishBuilder.append(": ");
                    stylishBuilder.append(value);
                    stylishBuilder.append("\n");
                    stylishBuilder.append(" ".repeat(2));
                    stylishBuilder.append("+ ");
                    stylishBuilder.append(key);
                    stylishBuilder.append(": ");
                    stylishBuilder.append(newValue);
                }
                case ("unchanged") -> {
                    stylishBuilder.append(" ".repeat(2));
                    stylishBuilder.append(" ".repeat(2));
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
