public class Calculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Cannot divide by zero");
        return a / b;
    }

    public static double squareRoot(double a) {
        if (a < 0) throw new IllegalArgumentException("Cannot take square root of negative number");
        return Math.sqrt(a);
    }

    public static double inverse(double a) {
        if (a == 0) throw new IllegalArgumentException("Cannot divide by zero");
        return 1 / a;
    }

    public static double square(double a) {
        return a * a;
    }

    public static double changeSign(double a) {
        return -a;
    }


}
