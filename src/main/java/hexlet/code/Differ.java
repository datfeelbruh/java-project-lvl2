package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, Object> firstMap = Parser.parse(filepath1);
        Map<String, Object> secondMap = Parser.parse(filepath2);
        return genDiff(diff(firstMap, secondMap));
    }
    private static List<String> diff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        List<String> difference = new LinkedList<>();
        Set<String> allKeySet = new TreeSet<>();
        allKeySet.addAll(firstMap.keySet());
        allKeySet.addAll(secondMap.keySet());
        for (String key : allKeySet) {
            if (!firstMap.containsKey(key)) {
                difference.add("+ " + key + ":" + " " + secondMap.get(key));
            } else if (!secondMap.containsKey(key)) {
                difference.add("- " + key + ":" + " " + firstMap.get(key));
            } else {
                if (!firstMap.get(key).equals(secondMap.get(key))) {
                    difference.add("- " + key + ":" + " " + firstMap.get(key));
                    difference.add("+ " + key + ":" + " " + secondMap.get(key));
                } else {
                    difference.add("  " + key + ":" + " " + secondMap.get(key));
                }
            }
        }
        return difference;
    }

    private static String genDiff(List<String> difference) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String elem : difference) {
            sb.append("  ");
            sb.append(elem);
            sb.append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
