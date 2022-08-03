import hexlet.code.Differ;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


public final class DifferTest {
    private static String stylishResult;
    private static String plainResult;
    private static String jsonResult;

    @BeforeAll
    public static void beforeAll() throws IOException {
        stylishResult = Files.readString(new File("src/test/resources/stylishResult.stylish").toPath());
        plainResult = Files.readString(new File("src/test/resources/plainResult.plain").toPath());
        jsonResult = Files.readString(new File("src/test/resources/jsonResult.json").toPath());

    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testGenerateDifference(String format) throws Exception {
        Path file1 = Paths.get("src", "test", "resources", "file1." + format);
        Path file2 = Paths.get("src", "test", "resources", "file2." + format);
        String absolutePathFile1 = file1.toFile().getAbsolutePath();
        String absolutePathFile2 = file2.toFile().getAbsolutePath();

        String actualStylish = Differ.generate(absolutePathFile1, absolutePathFile2, "stylish");
        String actualPlain = Differ.generate(absolutePathFile1, absolutePathFile2, "plain");
        String actualJson = Differ.generate(absolutePathFile1, absolutePathFile2, "json");
        assertThat(actualStylish).isEqualTo(stylishResult);
        assertThat(actualPlain).isEqualTo(plainResult);
        assertThat(actualJson).isEqualTo(jsonResult);
    }

    @Test
    public void testUnknownFormat() {
        Exception thrown = Assertions.assertThrows(Exception.class, () ->
            Differ.generate(
                    "src/test/resources/file1.json",
                    "src/test/resources/UnknownFormatFile.hxlt",
                    "stylish"
            )
        );
        Assertions.assertEquals("Unknown file format: hxlt", thrown.getMessage());
    }

    @Test
    public void testUnknownOutputFormat() {
        Exception thrown = Assertions.assertThrows(Exception.class, () ->
            Differ.generate(
                    "src/test/resources/file1.json",
                    "src/test/resources/file2.json",
                    "hxlt"
            )
        );
        Assertions.assertEquals("Unsupported output format: hxlt", thrown.getMessage());
    }
}
