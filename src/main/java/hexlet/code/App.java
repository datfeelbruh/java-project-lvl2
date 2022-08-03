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

//CHECKSTYLE:OFF
public class App implements Callable<Object> {
    @Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish]",
            defaultValue = "stylish"
    )
    String format = "stylish";

    @Parameters(
            index = "0",
            paramLabel = "filepath1",
            description = "path to first file"
    )
    String filepath1;

    @Parameters(
            index = "1",
            paramLabel = "filepath2",
            description = "path to second file"
    )
    String filepath2;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {
        String result = Differ.generate(filepath1, filepath2);
        System.out.println(result);
        return 0;
    }
    //CHECKSTYLE:ON
}
