package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

public class Comparator {
    private static final String ADDED = "added";
    private static final String DELETED = "deleted";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";
    public static Map<String, Map<String, Object>> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeySet = new TreeSet<>();
        allKeySet.addAll(map1.keySet());
        allKeySet.addAll(map2.keySet());
        Map<String, Map<String, Object>> comparingResult = new LinkedHashMap<>();
        for (String key : allKeySet) {
            Object firstValue = map1.get(key);
            Object secondValue = map2.get(key);
            Map<String, Object> valuesStatus = new HashMap<>();
            if (!map2.containsKey(key)) {
                valuesStatus.put("value", firstValue);
                valuesStatus.put("newValue", secondValue);
                valuesStatus.put("modification", DELETED);
                comparingResult.put(key, valuesStatus);

            } else if (!map1.containsKey(key)) {
                valuesStatus.put("value", firstValue);
                valuesStatus.put("newValue", secondValue);
                valuesStatus.put("modification", ADDED);
                comparingResult.put(key, valuesStatus);
            } else if (!compareValues(firstValue, secondValue)) {
                valuesStatus.put("value", firstValue);
                valuesStatus.put("newValue", secondValue);
                valuesStatus.put("modification", CHANGED);
                comparingResult.put(key, valuesStatus);
            } else {
                valuesStatus.put("value", firstValue);
                valuesStatus.put("modification", UNCHANGED);
                comparingResult.put(key, valuesStatus);
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
