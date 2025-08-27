import java.util.Scanner;

   /**
     * Problem 3 - Fibonacci Sequence (Recursive)
     * Given a number n, generate the first n Fibonacci numbers using recursion.
     *
     * Language Choice Reasoning:
     * Java is chosen because it supports both recursion and user input easily.
     * The recursive method helps demonstrate how Fibonacci values build up from base cases.
     * Java’s strong type system ensures safe and predictable recursion depth handling.
     */
    
public class P3 {
    
    // Recursive Fibonacci function
    public static int fibonacci(int n) {
        // Base case: 0th number is 0
        if (n == 0) return 0;

        // Base case: 1st number is 1
        if (n == 1) return 1;

        // Recursive case: sum of previous two Fibonacci numbers
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Generate sequence up to 'count' elements
    public static String generateFibonacci(int count) {
        String result = "";

        // Build the sequence one number at a time
        for (int i = 0; i < count; i++) {
            result += fibonacci(i);  // call recursive function
            if (i < count - 1) result += " "; // add space between numbers
        }

        return result;
    }

    // Main method (user input)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user how many numbers to generate
        System.out.print("Enter how many Fibonacci numbers to generate: ");
        int count = sc.nextInt();

        // Output the generated sequence
        System.out.println("Fibonacci sequence: ");
        System.out.println(generateFibonacci(count));
        
        sc.close();
    }
}
