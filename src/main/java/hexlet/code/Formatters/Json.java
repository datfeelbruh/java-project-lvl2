package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Pair;

import java.util.Map;

public class Json {
    public static String toFormat(Map<String, Pair> diff) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        return objectMapper.writeValueAsString(diff);
    }
}
