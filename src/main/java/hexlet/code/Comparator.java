package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {
    public static Map<String, Pair> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> allKeySet = new TreeSet<>();
        allKeySet.addAll(firstMap.keySet());
        allKeySet.addAll(secondMap.keySet());
        Map<String, Pair> comparingResult = new LinkedHashMap<>();
        for (String key : allKeySet) {
            Object firstValue = firstMap.get(key);
            Object secondValue = secondMap.get(key);
            if (!secondMap.containsKey(key)) {
                comparingResult.put(key, new Pair(firstValue, null, "deleted"));
            } else if (!firstMap.containsKey(key)) {
                comparingResult.put(key, new Pair(secondValue, null, "added"));
            } else if (!compareValues(firstValue, secondValue)) {
                comparingResult.put(key, new Pair(secondValue, firstValue, "changed"));
            } else {
                comparingResult.put(key, new Pair(secondValue, null, "unchanged"));
            }
        }
        return comparingResult;
    }

    private static boolean compareValues(Object firstValue, Object secondValue) {
        if ((firstValue == null) && (secondValue == null)) {
            return true;
        }
        if ((firstValue == null) || (secondValue == null)) {
            return false;
        }
        return firstValue.equals(secondValue);
    }
}
