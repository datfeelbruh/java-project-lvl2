package hexlet.code;

import hexlet.code.Formatters.Stylish;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, Pair> diff = Comparator.genDiff(Parser.parse(filepath1), Parser.parse(filepath2));
        String result = Stylish.toFormat(diff);
        return result;
    }
}
