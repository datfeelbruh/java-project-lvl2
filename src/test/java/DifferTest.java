import hexlet.code.Differ;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DifferTest {
    String absolutePathFile1;
    String absolutePathFile2;
    @BeforeEach
    public void beforeEach() {
        Path file1 = Paths.get("src", "test", "resources", "file1.json");
        Path file2 = Paths.get("src", "test", "resources", "file2.json");
        absolutePathFile1 = file1.toFile().getAbsolutePath();
        absolutePathFile2 = file2.toFile().getAbsolutePath();
    }

    @Test
    void diffTest() throws IOException {
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
}
