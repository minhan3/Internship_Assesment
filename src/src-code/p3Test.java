public class P3Test {
    public static void main(String[] args) {
        // Test 1: First 5 Fibonacci numbers
        // Expected output: "0 1 1 2 3" → should PASS
        String output1 = "0 1 1 2 3";
        runTest(5, output1, "Test 1");

        // Test 2: First 7 Fibonacci numbers
        // Expected output: "0 1 1 2 3 5 8" → should PASS
        String output2 = "0 1 1 2 3 5 8";
        runTest(7, output2, "Test 2");

        // Test 3: Incorrect output for first 3 numbers
        // Expected output: "0 1 1", but got "0 1 2" → should FAIL
        String output3 = "0 1 2";
        runTest(3, output3, "Test 3");

        // Test 4: Incorrect output for first 6 numbers
        // Missing value "2" → should FAIL
        String output4 = "0 1 1 3 5 8";
        runTest(6, output4, "Test 4");
    }

    // Helper function to run and validate test cases
    private static void runTest(int count, String output, String testName) {
        String result = P3.generateFibonacci(count);

        // Compare generated sequence with expected output
        if (result.equals(output)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL → Output should be: \"" + output + "\" but got: \"" + result + "\"");
        }
    }
}
