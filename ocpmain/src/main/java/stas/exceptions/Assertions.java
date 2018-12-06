package stas.exceptions;

enum Seasons {
    SPRING, SUMMER, FALL, /*  other developer added value WINTER to our enum */WINTER
}

public class Assertions {
    public static void test(Seasons s) {
        switch (s) {
            case SPRING:
            case FALL:
                System.out.println("Shorter hours");
                break;
            case SUMMER:
                System.out.println("Longer hours");
                break;
            default:
                assert false : "Invalid season";
		
		/*Because the value of s on line 3 can only be SPRING,  SUMMER, or FALL , and the switch
		statement has a case for all three of these outcomes, we can assert that line 12 is not reach-
		able.
		The assertion would fail silently, since you�d have assertions off in production.*/
		
		/*The only way this assertion will fail is if somehow the enum is modified. Suppose that
		you are working on a project that uses the Seasons enum, and the zoo decides to start
		opening in the winter. The assertion can help uncover the ripple effect of such a change.*/
        }
    }

    public static void main(String[] args) {
        test(Seasons.WINTER); //fails at runtime with error IF ASSERTION ARE ENABLED
	/*	Exception in thread "main" java.lang.AssertionError: Invalid season
		at TestSeason.main(Test.java:12)
		at TestSeason.main(Test.java:18)*/

    }
}

class RectangleClassInvariant {

    private int width, height;

    public RectangleClassInvariant(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        assert isValid() : "Not a valid Rectangle"; // assertion checks the state of the object
        return width * height;
    }

    private boolean isValid() {
        return (width >= 0 && height >= 0);
    }

    public static void main(String[] args) {
        RectangleClassInvariant one = new RectangleClassInvariant(5, 12);
        RectangleClassInvariant two = new RectangleClassInvariant(-4, 10);

        System.out.println("Area one = " + one.getArea());
        System.out.println("Area two = " + two.getArea());
    }
}

