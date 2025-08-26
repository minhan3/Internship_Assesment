import java.util.Scanner;

/**
 * Problem 1 - Sorting (ascending)
 * Approach: Insertion Sort
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

    // Function to sort array in ascending order
    public static int[] sortAsc(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements greater than key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    // Main method for user input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user how many numbers
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        
        int[] data = new int[n];
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }

        System.out.print("Input : ");
        for (int num : data) System.out.print(num + " ");
        System.out.println();

        int[] sorted = sortAsc(data);

        System.out.print("Output: ");
        for (int num : sorted) System.out.print(num + " ");
        System.out.println();
        
        sc.close();
    }
}
