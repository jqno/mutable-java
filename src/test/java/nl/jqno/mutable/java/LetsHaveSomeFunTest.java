package nl.jqno.mutable.java;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

        String hello1 = "hello ";
        String hello2 = "hello";
        String helloWorld = "hello world";

        assertEquals(helloWorld, hello1 + hello2);

        System.out.print("hello ");
        System.out.println("hello");
    }

    @Test
    public void callMeMaybe() {
        CallMe maybe = Mutate.createEnumConstant(CallMe.class, 2, "MAYBE");
        assertEquals("Call me maybe", maybe.call());
        System.out.println(maybe.call());
    }

    public enum CallMe {
        YES, NO;

        public String call() {
            return "Call me " + name().toLowerCase();
        }
    }
}
