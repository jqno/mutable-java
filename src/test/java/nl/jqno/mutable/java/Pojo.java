package nl.jqno.mutable.java;

import java.util.Objects;

public class Pojo {
    private final int someInt;
    private final String someString;

    public Pojo(int someInt, String someString) {
        this.someInt = someInt;
        this.someString = someString;
    }

    public int getSomeInt() {
        return someInt;
    }

    public String getSomeString() {
        return someString;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pojo)) {
            return false;
        }
        Pojo other = (Pojo)obj;
        return someInt == other.someInt && Objects.equals(someString, other.someString);
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int result = prime;
        result = (result * prime) + someInt;
        result = (result * prime) + Objects.hashCode(someString);
        return result;
    }
}
