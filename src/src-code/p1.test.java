public class P1Test {
    public static void main(String[] args) {

        // Test 1: Example case (should PASS)
        int[] input1 = {21, 400, 8, -3, 77, 99, -16, 55, 111, -36, 28};
        int[] output1 = {-36, -16, -3, 8, 21, 28, 55, 77, 99, 111, 400};
        runTest(input1, output1, "Test 1");

        // Test 2: Already sorted (should PASS)
        int[] input2 = {1, 2, 3, 4};
        int[] output2 = {1, 2, 3, 4};
        runTest(input2, output2, "Test 2");

        // Test 3: Wrong expected output (will FAIL)
        int[] input3 = {5, 4, 3, 2, 1};
        int[] output3 = {5, 4, 3, 2, 1}; // wrong on purpose
        runTest(input3, output3, "Test 3");

        // Test 4: Wrong expected output (will FAIL)
        int[] input4 = {2, 2, 1, 3, 1};
        int[] output4 = {2, 2, 1, 1, 3}; // wrong on purpose
        runTest(input4, output4, "Test 4");
    }

    // function to check results
    private static void runTest(int[] arr, int[] expected, String testName) {
        int[] result = P1.sortAsc(arr);
        boolean pass = true;

        if (result.length != expected.length) pass = false;
        for (int i = 0; i < expected.length && pass; i++) {
            if (result[i] != expected[i]) {
                pass = false;
            }
        }

        if (pass) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.print(testName + ": FAIL → Expected: ");
            printArray(expected);
            System.out.print(" but got: ");
            printArray(result);
            System.out.println();
        }
    }

    // Print array nicely
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
