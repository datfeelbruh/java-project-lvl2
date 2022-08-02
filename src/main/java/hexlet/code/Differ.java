package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        Path firstFilepath = Paths.get(filepath1).toAbsolutePath();
        Path secondFilepath = Paths.get(filepath2).toAbsolutePath();
        List<String> firstFileLines = Files.readAllLines(firstFilepath);
        List<String> secondFileLines = Files.readAllLines(secondFilepath);
        String firstJson = String.join("", firstFileLines);
        String secondJson = String.join("", secondFileLines);
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> firstMap = objectMapper.readValue(firstJson, new TypeReference<>() { });
        Map<String, Object> secondMap = objectMapper.readValue(secondJson, new TypeReference<>() { });
        System.out.println(diff(firstMap, secondMap));
        return diff(firstMap, secondMap);
    }
    private static String diff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        StringBuilder resultBuilder = new StringBuilder();
        Set<String> allKeySet = new TreeSet<>();
        allKeySet.addAll(firstMap.keySet());
        allKeySet.addAll(secondMap.keySet());
        for (String key : allKeySet) {
            if (!firstMap.containsKey(key)) {
                String value = "+ " + key + ":" + " " + secondMap.get(key);
                genDiff(value, resultBuilder);
            } else if (!secondMap.containsKey(key)) {
                String value = "- " + key + ":" + " " + firstMap.get(key);
                genDiff(value, resultBuilder);
            } else {
                if (!firstMap.get(key).equals(secondMap.get(key))) {
                    String value = "- " + key + ":" + " " + firstMap.get(key);
                    genDiff(value, resultBuilder);
                    value = "+ " + key + ":" + " " + secondMap.get(key);
                    genDiff(value, resultBuilder);
                } else {
                    String value = "  " + key + ":" + " " + secondMap.get(key);
                    genDiff(value, resultBuilder);
                }
            }
        }
        resultBuilder.insert(0, "{\n");
        resultBuilder.insert(resultBuilder.length() - 1, "\n}");
        return resultBuilder.toString();
    }

    private static void genDiff(String value, StringBuilder builder) {
        builder.append("  ");
        builder.append(value);
        builder.append("\n");
    }
}
