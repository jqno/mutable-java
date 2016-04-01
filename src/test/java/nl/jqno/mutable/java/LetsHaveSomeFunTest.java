package nl.jqno.mutable.java;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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
    public void noMoreSingleton() {
        Singleton anotherInstance = Mutate.copyEnumConstant(Singleton.class, Singleton.INSTANCE);

        assertNotSame(Singleton.INSTANCE, anotherInstance);
        assertEquals(Singleton.INSTANCE.returnSomethingSingletonny(), anotherInstance.returnSomethingSingletonny());
    }

    public enum Singleton {
        INSTANCE;

        public String returnSomethingSingletonny() {
            return "this is a singleton!";
        }
    }

    @Test
    public void confusingPokerGame() {
        Mutate.addEnumConstant(Suit.class, "SWORDS");

        assertEquals("HEARTS", Suit.values()[0].name());
        assertEquals("CLUBS", Suit.values()[1].name());
        assertEquals("DIAMONDS", Suit.values()[2].name());
        assertEquals("SPADES", Suit.values()[3].name());

        assertEquals("SWORDS", Suit.values()[4].name());
    }

    public enum Suit {
        HEARTS, CLUBS, DIAMONDS, SPADES
    }
}
