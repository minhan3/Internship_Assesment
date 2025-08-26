public class P2 {
    // Generate fizzbuzz sequence from start to end
    public static String generateFizzBuzz(int start, int end) {
        String result = "";
        for (int i = start; i <= end; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result += "FizzBuzz";
            } else if (i % 3 == 0) {
                result += "Fizz";
            } else if (i % 5 == 0) {
                result += "Buzz";
            } else {
                result += i;
            }
            if (i < end) result += " ";
        }
        return result;
    }

    public static void main(String[] args) {
        // Print full 1–100 sequence
        System.out.println(generateFizzBuzz(1, 100));
    }
}
