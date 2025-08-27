public class P8Test {
    public static void main(String[] args) {
        // Test 1: Expected true → "night" and "thing" are anagrams
        // Same letters in different order → should PASS
        runTest("night", "thing", true, "Test 1");

        // Test 2: Expected false → "paper" and "pencil" are not anagrams
        // Different letters → should PASS
        runTest("paper", "pencil", false, "Test 2");

        // Test 3: Should be true → "angel" and "glean" are anagrams
        // Marked false on purpose → should FAIL
        runTest("angel", "glean", false, "Test 3");

        // Test 4: Should be false → "train" and "rainy" are not anagrams
        // Marked true on purpose → should FAIL
        runTest("train", "rainy", true, "Test 4");
    }

    // This method runs the test by comparing expected and actual result from isAnagram()
    private static void runTest(String s1, String s2, boolean expected, String testName) {
        boolean result = P8.isAnagram(s1, s2);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL → Expected answer was " + expected + ", but the result was " + result);
        }
    }
}
