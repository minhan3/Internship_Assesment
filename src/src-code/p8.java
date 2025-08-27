/*
 * Problem 8 - Anagram Checker
 *
 * Language Justification:
 * Java is suitable here because it offers easy string manipulation, character access, and a 
 * fixed-size array to count characters (ASCII 128). Java also makes it straightforward to clean
 * strings and manage character comparisons. This is useful for checking letter frequencies 
 * and ignoring punctuation or whitespace.

/*
 * Time Complexity: O(n)
 * - We loop through each character in the strings a few times.
 *
 * Space Complexity: O(1)
 * - We use a fixed size array (128) to count characters.
 */

public class P8 {

    // Function to check if two strings are anagrams
    public static boolean isAnagram(String str1, String str2) {
        // Clean both strings: remove non-letters and convert to lowercase
        str1 = cleanString(str1);
        str2 = cleanString(str2);

        // If lengths are different after cleaning, not anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Count each character in str1
        int[] count = new int[128]; // For standard ASCII
        for (int i = 0; i < str1.length(); i++) {
            count[str1.charAt(i)]++;
        }

        // Subtract the character counts from str2
        for (int i = 0; i < str2.length(); i++) {
            count[str2.charAt(i)]--;
        }

        // Check if all character counts are back to 0
        for (int i = 0; i < 128; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        return true;
    }

    // Helper function to clean string (remove non-letters and make lowercase)
    public static String cleanString(String str) {
        String cleaned = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) {
                cleaned += Character.toLowerCase(ch);
            }
        }
        return cleaned;
    }

    // Main method to test the anagram function with all 6 examples
    public static void main(String[] args) {
        System.out.println("Example 1 → " + isAnagram("listen", "silent"));                 // true
        System.out.println("Example 2 → " + isAnagram("debit card", "Bad credit"));         // true
        System.out.println("Example 3 → " + isAnagram("hello", "bye"));                     // false
        System.out.println("Example 4 → " + isAnagram("restful", "fluster"));               // true
        System.out.println("Example 5 → " + isAnagram("listen", "silentt"));                // false
        System.out.println("Example 6 → " + isAnagram("Conversation", "Voices, rant on"));  // true
    }
}
