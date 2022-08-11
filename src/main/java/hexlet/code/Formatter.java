package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String getOutputFormat(Map<String, DiffMap> result, String outFormat) throws Exception {
        return switch (outFormat) {
            case ("stylish") -> Stylish.formatting(result);
            case ("plain") -> Plain.formatting(result);
            case ("json") -> Json.formatting(result);
            default -> throw new Exception("Unsupported output format: " + outFormat);
        };
    }
}
