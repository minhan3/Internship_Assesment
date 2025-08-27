import java.util.Scanner;

/**
 * Problem 1 - Sorting (ascending)
 * Approach: Insertion Sort
 *
 * Language Choice Reasoning:
 * Java was chosen because this problem involves array manipulation and user input handling.
 * Java provides strong built-in support for array processing, clear loop constructs,
 * and robust input mechanisms like Scanner, which make implementing insertion sort straightforward.
 */

/** Complexity Analysis:
  - Idea: Take each number and move it left until it is in the correct place.
  - Best case: O(n) → list already sorted, few moves needed.
  - Average case: O(n^2) → some numbers need to move several times.
  - Worst case: O(n^2) → list in reverse order, every number moves a lot.
  - Space: O(1) → uses the same array, only a few extra variables.
  - Stable: Yes → equal numbers stay in the same order.
  - Good for: small or almost sorted lists.
  - Not good for: very large or very mixed lists (better to use faster sorts).
 */

public class P1 {

    // Function to sort array in ascending order using Insertion Sort
    public static int[] sortAsc(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];        // Current element to insert
            int j = i - 1;

            // Shift elements greater than key to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Move element one step right
                j--;
            }

            // Place key into correct position
            arr[j + 1] = key;
        }
        return arr;
    }

    // Main method for user input and output display
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user how many numbers to sort
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] data = new int[n];

        // Input each number from the user
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }

        // Display the original input
        System.out.print("Input : ");
        for (int num : data) System.out.print(num + " ");
        System.out.println();

        // Sort the array
        int[] sorted = sortAsc(data);

        // Display the sorted output
        System.out.print("Output: ");
        for (int num : sorted) System.out.print(num + " ");
        System.out.println();

        sc.close();
    }
}
