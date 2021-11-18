import static org.hamcrest.Matchers.*;
import org.junit.*;
import org.junit.rules.*;
import org.junit.contrib.java.lang.system.*;
import static org.junit.Assert.*;



public class PWGenTest
{
    @Test
    public void test_test()
    {
        final String[] args = {"-n", "8"};

        PWGen.main(args);
    }


}