package nl.jqno.mutable.java;

import org.junit.Ignore;
import org.junit.Test;

public class LetsHaveSomeFunTest {
    @Test@Ignore("Can't confirm infinite loop due to halting problem. Too bad.")
    public void createAnInfiniteLoop() {
        Integer five = 5;
        Mutate.setInteger(five, 4);
        for (Integer i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void helloWorld() {
        Mutate.setString("hello", "world");

        System.out.print("hello ");
        System.out.println("hello");
    }
}
