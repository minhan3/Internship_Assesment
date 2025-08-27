/*
 * Problem 7 - Square Root of a Perfect Square (without using built-in functions)
 *
 * Language Justification:
 * Java is ideal for this problem because it supports basic loops and integer operations cleanly.
 * It allows full control over how numbers are compared, and since we are not using any built-in 
 * square root function, Java’s manual iteration approach is clear and efficient for this case.

 * Time Complexity:
 * - O(√x), which means the time depends on the square root of x.
 * - This is because we check one number at a time from 0 up to the square root of x.
 *
 * Space Complexity:
 * - O(1), which means we only use a fixed amount of memory.
 * - We don’t use any big data structures, just a few simple variables.
 */

public class P7 {
    
    // Function to find the square root of a perfect square number
    public static int findSquareRoot(int x) {
        // Try numbers from 0 up to x
        for (int i = 0; i <= x; i++) {
            // If i * i equals x, we've found the square root
            if (i * i == x) {
                return i;
            }
        }
        // If no perfect square root is found (should not happen in valid input)
        return -1;
    }

    // Example usage
    public static void main(String[] args) {
        int x = 25;
        int result = findSquareRoot(x);
        System.out.println("Square root of " + x + " is: " + result);
    }
}
