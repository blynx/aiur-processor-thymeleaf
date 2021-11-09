package AiurProcessorThymeleaf;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;


/**
 * TODO: Create custom templateresolver which accepts template strings but is configurable
 *
 */

@Command(
        name = "thymeleaf",
        description = "Little Thymeleaf command line processor."
)
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "Path to file.")
    private String source;

    @Option(names={"-p", "--prefix"}, description="TemplateResolver prefix.")
    private String prefix;

    // @Option(names = {"-l", "--layout"}, description = "Use popular Layout Dialect." )
    // private boolean useLayoutDialect;

    @Option(names = {"-s", "--suffix"}, description = "TemplateResolver suffix.")
    private String suffix;

    // @Option(names = {"-m", "--mode"}, description = "TemplateResolver mode.")
    // private String mode;

    @Option(names = {"-d", "--data"}, description = "JSON data for context.")
    private String data;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    public Integer call() {
        AiurProcessorThymeleaf thl = new AiurProcessorThymeleaf(prefix);
        System.out.println(thl.processFile(source, data));
        return 0;
    }
}
