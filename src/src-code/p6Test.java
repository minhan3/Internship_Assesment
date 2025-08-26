public class P6Test {
    public static void main(String[] args) {
        // Test 1: Expected 'l' occurs 3 times
        runTest("Hello, world!", 'l', 3, "Test 1");

        // Test 2: Expected 'a' occurs 6 times
        runTest("Banana apple again", 'a', 6, "Test 2");

        // Test 3: Wrong expected character (actual: 's' = 3)
        runTest("This is a test", 's', 2, "Test 4");
    }

    private static void runTest(String input, char expectedChar, int expectedCount, String testName) {
        char[] result = P6.findMaxChar(input);
        char actualChar = result[0];
        int actualCount = (int) result[1]; // Convert char to int for count

        boolean pass = (actualChar == expectedChar && actualCount == expectedCount);

        if (pass) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL → Expected '" + expectedChar + "' = " + expectedCount +
                               ", but the answer is '" + actualChar + "' = " + actualCount);
        }
    }
}
