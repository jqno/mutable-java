Do you think immutability is too constraining? Do you agree that there's a _good reason_ why variables in Java aren't final by default? Do you think that reason is called "performance"? Or, maybe you're coming from a dynamic language where "private fields" just aren't a thing? Do you simply want to stop fussing over minor details and get the friggin' _job done_? **Mutable Java** has your back!

Features
===
Mutate private fields
---
Let's say you have one of those annoying immutable Pojos with some private fields, but no setters.

    class Point {
        private final int x;
        private final int y;

        // Constructor, getters, equals, hashCode, etc elided for brevity.
        // There are no setters.
    }

You can easily change a field:

    Point p = new Point(1, 2);
    System.out.println(p.getX());  // prints 1
    Mutate.setPrivateField(Point.class, "x", p, 42);
    System.out.println(p.getX());  // prints 42

Mutate Strings
---
In Ruby, you can mutate Strings in place. Wouldn't it be nice if you could do that in Java too? Well, now you can!

    System.out.print("hello");
    Mutate.setString("hello", " world");
    System.out.println("hello");
    // prints 'hello world'

Mutate boxed primitives
---
But why stop with Strings? Consider this:

    Mutate.setInteger(5, 4);
    for (Integer i = 0; i < 10; i++) {
        System.out.println(i);
    }

This prints 0, 1, 2, 3, 4, 4, 4, 4, 4, 4... Because after 4 comes 5, but 5 is 4, after which comes 5 again which is 4, and presto: you have an infinite loop!

Of course, there's a method like this for each of Java's primitive classes.

Mutate enums
---
Josh Bloch says, in Effective Java's 2nd edition, that enums are the best way to implement the Singleton design pattern, because the JVM will ensure there can never be two instances of the same enum instance. Well, everybody knows the Singleton pattern is stupid anyway, so here's a way around that!

    enum MySingleton { INSTANCE }
    //
    MySingleton noLongerASingleton = Mutate.copyEnumConstant(MySingleton.class, MySingleton.INSTANCE);
    System.out.println(MySingleton.INSTANCE);  // prints INSTANCE
    System.out.println(noLongerASingleton);    // prints INSTANCE

Obviously, it's also a great oversight by Java's developers that you can't add elements to an enum at runtime. Fortunately, now you can!

    enum Suit { HEARTS, CLUBS, DIAMONDS, SPADES }
    // ...
    Mutate.addEnumConstant(Suit.class, "SWORDS");
    for (Suit s : Suit.values()) {
        System.out.println(s);
    }
    // prints HEARTS, CLUBS, DIAMONDS, SPADES, SWORDS

Bonus features
===
Generate String with same hashCode
---
You have a String, and you want a different String with the same hashCode? No problem!

    String first = "hello world";
    String second = Mutate.stringWithSameHashCode(first);
    System.out.println(first + " " + first.hashCode());    // prints 'helloworld -1524582912'
    System.out.println(second + " " + second.hashCode());  // prints 'iFmMpXpSmE -1524582912'

Instantiate Void
---
Ever wanted to return a Void? Why not?

    public Void theCallOfTheVoid() {
        return Mutate.newVoid();
    }

Have a void on me!

Disclaimer
===
This code was tested on Oracle JDK 1.8 and may or may not work on other JDKs and running it will mess up your running JVM instance so it may not be smart to use it in production. Or at all.
