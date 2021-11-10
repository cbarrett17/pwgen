import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class PWGen {
    public static void main(String[] args) {
        Options options;

        // Create a list of options
        options = createOptions();

        // Parse the options in args
        parseCMDOptions(options, args);
    }

    private static Options createOptions() {
        // Create new options
        Options options = new Options();

        // Add a help option
        options.addOption("h", "help", false, "Prints a list of available commands.");

        // Add a required length option
        options.addOption("n", "length", true, "Indicates the length to be output. There is no default length.");

        // Add a letter usage option
        options.addOption("a", "alpha", false, "Indicates the password must contain letters.");

        // Add a number usage option
        options.addOption("q", "number", false, "Indicates the password must contain numbers.");

        // Add a symbol usage option
        options.addOption("s", "symbol", false, "Indicates the password must contain symbols.");

        return options;
    }

    private static void parseCMDOptions(Options options, String[] args) {
        final int ERROR_EXIT_CODE = 2;
        int passLength;

        // Create a parser
        CommandLineParser parser = new DefaultParser();

        // Parse options passed in as CLA
        try {
            CommandLine cmd = parser.parse(options, args);

            // Check if help option is present or not
            if (cmd.hasOption("h")) {
                System.out.printf("Available commands:\n-s, --symbol: %s\n-n, --length: %s\n-a, --alpha:  %s\n-q, --number: %s",
                        options.getOption("s").getDescription(),
                        options.getOption("n").getDescription(),
                        options.getOption("a").getDescription(),
                        options.getOption("q").getDescription()
                );
            }
            else {
                // Check if length option is present
                if (cmd.hasOption("n")) {
                    passLength = Integer.parseInt(cmd.getOptionValue("length"));
                    if (passLength <= 0) {
                        throw new IllegalArgumentException();
                    }
                    System.out.println(passLength);
                }
                else {
                    throw new IllegalArgumentException();
                }

                // Check if letter usage option is present
                if (cmd.hasOption("a")) {
                    // Set some flag to true, the password will have letters
                    System.out.println("The password will be made of letters.");
                }

                // Check if number usage option is present
                if (cmd.hasOption("q")) {
                    // Set some flag to true, the password will have letters
                    System.out.println("The password will be made of numbers.");
                }

                // Check if the symbol usage option is present
                if (cmd.hasOption("s")) {
                    // Set some flag to true, the password will have letters
                    System.out.println("The password will be made of symbols.");
                }
            }
        }
        catch (org.apache.commons.cli.ParseException p) {
            System.exit(ERROR_EXIT_CODE);
        }
    }
    // Need a method to construct the passwords with the provided arguments

    // Need a method to return the completed password
}
