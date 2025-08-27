public class P5Test {
    public static void main(String[] args) {
        // Test 1: Expected [2, 3, 1, 8, 7, 9]
        // Elements unique to each list (excluding common values 4, 5, 6)
        int[] list1 = {4, 5, 2, 3, 1, 6};
        int[] list2 = {8, 7, 6, 9, 4, 5};
        int[] output1 = {2, 3, 1, 8, 7, 9};
        runTest(list1, list2, output1, "Test 1");

        // Test 2: Expected [1, 2, 4, 5]
        // 3 is in both lists, so we exclude it
        int[] list3 = {1, 2, 3};
        int[] list4 = {3, 4, 5};
        int[] output2 = {1, 2, 4, 5};
        runTest(list3, list4, output2, "Test 2");

        // Test 3: Wrong output (duplicate, wrong order)
        // Output includes 2 (common) and is not sorted like result from the function
        int[] list5 = {1, 2, 3};
        int[] list6 = {2, 3, 4};
        int[] output3 = {1, 4, 2}; // should be [1, 4]
        runTest(list5, list6, output3, "Test 3");

        // Test 4: Missing values (should have 13, 14)
        // 12 is common, but 13 and 14 should appear in the symmetric difference
        int[] list7 = {10, 11, 12};
        int[] list8 = {12, 13, 14};
        int[] output4 = {10, 11}; // missing 13, 14
        runTest(list7, list8, output4, "Test 4");
    }

    // Function to test and compare actual result with expected output
    private static void runTest(int[] list1, int[] list2, int[] output, String testName) {
        int[] result = P5.findSymmetricDifference(list1, list2);

        boolean pass = result.length == output.length;
        for (int i = 0; i < result.length && pass; i++) {
            if (result[i] != output[i]) {
                pass = false;
            }
        }

        if (pass) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.print(testName + ": FAIL → Output should be: ");
            printArray(output);
            System.out.print(" but got: ");
            printArray(result);
            System.out.println();
        }
    }

    // Helper method to print array values
    private static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
