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
        return genDiff(diff(firstMap, secondMap));
    }
    private static List<String> diff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        List<String> difference = new LinkedList<>();
        Set<String> allKeySet = new TreeSet<>();
        allKeySet.addAll(firstMap.keySet());
        allKeySet.addAll(secondMap.keySet());
        for (String key : allKeySet) {
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (firstMap.get(key).equals(secondMap.get(key))) {
                    difference.add("  " + key + ":" + " " + firstMap.get(key));
                } else {
                    difference.add("- " + key + ":" + " " + firstMap.get(key));
                    difference.add("+ " + key + ":" + " " + secondMap.get(key));
                }
            } else {
                if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                    difference.add("- " + key + ":" + " " + firstMap.get(key));
                } else if (!firstMap.containsKey(key) && secondMap.containsKey(key)) {
                    difference.add("+ " + key + ":" + " " + secondMap.get(key));
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
        sb.append("}");
        return sb.toString();
    }
}
