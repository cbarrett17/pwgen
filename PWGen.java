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

        // TODO: IMPLEMENTATION
        // 1) Create sub array lists containing numbers, letters, symbols.
        // as well as empty possibleChars arrayList and empty password arrayList <- Daniel
        // 2) IN "parseCMDOptions", if an option exists, add sub array list to possible chars array list <- Caleb
        // 3) Create function to get random elements and add to pw <- Jamie
        // 4) Output of password <- Sam
    }

    private static Options createOptions() {
        // Create new options
        Options options = new Options();

        // All possible options added below
        options.addOption("h", "help", false, "Prints a list of available commands.");

        options.addOption("n", "length", true, "Indicates the length to be output. There is no default length.");

        options.addOption("a", "alpha", false, "Indicates the password must contain letters.");

        options.addOption("q", "number", false, "Indicates the password must contain numbers.");

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
                // Any program run that doesn't include a help command must contain a length at the very least
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

                // Check if any other option flags are present
                // TODO: point the result of these options to the corresponding array or method
                if (cmd.hasOption("a")) {
                    System.out.println("The password will be made of letters.");
                }

                if (cmd.hasOption("q")) {
                    System.out.println("The password will be made of numbers.");
                }

                if (cmd.hasOption("s")) {
                    System.out.println("The password will be made of symbols.");
                }
            }
        }
        catch (org.apache.commons.cli.ParseException p) {
            System.exit(ERROR_EXIT_CODE);
        }
    }
}
