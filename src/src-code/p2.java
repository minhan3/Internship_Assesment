    /**
     * Problem 2 - FizzBuzz
     * Output numbers from start to end:
     * - Replace multiples of 3 with "Fizz"
     * - Replace multiples of 5 with "Buzz"
     * - Replace multiples of both 3 and 5 with "FizzBuzz"
     *
     * Language Choice Reasoning:
     * Java is chosen because of its simple conditional structure and string handling,
     * making it suitable for writing logic like FizzBuzz cleanly and with clear output formatting.
     */

public class P2 {
    
    // Generate FizzBuzz sequence from start to end
    public static String generateFizzBuzz(int start, int end) {
        String result = "";

        // Loop through all numbers from start to end
        for (int i = start; i <= end; i++) {

            // Check for FizzBuzz condition first
            if (i % 3 == 0 && i % 5 == 0) {
                result += "FizzBuzz";
            }
            // Check for Fizz only
            else if (i % 3 == 0) {
                result += "Fizz";
            }
            // Check for Buzz only
            else if (i % 5 == 0) {
                result += "Buzz";
            }
            // If not divisible by 3 or 5, just add the number
            else {
                result += i;
            }

            // Add space between outputs (but not after last number)
            if (i < end) result += " ";
        }

        return result;
    }

    public static void main(String[] args) {
        // Print full FizzBuzz sequence from 1 to 100
        System.out.println(generateFizzBuzz(1, 100));
    }
}
