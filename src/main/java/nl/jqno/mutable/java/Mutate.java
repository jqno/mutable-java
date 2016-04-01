package nl.jqno.mutable.java;

import java.lang.reflect.Field;

public class Mutate {
    public static void setInteger(Integer receiver, int newValue) {
        set(Integer.class, "value", receiver, newValue);
    }

    public static void setString(String receiver, String newValue) {
        try {
            Field f = getDeclaredField(String.class, "value");
            set(String.class, "value", receiver, f.get(newValue));
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            itDidntWork(e);
        }
    }

    private static <T> void set(Class<T> type, String fieldName, T receiver, Object newValue) {
        try {
            Field field = getDeclaredField(type, fieldName);
            field.set(receiver, newValue);
        }
        catch (Exception e) {
            itDidntWork(e);
        }
    }

    private static <T> Field getDeclaredField(Class<T> type, String fieldName) throws NoSuchFieldException {
        Field field = type.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    private static void itDidntWork(Exception e) {
        throw new RuntimeException("It didn't work, I'm sorry :(", e);
    }
}
