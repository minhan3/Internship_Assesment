public class P2Test {
    public static void main(String[] args) {
        // Test 1: PASS
        String output1 = "1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz";
        runTest(1, 15, output1, "Test 1");

        // Test 2: PASS
        String output2 = "1 2 Fizz 4 Buzz";
        runTest(1, 5, output2, "Test 2");

        // Test 3: FAIL
        String output3 = "1 2 Buzz";
        runTest(1, 3, output3, "Test 3");

        // Test 4: FAIL
        String output4 = "1 2 Fizz 4 Buzz Fizz 7 8 Buzz 10";
        runTest(1, 10, output4, "Test 4");
    }

    private static void runTest(int start, int end, String output, String testName) {
        String result = P2.generateFizzBuzz(start, end); // ← calls P2 now

        if (result.equals(output)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL → Output should be: \"" + output + "\" but got: \"" + result + "\"");
        }
    }
}
