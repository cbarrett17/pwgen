import org.junit.*;
import org.junit.rules.*;
import org.junit.contrib.java.lang.system.*;
import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that extensively tests PWGEN
 */
public class PWGenTest
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public final SystemOutRule stdout = new SystemOutRule().enableLog().mute();

    /**
     * Verify commands are displayed when -h is provided.
     */
    @Test
    public void CommandOptionsTest_1()
    {
        final String[] args = {"-h"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();
            String expectedResult = String.format("Available commands:\n-n, --length: %s\n-s, --symbol: %s\n-a, --alpha:  %s\n-d, --number: %s",
                    "Indicates the length to be output. " +
                            "There is no default length.",
                    "Indicates the password must contain symbols.",
                    "Indicates the password must contain letters.",
                    "Indicates the password must contain numbers.");
            Assert.assertEquals(expectedResult, printedResult);
        });

        PWGen.main(args);
    }

    /**
     * Verify the length and that all possible characters are added when only -n is provided.
     */
    @Test
    public void CommandOptionsTest_2()
    {
        final String[] args = {"-n", "5"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();
            int printedLength = printedResult.length();
            int expectedLength = 5;

            assertEquals(expectedLength, printedLength);
        });

        PWGen.main(args);
    }

    /**
     * Verify only letters are added when -a and -n is provided.
     */
    @Test
    public void CommandOptionsTest_3()
    {
        final String[] args = {"-a", "-n", "8"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();

            Pattern digit = Pattern.compile("[0-9]");
            Pattern symbol = Pattern.compile ("[!@#$%&*()_+=|<>?{}.,;:`'/\"\\[\\]~-]");

            Matcher d = digit.matcher(printedResult);
            Matcher s = symbol.matcher(printedResult);

            boolean containsDigit = d.find();
            boolean containsSymbol = s.find();

            Assert.assertFalse(containsDigit || containsSymbol);
        });

        PWGen.main(args);
    }

    /**
     * Verify only numbers are added when -d and -n is provided.
     */
    @Test
    public void CommandOptionsTest_4()
    {
        final String[] args = {"-d", "-n", "8"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();

            Pattern letter = Pattern.compile("[a-zA-Z]");
            Pattern symbol = Pattern.compile ("[!@#$%&*()_+=|<>?{}.,;:`'/\"\\[\\]~-]");

            Matcher l = letter.matcher(printedResult);
            Matcher s = symbol.matcher(printedResult);

            boolean containsLetter = l.find();
            boolean containsSymbol = s.find();

            Assert.assertFalse(containsLetter || containsSymbol);

        });

        PWGen.main(args);
    }

    /**
     * Verify only symbols are added when -s and -n is provided.
     */
    @Test
    public void CommandOptionsTest_5()
    {
        final String[] args = {"-s", "-n", "8"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();

            Pattern letter = Pattern.compile("[a-zA-Z]");
            Pattern digit = Pattern.compile("[0-9]");

            Matcher l = letter.matcher(printedResult);
            Matcher d = digit.matcher(printedResult);

            boolean containsLetter = l.find();
            boolean containsDigit = d.find();

            Assert.assertFalse(containsLetter || containsDigit);
        });

        PWGen.main(args);
    }

    /**
     * Verify only letters and digits are added when -a, -s and -n is provided.
     */
    @Test
    public void CommandOptionsTest_6()
    {
        final String[] args = {"-a", "-d", "-n", "8"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();

            Pattern symbol = Pattern.compile ("[!@#$%&*()_+=|<>?{}.,;:`'/\"\\[\\]~-]");

            Matcher s = symbol.matcher(printedResult);

            boolean containsSymbol = s.find();

            Assert.assertFalse(containsSymbol);
        });

        PWGen.main(args);
    }

    /**
     * Verify only letters and symbols are added when -a, -s and -n is provided.
     */
    @Test
    public void CommandOptionsTest_7()
    {
        final String[] args = {"-a", "-s", "-n", "8"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();

            Pattern digit = Pattern.compile("[0-9]");

            Matcher d = digit.matcher(printedResult);

            boolean containsDigit = d.find();

            Assert.assertFalse(containsDigit);
        });

        PWGen.main(args);
    }

    /**
     * Verify only digits and symbols are added when -d, -s and -n is provided.
     */
    @Test
    public void CommandOptionsTest_8()
    {
        final String[] args = {"-d", "-s", "-n", "8"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();

            Pattern letter = Pattern.compile("[a-zA-Z]");

            Matcher l = letter.matcher(printedResult);

            boolean containsLetter = l.find();

            Assert.assertFalse(containsLetter);
        });

        PWGen.main(args);
    }

    /**
     * Verify that, given an invalid password length, the system exits with the
     * invalidPassLengthExitCode exit code.
     */
    @Test
    public void InvalidLengthTest_1() {
        final int invalidPassLengthExitCode = 2;
        final String[] args = {"-n", "-1"};

        exit.expectSystemExitWithStatus(invalidPassLengthExitCode);

        PWGen.main(args);
    }

    /**
     * Verify that, given an invalid input, the system exits with the
     * invalidInputExitCode exit code.
     */
    @Test
    public void InvalidInputTest_1() {
        final int invalidInputExitCode = 1;
        final String[] args = {"-a", "--hello"};

        exit.expectSystemExitWithStatus(invalidInputExitCode);

        PWGen.main(args);
    }

    /**
     * Verify that, given an invalid number input, the system exits with the
     * invalidNumberInput exit code.
     */
    @Test
    public void InvalidLengthInputTest_1() {
        final int invalidNumberInput = 3;
        final String[] args = {"-n", "--hello"};

        exit.expectSystemExitWithStatus(invalidNumberInput);

        PWGen.main(args);
    }
}