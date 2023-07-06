package package2;

import package1.MathOperations;

public class Main {
    public static void main(String[] args) {
        int result = MathOperations.add(2, 2);
        System.out.println("Addition: " + result);
        result = MathOperations.sub(5, 3);
        System.out.println("Subtraction: " + result);
        result = MathOperations.mul(2, 4);
        System.out.println("Multiplication: " + result);
        result = MathOperations.div(10, 2);
        System.out.println("Division: " + result);
        ;
    }
}
