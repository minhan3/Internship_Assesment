public class P3Test {
    public static void main(String[] args) {
        // Test 1: First 5 numbers (should PASS)
        String output1 = "0 1 1 2 3";
        runTest(5, output1, "Test 1");

        // Test 2: First 7 numbers (should PASS)
        String output2 = "0 1 1 2 3 5 8";
        runTest(7, output2, "Test 2");

        // Test 3: Wrong output for first 3 numbers (FAIL)
        String output3 = "0 1 2"; // wrong, should be "0 1 1"
        runTest(3, output3, "Test 3");

        // Test 4: Wrong output for first 6 numbers (FAIL)
        String output4 = "0 1 1 3 5 8"; // wrong, missing 2
        runTest(6, output4, "Test 4");
    }

    private static void runTest(int count, String output, String testName) {
        String result = P3.generateFibonacci(count);

        if (result.equals(output)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL → Output should be: \"" + output + "\" but got: \"" + result + "\"");
        }
    }
}
