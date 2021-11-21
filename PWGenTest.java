import static org.hamcrest.Matchers.*;
import org.junit.*;
import org.junit.rules.*;
import org.junit.contrib.java.lang.system.*;
import static org.junit.Assert.*;

public class PWGenTest
{
    /**
     * Verify commands are displayed when -h is provided.
     */
    @Test
    public void TestPWGen_1()
    {
        final String[] args = {"-h"};

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