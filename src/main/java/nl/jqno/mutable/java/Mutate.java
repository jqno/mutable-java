package nl.jqno.mutable.java;

import java.lang.reflect.Field;

public class Mutate {
    public static void setBoolean(Boolean receiver, boolean newValue) {
        setPrivateField(Boolean.class, "value", receiver, newValue);
    }

    public static void setByte(Byte receiver, byte newValue) {
        setPrivateField(Byte.class, "value", receiver, newValue);
    }

    public static void setChar(Character receiver, char newValue) {
        setPrivateField(Character.class, "value", receiver, newValue);
    }

    public static void setDouble(Double receiver, double newValue) {
        setPrivateField(Double.class, "value", receiver, newValue);
    }

    public static void setFloat(Float receiver, float newValue) {
        setPrivateField(Float.class, "value", receiver, newValue);
    }

    public static void setInteger(Integer receiver, int newValue) {
        setPrivateField(Integer.class, "value", receiver, newValue);
    }

    public static void setLong(Long receiver, long newValue) {
        setPrivateField(Long.class, "value", receiver, newValue);
    }

    public static void setShort(Short receiver, short newValue) {
        setPrivateField(Short.class, "value", receiver, newValue);
    }

    public static void setString(String receiver, String newValue) {
        try {
            Field f = getDeclaredField(String.class, "value");
            setPrivateField(String.class, "value", receiver, f.get(newValue));
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            itDidntWork(e);
        }
    }

    public static <T> void setPrivateField(Class<T> type, String fieldName, T receiver, Object newValue) {
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
