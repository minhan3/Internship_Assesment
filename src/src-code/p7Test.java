public class P7Test {
    public static void main(String[] args) {
        // Test 1: sqrt(16) = 4
        runTest(16, 4, "Test 1");

        // Test 2: sqrt(36) = 6
        runTest(36, 6, "Test 2");

        // Test 3: Wrong expected result
        runTest(49, 6, "Test 3"); // Expected 7

        // Test 4: Wrong expected result
        runTest(81, 8, "Test 4"); // Expected 9
    }

    private static void runTest(int input, int expected, String testName) {
        int result = P7.findSquareRoot(input);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL → Output is " + expected + " but the right answer is " + result);
        }
    }
}
