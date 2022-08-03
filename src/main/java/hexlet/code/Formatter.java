package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String getOutputFormat(Map<String, Pair> result, String outputFormat) throws Exception {
        return switch (outputFormat) {
            case ("stylish") -> Stylish.toFormat(result);
            case ("plain") -> Plain.toFormat(result);
            case ("json") -> Json.toFormat(result);
            default -> throw new Exception("Unsupported output format: " + outputFormat);
        };
    }
}
