package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffMap;

import java.util.Map;

public class Json {
    public static String formatting(Map<String, DiffMap> diff) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        return objectMapper.writeValueAsString(diff);
    }
}
