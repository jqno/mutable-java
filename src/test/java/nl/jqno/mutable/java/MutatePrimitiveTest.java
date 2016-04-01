package nl.jqno.mutable.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutatePrimitiveTest {
    @Test
    public void mutateBoolean() {
        Mutate.setBoolean(true, false);
        assertEquals(Boolean.FALSE, Boolean.TRUE);
    }

    @Test
    public void mutateByte() {
        Mutate.setByte((byte)2, (byte)1);
        assertEquals(Byte.valueOf((byte)2), Byte.valueOf((byte)1));
    }

    @Test
    public void mutateChar() {
        Mutate.setChar('b', 'a');
        assertEquals(Character.valueOf('a'), Character.valueOf('b'));
    }

    @Test
    public void mutateDouble() {
        Double d = 2.0D;
        Mutate.setDouble(d, 1.0D);
        assertEquals(Double.valueOf(1.0D), d);
    }

    @Test
    public void mutateFloat() {
        Float f = 2.0F;
        Mutate.setFloat(f, 1.0F);
        assertEquals(Float.valueOf(1.0F), f);
    }

    @Test
    public void mutateInteger() {
        Mutate.setInteger(2, 1);
        assertEquals(Integer.valueOf(1), Integer.valueOf(2));
    }

    @Test
    public void mutateLong() {
        Mutate.setLong(2L, 1L);
        assertEquals(Long.valueOf(1), Long.valueOf(2));
    }

    @Test
    public void mutateShort() {
        Mutate.setShort((short)2, (short)1);
        assertEquals(Short.valueOf((short)1), Short.valueOf((short)2));
    }
}
