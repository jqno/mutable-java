package nl.jqno.mutable.java;

import java.lang.reflect.Field;

public class Mutate {
    public static void setBoolean(Boolean receiver, boolean newValue) {
        set(Boolean.class, "value", receiver, newValue);
    }

    public static void setByte(Byte receiver, byte newValue) {
        set(Byte.class, "value", receiver, newValue);
    }

    public static void setChar(Character receiver, char newValue) {
        set(Character.class, "value", receiver, newValue);
    }

    public static void setDouble(Double receiver, double newValue) {
        set(Double.class, "value", receiver, newValue);
    }

    public static void setFloat(Float receiver, float newValue) {
        set(Float.class, "value", receiver, newValue);
    }

    public static void setInteger(Integer receiver, int newValue) {
        set(Integer.class, "value", receiver, newValue);
    }

    public static void setLong(Long receiver, long newValue) {
        set(Long.class, "value", receiver, newValue);
    }

    public static void setShort(Short receiver, short newValue) {
        set(Short.class, "value", receiver, newValue);
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
