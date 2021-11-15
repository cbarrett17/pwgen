import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.List;

public class PWGen {
    private static final int INVALID_INPUT = 1;
    private static final int INVALID_LENGTH = 2;
    private static int length = 0;
    private static final String[] ALPHABET = {"A","B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a","b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static final String[] NUMERIC = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] SYMBOL = {"~", "!", "@", "#", "$","%", "^", "&", "*", "(", ")", "_", "+", "`", "-",
            "=", "{", "}", "[", "]", "|", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "\\", "?"};


    public static void main(String[] args) {
        Options options;

        // Create a list of options
        options = createOptions();

        // Parse the options in args, and set to possible chars list
        List<String> possibleChars = parseCMDOptions(options, args);

        // generate random password with possibleChars
        String password = generatePW(possibleChars);

        // TODO: IMPLEMENTATION
        // 1) - COMPLETED - Create sub array lists containing numbers, letters, symbols.
        // as well as empty possibleChars arrayList and empty password arrayList <- Daniel
        // 2) - COMPLETED - IN "parseCMDOptions", if an option exists, add sub array list to possible chars array list <- Caleb
        // 3) - COMPLETED - Create function to get random elements and add to pw <- Jamie
        // 4) Output of password <- Sam
    }

    private static Options createOptions() {
        // Create new options
        Options options = new Options();

        // All possible options added below
        options.addOption("h", "help", false, "Prints a list of available commands.");

        options.addOption("n", "length", true, "Indicates the length to be output. " +
            "There is no default length.");

        options.addOption("a", "alpha", false, "Indicates the password must contain letters.");

        options.addOption("d", "number", false, "Indicates the password must contain numbers.");

        options.addOption("s", "symbol", false, "Indicates the password must contain symbols.");

        return options;
    }

    private static List<String> parseCMDOptions(Options options, String[] args) {
        int passLength;
        List<String> possibleChars;

        // Create a parser
        CommandLineParser parser = new DefaultParser();

        // Create the list of available characters for the password
        possibleChars = new ArrayList<>();

        // Parse options passed in as CLA
        try {
            CommandLine cmd = parser.parse(options, args);

            // Check if help option is present or not
            if (cmd.hasOption("h")) {
                System.out.printf("Available commands:\n-s, --symbol: %s\n-n, --length: %s\n-a, --alpha:  %s\n-q, --number: %s",
                        options.getOption("s").getDescription(),
                        options.getOption("n").getDescription(),
                        options.getOption("a").getDescription(),
                        options.getOption("d").getDescription()
                );
            }
            else {
                // Any program run that doesn't include a help command must contain a length at the very least
                if (cmd.hasOption("n")) {
                    passLength = Integer.parseInt(cmd.getOptionValue("length"));
                    if (passLength <= 0) {
                        System.exit(INVALID_LENGTH);
                    }
                    System.out.println(passLength);

                    // update global variable length
                    length = passLength;
                }
                else {
                    System.exit(INVALID_INPUT);
                }

                // Check if any other option flags are present
                if (cmd.hasOption("a")) {
                    System.out.println("The password will be made of letters.");
                    possibleChars.addAll(List.of(ALPHABET));
                }

                if (cmd.hasOption("d")) {
                    System.out.println("The password will be made of numbers.");
                    possibleChars.addAll(List.of(NUMERIC));
                }

                if (cmd.hasOption("s")) {
                    System.out.println("The password will be made of symbols.");
                    possibleChars.addAll(List.of(SYMBOL));
                }
            }
        }
        catch (org.apache.commons.cli.ParseException p) {
            System.exit(INVALID_INPUT);
        }

        return possibleChars;
    }

    private static String generatePW(List<String> possibleChars) {
        // initialize empty password string builder
        StringBuilder password = new StringBuilder();

        // Append n chars from random indices of possibleChars to password
        for (int i = 0; i < length; i++) {
            int randomNum = (int)(Math.random() * possibleChars.size()); // [0, size)
            password.append(possibleChars.get(randomNum));
        }

        return password.toString();
    }
}
