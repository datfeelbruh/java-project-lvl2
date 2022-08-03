package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filename) throws IOException {
        return getFileType(filename);
    }

    private static Map<String, Object> getFileType(String filename) throws IOException {
        Path firstFilepath = Paths.get(filename).toAbsolutePath();
        List<String> fileLines = Files.readAllLines(firstFilepath);
        ObjectMapper objectMapper;
        if (filename.endsWith("json")) {
            objectMapper = new ObjectMapper(new JsonFactory());
            String json = String.join("", fileLines);
            return objectMapper.readValue(json, new TypeReference<>() { });
        } else {
            objectMapper = new ObjectMapper(new YAMLFactory());
            String yml = String.join("", fileLines);
            return objectMapper.readValue(yml, new TypeReference<>() { });
        }
    }
}
