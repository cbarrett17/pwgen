import static org.hamcrest.Matchers.*;
import org.junit.*;
import org.junit.rules.*;
import org.junit.contrib.java.lang.system.*;
import static org.junit.Assert.*;

public class PWGenTest
{
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

        exit.checkAssertionAfterwards(new Assertion()
        {
            public void checkAssertion()
            {
                String printedResult = stdout.getLog();
                String expectedResult = String.format("Available commands:\n-n, --length: %s\n-s, --symbol: %s\n-a, --alpha:  %s\n-d, --number: %s",
                        "Indicates the length to be output. " +
                                "There is no default length.",
                        "Indicates the password must contain symbols.",
                        "Indicates the password must contain letters.",
                        "Indicates the password must contain numbers.");
                Assert.assertEquals(expectedResult, printedResult);
            }
        });

        PWGen.main(args);
    }

    /**
     * Verify the length and that all possible characters are added when only -n is provided.
     */
    @Test
    public void TestPWGen_2()
    {
        final String[] args = {"-n", "8"};

        PWGen.main(args);
    }

    /**
     * Verify only letters are added when -a and -n is provided.
     */
    @Test
    public void TestPWGen_3()
    {
        final String[] args = {"-a", "-n", "8"};

        PWGen.main(args);
    }

    /**
     * Verify only numbers are added when -d and -n is provided.
     */
    @Test
    public void TestPWGen_4()
    {
        final String[] args = {"-d", "-n", "8"};

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
}