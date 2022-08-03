package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getFileContent(String filename, String format) throws Exception {
        ObjectMapper objectMapper;
        if (format.equals("json")) {
            objectMapper = new ObjectMapper(new JsonFactory());
        } else if (format.equals("yml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new Exception("Unknown file format: " + format);
        }
        return objectMapper.readValue(filename, new TypeReference<>() { });
    }
}
