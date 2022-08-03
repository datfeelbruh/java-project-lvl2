package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)

public final class App implements Callable<Object> {
    @Parameters(
            index = "0",
            paramLabel = "filepath1",
            description = "path to first file"
    )
    private String filepath1;

    @Parameters(
            index = "1",
            paramLabel = "filepath2",
            description = "path to second file"
    )
    private String filepath2;

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: ${DEFAULT_VALUE}]",
            defaultValue = "stylish"
    )
    private String format;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {
        String result = Differ.generate(filepath1, filepath2, format);
        System.out.println(result);
        return 0;
    }
}
