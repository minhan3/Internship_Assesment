public class P7Test {
    public static void main(String[] args) {
        // Test 1: sqrt(16) = 4
        // 4*4 = 16, so this test should PASS
        runTest(16, 4, "Test 1");

        // Test 2: sqrt(36) = 6
        // 6*6 = 36, so this test should PASS
        runTest(36, 6, "Test 2");

        // Test 3: Wrong expected result
        // Expected result is 7, but given 6 → should FAIL
        runTest(49, 6, "Test 3"); // Expected 7

        // Test 4: Wrong expected result
        // Expected result is 9, but given 8 → should FAIL
        runTest(81, 8, "Test 4"); // Expected 9
    }

    // This function runs the test and checks if the square root output matches the expected value
    private static void runTest(int input, int expected, String testName) {
        int result = P7.findSquareRoot(input);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL → Output is " + expected + " but the right answer is " + result);
        }
    }
}
