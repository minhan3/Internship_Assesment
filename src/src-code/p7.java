/*
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
        for (int i = 0; i <= x; i++) {
            if (i * i == x) {
                return i;
            }
        }
        return -1; // should not happen if x is a perfect square
    }

    // Example usage
    public static void main(String[] args) {
        int x = 25;
        int result = findSquareRoot(x);
        System.out.println("Square root of " + x + " is: " + result);
    }
}
