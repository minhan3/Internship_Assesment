public class P5 {

    // Function to check if a number is in a list
    public static boolean isInList(int[] list, int size, int number) {
        for (int i = 0; i < size; i++) {
            if (list[i] == number) {
                return true;
            }
        }
        return false;
    }

    // Function to find symmetric difference
    public static int[] findSymmetricDifference(int[] list1, int[] list2) {
        int[] temp = new int[list1.length + list2.length];
        int count = 0;

        // Step 1: Elements in list1 but not in list2
        for (int i = 0; i < list1.length; i++) {
            boolean found = false;
            for (int j = 0; j < list2.length; j++) {
                if (list1[i] == list2[j]) {
                    found = true;
                    break;
                }
            }
            if (!found && !isInList(temp, count, list1[i])) {
                temp[count] = list1[i];
                count++;
            }
        }

        // Step 2: Elements in list2 but not in list1
        for (int i = 0; i < list2.length; i++) {
            boolean found = false;
            for (int j = 0; j < list1.length; j++) {
                if (list2[i] == list1[j]) {
                    found = true;
                    break;
                }
            }
            if (!found && !isInList(temp, count, list2[i])) {
                temp[count] = list2[i];
                count++;
            }
        }

        // Copy result to final array
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] list1 = {4, 5, 2, 3, 1, 6};
        int[] list2 = {8, 7, 6, 9, 4, 5};

        int[] output = findSymmetricDifference(list1, list2);

        System.out.print("Symmetric Difference: ");
        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i] + " ");
        }
    }
}
