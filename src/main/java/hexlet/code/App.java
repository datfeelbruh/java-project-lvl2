package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)


public class App implements Runnable {
    @Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish]",
            defaultValue = "stylish"
    )
    String format = "stylish";

    @Parameters(
            paramLabel = "filepath1",
            description = "path to first file"
    )
    String filepath1;

    @Parameters(
            paramLabel = "filepath2",
            description = "path to second file"
    )
    String filepath2;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        Differ.generate();
    }

}
