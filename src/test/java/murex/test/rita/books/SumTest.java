package murex.test.rita.books;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by RCPopescu on 8/9/2016.
 */
public class SumTest {

    @Test
    public void testAdd() throws Exception {
        Sum s = new Sum();
        int sumResult = s.add(10, 20);
        assertEquals("The sum is not what was expected.", 30, sumResult);
    }
}