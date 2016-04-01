package nl.jqno.mutable.java;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Immutability is too constraining? MutableJava has your back!
 */
public class Mutate {
    private static final Objenesis OBJENESIS = new ObjenesisStd();

    /**
     * Assigns TRUE to FALSE and FALSE to TRUE.
     */
    public static void setBoolean(Boolean receiver, boolean newValue) {
        setPrivateField(Boolean.class, "value", receiver, newValue);
    }

    /**
     * Assigns a new byte value to an existing Byte object.
     */
    public static void setByte(Byte receiver, byte newValue) {
        setPrivateField(Byte.class, "value", receiver, newValue);
    }

    /**
     * Assigns a new char value to an existing Character object.
     */
    public static void setChar(Character receiver, char newValue) {
        setPrivateField(Character.class, "value", receiver, newValue);
    }

    /**
     * Assigns a new double value to an existing Double object.
     */
    public static void setDouble(Double receiver, double newValue) {
        setPrivateField(Double.class, "value", receiver, newValue);
    }

    /**
     * Assigns a new float value to an existing Float object.
     */
    public static void setFloat(Float receiver, float newValue) {
        setPrivateField(Float.class, "value", receiver, newValue);
    }

    /**
     * Assigns a new int value to an existing Integer object.
     */
    public static void setInteger(Integer receiver, int newValue) {
        setPrivateField(Integer.class, "value", receiver, newValue);
    }

    /**
     * Assigns a new long value to an existing Long object.
     */
    public static void setLong(Long receiver, long newValue) {
        setPrivateField(Long.class, "value", receiver, newValue);
    }

    /**
     * Assigns a new short value to an existing Short object.
     */
    public static void setShort(Short receiver, short newValue) {
        setPrivateField(Short.class, "value", receiver, newValue);
    }

    /**
     * Assigns a new String value to an existing (and possibly interned) String object.
     */
    public static void setString(String receiver, String newValue) {
        try {
            Field f = getDeclaredField(String.class, "value");
            setPrivateField(String.class, "value", receiver, f.get(newValue));
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            itDidntWork(e);
        }
    }

    /**
     * Generates a different String with the same hashCode as the original.
     *
     * Adapted from http://stackoverflow.com/a/12926356/127863
     */
    public static String stringWithSameHashCode(String original) {
        int level = 1;
        int length = original.length();
        if (length < 2) {
            return original;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length / 2; i++) {
            char c0 = original.charAt(i * 2);
            char c1 = original.charAt(i * 2 + 1);
            c0 = (char)(c0 + level);
            c1 = (char)(c1 - 31 * level);
            sb.append(c0);
            sb.append(c1);
        }
        if (length % 2 == 1) {
            sb.append(original.charAt(length - 1));
        }
        return sb.toString();
    }

    /**
     * Makes a copy of the given enum constant.
     *
     * Its name and ordinal will be identical, but it won't replace the original
     * instance in the enum's values array.
     */
    public static <E extends Enum<?>> E copyEnumConstant(Class<E> type, E constant) {
        E newInstance = OBJENESIS.newInstance(type);
        setPrivateField(Enum.class, "ordinal", newInstance, constant.ordinal());
        setPrivateField(Enum.class, "name", newInstance, constant.name());
        return newInstance;
    }

    /**
     * Adds an enum constant to the given enum.
     *
     * It has the given name, its ordinal will be the ordinal of the last enum constant, plus 1.
     *
     * Not thread-safe.
     */
    public static <E extends Enum<?>> void addEnumConstant(Class<E> type, String constantName) {
        try {
            Method method = type.getDeclaredMethod("values");
            Enum[] values = (Enum[])method.invoke(type);
            int ordinal = values.length;

            E newInstance = OBJENESIS.newInstance(type);
            setPrivateField(Enum.class, "ordinal", newInstance, ordinal);
            setPrivateField(Enum.class, "name", newInstance, constantName);

            Enum[] newValues = (Enum[])Array.newInstance(type, ordinal + 1);
            System.arraycopy(values, 0, newValues, 0, ordinal);
            newValues[ordinal] = newInstance;

            Field valuesField = getDeclaredField(type, "$VALUES");
            Field modifiersField = getDeclaredField(Field.class, "modifiers");
            modifiersField.setInt(valuesField, valuesField.getModifiers() & ~Modifier.FINAL);

            valuesField.set(null, newValues);
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchFieldException | NoSuchMethodException e) {
            itDidntWork(e);
        }
    }

    /**
     * Returns an instance of Void.
     */
    public static Void newVoid() {
        return OBJENESIS.newInstance(Void.class);
    }

    /**
     * Modifies a private field in the given receiver object.
     */
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
