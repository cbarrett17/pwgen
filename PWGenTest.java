import static org.hamcrest.Matchers.*;
import org.junit.*;
import org.junit.rules.*;
import org.junit.contrib.java.lang.system.*;
import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void TestPWGen_1()
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
    public void TestPWGen_2()
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
    public void TestPWGen_3()
    {
        final String[] args = {"-a", "-n", "8"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();

            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher d = digit.matcher(printedResult);
            Matcher s = special.matcher(printedResult);

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
    public void TestPWGen_4()
    {
        final String[] args = {"-d", "-n", "8"};

        exit.checkAssertionAfterwards(() ->
        {
            String printedResult = stdout.getLog();

            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher l = letter.matcher(printedResult);
            Matcher s = special.matcher(printedResult);

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
    public void TestPWGen_5()
    {
        final String[] args = {"--symbol", "-n", "8"};

        PWGen.main(args);
    }

    @Test
    public void InvalidLengthTest_1() {
        final int invalidPassLengthExitCode = 2;
        final String[] args = {"-n", "-1"};

        exit.expectSystemExitWithStatus(invalidPassLengthExitCode);

        PWGen.main(args);
    }

    @Test
    public void InvalidInputTest_1() {
        final int invalidInputExitCode = 1;
        final String[] args = {"-a", "--hello"};

        exit.expectSystemExitWithStatus(invalidInputExitCode);

        PWGen.main(args);
    }

    @Test
    public void InvalidLengthInputTest_1() {
        final int invalidNumberInput = 3;
        final String[] args = {"-n", "--hello"};

        exit.expectSystemExitWithStatus(invalidNumberInput);

        PWGen.main(args);
    }
}