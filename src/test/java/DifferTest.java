import hexlet.code.Differ;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


//CHECKSTYLE:OFF
public class DifferTest {
    private static String stylishResult;

    @BeforeAll
    public static void beforeAll() throws IOException {
        stylishResult = Files.readString(new File("src/test/resources/stylishResult.stylish").toPath());
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testGenerateDifference(String format) throws Exception {
        Path file1 = Paths.get("src", "test", "resources", "file1." + format);
        Path file2 = Paths.get("src", "test", "resources", "file2." + format);
        String absolutePathFile1 = file1.toFile().getAbsolutePath();
        String absolutePathFile2 = file2.toFile().getAbsolutePath();

        String actualStylish = Differ.generate(absolutePathFile1, absolutePathFile2);
        assertThat(actualStylish).isEqualTo(stylishResult);
    }
//    void diffTestJson() throws IOException {
//        Path file1 = Paths.get("src", "test", "resources", "file1.json");
//        Path file2 = Paths.get("src", "test", "resources", "file2.json");
//        absolutePathFile1 = file1.toFile().getCanonicalPath();
//        absolutePathFile2 = file2.toFile().getCanonicalPath();
//        String expected = """
//                {
//                  - follow: false
//                    host: hexlet.io
//                  - proxy: 123.234.53.22
//                  - timeout: 50
//                  + timeout: 20
//                  + verbose: true
//                }
//                """;
//        String actual = Differ.generate(absolutePathFile1, absolutePathFile2);
//        Assertions.assertEquals(actual, expected);
//    }
//
//    @Test
//    void diffTestYml() throws IOException {
//        Path file1 = Paths.get("src", "test", "resources", "file1.yml");
//        Path file2 = Paths.get("src", "test", "resources", "file2.yml");
//        absolutePathFile1 = file1.toFile().getCanonicalPath();
//        absolutePathFile2 = file2.toFile().getCanonicalPath();
//        String expected = """
//                {
//                  - follow: false
//                    host: hexlet.io
//                  - proxy: 123.234.53.22
//                  - timeout: 50
//                  + timeout: 20
//                  + verbose: true
//                }
//                """;
//        String actual = Differ.generate(absolutePathFile1, absolutePathFile2);
//        Assertions.assertEquals(actual, expected);
//    }
//    //CHECKSTYLE:ON
}
