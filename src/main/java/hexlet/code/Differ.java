package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json1 = jsonToString(filepath1);
        String json2 = jsonToString(filepath2);
        Map<String, Object> firstMap = objectMapper.readValue(json1, new TypeReference<>() {});
        Map<String, Object> secondMap = objectMapper.readValue(json2, new TypeReference<>() {});

        System.out.println(firstMap);
        System.out.println(secondMap);
        System.out.println(diff(firstMap, secondMap));
        return diff(firstMap, secondMap);
    }
    /*
    получаем json строку для jackson mapper
     */
    private static String jsonToString(String filepath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bf = Files.newBufferedReader(Path.of(filepath))) {
            while (bf.ready()) {
                sb.append(bf.readLine());
            }
        }
        return sb.toString();
    }
    /*
    - удален во втором
    + добавлен во втором
     неизменен во втором
    - в первом и + во втором = изменен во втором нужно вывести обе пары подряд
     */
    private static String diff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, Object> difference = new HashMap<>();
        Set<String> allKeySet = new TreeSet<>();
        allKeySet.addAll(firstMap.keySet());
        allKeySet.addAll(secondMap.keySet());
        for (String key : allKeySet) {
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                // Неизменен
                if (firstMap.get(key).equals(secondMap.get(key))) {
                    difference.put(key, firstMap.get(key));
                    // Изменен во втором обе пары в линкедмап
                } else {
                    difference.put(key, firstMap.get(key));
                    difference.put(key, secondMap.get(key));
                }
            } else {
                // Есть в первом удален во втором
                if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                    difference.put(key, firstMap.get(key));
                    // Нет в первом добавлен во втором
                } else if (!firstMap.containsKey(key) && secondMap.containsKey(key)) {
                    difference.put(key, secondMap.get(key));
                }
            }
        }
        Map<String, Object> treeMap = new TreeMap<>(difference);
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<String, Object> pair : treeMap.entrySet()) {
            sb.append("  ");
            sb.append(pair.getKey());
            sb.append(":");
            sb.append(" ");
            sb.append(pair.getValue());
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
