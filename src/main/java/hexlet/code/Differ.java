package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


public final class Differ {
    public static String generate(String firstFile, String secondFile) throws Exception {
        return Differ.generate(firstFile, secondFile, "stylish");
    }
    public static String generate(String firstFile, String secondFile, String outputFormat) throws Exception {
        String format1 = firstFile.substring(firstFile.lastIndexOf(".") + 1);
        String format2 = secondFile.substring(secondFile.lastIndexOf(".") + 1);
        String firstFileData = Files.readString(Path.of(firstFile));
        String secondFileData = Files.readString(Path.of(secondFile));
        Map<String, Object> firstData = Parser.getFileContent(firstFileData, format1);
        Map<String, Object> secondData = Parser.getFileContent(secondFileData, format2);
        Map<String, DiffMap> diff = Comparator.genDiff(firstData, secondData);
        return Formatter.getOutputFormat(diff, outputFormat);
    }
}
