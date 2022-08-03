import hexlet.code.Differ;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//CHECKSTYLE:OFF
public class DifferTest {
    String absolutePathFile1;
    String absolutePathFile2;

    @Test
    void diffTestJson() throws IOException {
        Path file1 = Paths.get("src", "test", "resources", "file1.json");
        Path file2 = Paths.get("src", "test", "resources", "file2.json");
        absolutePathFile1 = file1.toFile().getCanonicalPath();
        absolutePathFile2 = file2.toFile().getCanonicalPath();
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        String actual = Differ.generate(absolutePathFile1, absolutePathFile2);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void diffTestYml() throws IOException {
        Path file1 = Paths.get("src", "test", "resources", "file1.yml");
        Path file2 = Paths.get("src", "test", "resources", "file2.yml");
        absolutePathFile1 = file1.toFile().getCanonicalPath();
        absolutePathFile2 = file2.toFile().getCanonicalPath();
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        String actual = Differ.generate(absolutePathFile1, absolutePathFile2);
        Assertions.assertEquals(actual, expected);
    }
    //CHECKSTYLE:ON
}
