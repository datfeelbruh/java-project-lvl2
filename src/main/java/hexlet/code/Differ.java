package hexlet.code;

import hexlet.code.Formatters.Stylish;
import java.util.Map;


public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Pair> diff = Comparator.genDiff(Parser.parse(filepath1), Parser.parse(filepath2));
        return Stylish.toFormat(diff);
    }
}
