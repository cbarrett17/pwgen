import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class PWGen {
    public static void main(String[] args) {
        // Create new options
        Options options = new Options();

        // Add a help option
        options.addOption("h", "help", false, "Prints a list of available commands.");

        // Add a required length option
        options.addOption("n", "length", true, "Denotes the length to be output");

        // Add a letter usage option
        options.addOption("a", "alpha", false, "Indicates the password must contain alphabetical characters.");

        // Create a parser
        CommandLineParser parser = new DefaultParser();

        // Parse options passed in as CLA
        try {
            CommandLine cmd = parser.parse(options, args);

            // Check if option is present or not
            if (cmd.hasOption("h") || cmd.hasOption("help")) {
                System.out.println("Help Section goes here!");
            }
            else if (cmd.hasOption("n") || cmd.hasOption("length")) {
                System.out.println(cmd.getOptionValue("length"));
            }
            else if (cmd.hasOption("a") || cmd.hasOption("alpha")) {
                System.out.println("The password will be made of letters");
            }
        }
        catch (org.apache.commons.cli.ParseException p) {
            System.exit(2);
        }
    }
}
