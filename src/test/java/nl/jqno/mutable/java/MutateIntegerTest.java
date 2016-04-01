package nl.jqno.mutable.java;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutateIntegerTest {
    @Test
    public void twoIsOne() {
        Mutate.setInteger(2, 1);
        assertEquals(Integer.valueOf(1), Integer.valueOf(2));
    }

    @Test@Ignore("Can't confirm infinite loop due to halting problem. Too bad.")
    public void createAnInfiniteLoop() {
        Integer five = 5;
        Mutate.setInteger(five, 4);
        for (Integer i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
