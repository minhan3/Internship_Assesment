import java.util.Scanner;

public class P3 {

    // Recursive Fibonacci function
    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Generate sequence up to count elements
    public static String generateFibonacci(int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += fibonacci(i);
            if (i < count - 1) result += " ";
        }
        return result;
    }

    // Main method (user input)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter how many Fibonacci numbers to generate: ");
        int count = sc.nextInt();

        System.out.println("Fibonacci sequence: ");
        System.out.println(generateFibonacci(count));
        
        sc.close();
    }
}
