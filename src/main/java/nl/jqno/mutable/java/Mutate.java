package nl.jqno.mutable.java;

import java.lang.reflect.Field;

public class Mutate {
    public static void setInteger(Integer receiver, int newValue) {
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(receiver, newValue);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("It didn't work, I'm sorry :(");
        }
    }
}
