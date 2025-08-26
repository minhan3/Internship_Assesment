public class P6 {

    public static char[] findMaxChar(String input) {
        int[] count = new int[128]; // For standard ASCII characters

        // Count only alphabet characters (ignore spaces and punctuation)
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isLetter(ch)) {
                count[ch]++;
            }
        }

        // Find character with the maximum count
        int max = 0;
        char maxChar = ' ';
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                maxChar = (char) i;
            }
        }

        return new char[]{maxChar, (char) max}; // Return char and count (as char)
    }

    // Helper method to check if a character is a letter
    public static boolean isLetter(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    // Example usage
    public static void main(String[] args) {
        String input = "Hello, world!";
        char[] result = findMaxChar(input);
        System.out.println("Character: '" + result[0] + "', Occurrence: " + (int) result[1]);
        // Output: Character: 'l', Occurrence: 3
    }
}
