/*
 * Problem 6 - Most Frequent Character in a String
 *
 * Language Justification:
 * Java is suitable for this problem because it provides precise handling of character arrays
 * and ASCII values. It allows us to use a fixed-size array (128) to track character frequency,
 * which is efficient and avoids relying on built-in data structures.
 */

public class P6 {

    // Function to find the most frequent alphabet character in the input string
    public static char[] findMaxChar(String input) {
        int[] count = new int[128]; // For standard ASCII characters

        // Count only alphabet characters (ignore spaces and punctuation)
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isLetter(ch)) {
                count[ch]++; // Count how many times each character appears
            }
        }

        // Find the character that appears the most
        int max = 0;
        char maxChar = ' ';
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                maxChar = (char) i;
            }
        }

        // Return result as a character array: [most frequent character, its count as a character]
        return new char[]{maxChar, (char) max};
    }

    // Helper method to check if a character is a letter (A–Z or a–z)
    public static boolean isLetter(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    // Main method for testing
    public static void main(String[] args) {
        String input = "Hello, world!"; // Example input
        char[] result = findMaxChar(input);
        System.out.println("Character: '" + result[0] + "', Occurrence: " + (int) result[1]);
        // Output: Character: 'l', Occurrence: 3
    }
}
