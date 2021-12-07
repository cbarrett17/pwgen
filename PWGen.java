import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * A simple Command Line Argument program that accepts user flags and outputs a password
 * corresponding to the user's needs.
 *
 * @author cbarrett17@georgefox.edu
 * @author joconnell19@georgefox.edu
 * @author dpaxton18@georgefox.edu
 * @author slorenz19@georgefox.edu
 */
public class PWGen {
    private static final int INVALID_INPUT = 1;
    private static final int INVALID_PASSWORD_LENGTH = 2;
    private static final int LENGTH_NUMBER_FORMAT_ERROR = 3;

    private static final String[] ALPHABET = {"A","B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a","b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static final String[] NUMERIC = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] SYMBOL = {"~", "!", "@", "#", "$","%", "^", "&", "*", "(", ")", "_", "+", "`", "-",
            "=", "{", "}", "[", "]", "|", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "\\", "?"};

    private static int _length = 0;

    /**
     * The main method of the class that runs the methods provided below.
     * @param args The arguments provided by the user
     */
    public static void main(String[] args) {
        // determine the locale
        Locale locale = Locale.getDefault();
        // load localized string resources
        ResourceBundle strings = ResourceBundle.getBundle("PWGEN", locale);

        Options options;
        List<String> possibleChars;
        String password;

        // Create a list of options
        options = createOptions(strings);

        // Parse the options in args, and set to possible chars list
        possibleChars = parseCMDOptions(options, args, strings);

        // generate random password with possibleChars
        password = generatePW(possibleChars);

        // Print password to standard out
        System.out.print(password);
    }

    /**
     * Creates options that the user can use to determine what characters will be used in the password.
     * @return The set of options
     */
    private static Options createOptions(ResourceBundle strings) {
        // get localized strings
        String helpOpt = strings.getString("helpOpt");
        String lenOpt = strings.getString("lenOpt");
        String alphOpt = strings.getString("alphOpt");
        String numOpt = strings.getString("numOpt");
        String symOpt = strings.getString("symOpt");

        // Create new options
        Options options = new Options();

        // All possible options added below
        options.addOption("h", "help", false, helpOpt);

        options.addOption("n", "length", true, lenOpt +
            "There is no default length.");

        options.addOption("a", "alpha", false, alphOpt);

        options.addOption("d", "number", false, numOpt);

        options.addOption("s", "symbol", false, symOpt);

        return options;
    }

    /**
     * Allows the parsing of the options provided by the user based on the previously constructed option set.
     * @param options The set of options
     * @param args The arguments provided by the user
     * @return The list of available characters from which the password can be built.
     */
    private static List<String> parseCMDOptions(Options options, String[] args, ResourceBundle strings) {
        int passLength;
        List<String> possibleChars;

        // get help mes
        String helpMes = strings.getString("helpMes");

        // Create a parser
        CommandLineParser parser = new DefaultParser();

        // Create the list of available characters for the password
        possibleChars = new ArrayList<>();

        // Parse options passed in as CLA
        try {
            CommandLine cmd = parser.parse(options, args);

            // Check if help option is present or not
            if (cmd.hasOption("h")) {
                System.out.printf(helpMes + "\n-n, --length: %s\n-s, --symbol: %s\n-a, --alpha:  %s\n-d, --number: %s",
                        options.getOption("n").getDescription(),
                        options.getOption("s").getDescription(),
                        options.getOption("a").getDescription(),
                        options.getOption("d").getDescription()
                );
            }
            else {
                // Any program run that doesn't include a help command must contain a length at the very least
                if (cmd.hasOption("n")) {
                    passLength = Integer.parseInt(cmd.getOptionValue("length"));
                    if (passLength <= 0) {
                        System.exit(INVALID_PASSWORD_LENGTH);
                    }

                    // update global variable length
                    _length = passLength;
                }
                else {
                    System.exit(INVALID_INPUT);
                }

                // Check if any other option flags are present
                if (cmd.hasOption("a")) {
                    possibleChars.addAll(List.of(ALPHABET));
                }

                if (cmd.hasOption("d")) {
                    possibleChars.addAll(List.of(NUMERIC));
                }

                if (cmd.hasOption("s")) {
                    possibleChars.addAll(List.of(SYMBOL));
                }
                // If no other optional requirement is given than the length, all possible characters are added
                if (!cmd.hasOption("a") && !cmd.hasOption("d") && !cmd.hasOption("s")) {
                    possibleChars.addAll(List.of(ALPHABET));
                    possibleChars.addAll(List.of(NUMERIC));
                    possibleChars.addAll(List.of(SYMBOL));
                }
            }
        }
        catch (org.apache.commons.cli.ParseException p) {
            System.exit(INVALID_INPUT);
        }
        catch (NumberFormatException n) {
            System.exit(LENGTH_NUMBER_FORMAT_ERROR);
        }

        return possibleChars;
    }

    /**
     * Calls a random accessor to choose characters from the provided array to add to the password.
     * @param possibleChars All available characters to choose from
     * @return The completed password
     */
    private static String generatePW(List<String> possibleChars) {
        // initialize empty password string builder
        StringBuilder password = new StringBuilder();

        // Append n chars from random indices of possibleChars to password
        for (int i = 0; i < _length; i++) {
            int randomNum = (int)(Math.random() * possibleChars.size()); // [0, size)
            password.append(possibleChars.get(randomNum));
        }
        return password.toString();
    }
}
