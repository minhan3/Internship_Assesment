/*
 * ============================
 * Bonus: Space-Time Complexity
 * ============================
 *
 * Time Complexity:
 * - The program checks every item in list1 against every item in list2.
 * - This means if both lists have 6 items, it may do up to 36 comparisons.
 * - So, as the lists get longer, the time it takes grows quickly.
 * - This is called O(n × m) time.
 *
 * Space Complexity:
 * - The program makes a new list to store the result (the unique values).
 * - If the two lists don’t share any numbers, it stores everything from both.
 * - So, the memory used depends on how big the lists are.
 * - This is called O(n + m) space.
 *
 * Summary:
 * Time = O(n × m)
 * Space = O(n + m)
 */

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
