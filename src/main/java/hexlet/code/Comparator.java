package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {
    private static final String ADDED = "added";
    private static final String DELETED = "deleted";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";
    public static Map<String, DiffMap> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeySet = new TreeSet<>();
        allKeySet.addAll(map1.keySet());
        allKeySet.addAll(map2.keySet());
        Map<String, DiffMap> comparingResult = new LinkedHashMap<>();
        for (String key : allKeySet) {
            Object firstValue = map1.get(key);
            Object secondValue = map2.get(key);
            if (!map2.containsKey(key)) {
                comparingResult.put(key, new DiffMap(firstValue, secondValue, DELETED));
            } else if (!map1.containsKey(key)) {
                comparingResult.put(key, new DiffMap(firstValue, secondValue, ADDED));
            } else if (!compareValues(firstValue, secondValue)) {
                comparingResult.put(key, new DiffMap(firstValue, secondValue, CHANGED));
            } else {
                comparingResult.put(key, new DiffMap(firstValue, UNCHANGED));
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
