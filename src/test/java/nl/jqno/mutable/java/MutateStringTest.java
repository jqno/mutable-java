package nl.jqno.mutable.java;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MutateStringTest {
    @Test
    public void changeValue() {
        byte[] original = "xxx".getBytes();

        Mutate.setString("xxx", "yyy");

        byte[] mutated = "xxx".getBytes();
        assertFalse(Arrays.equals(original, mutated));

        byte[] newValue = "yyy".getBytes();
        assertTrue(Arrays.equals(newValue, mutated));
    }
}
