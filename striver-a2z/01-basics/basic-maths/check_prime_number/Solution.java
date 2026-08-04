import java.util.ArrayList;

public class Solution {

    public static boolean checkPrime(int n) {

        // 0 and 1 are not prime
        if (n <= 1) {
            return false;
        }

        int limit = (int) Math.sqrt(n);

        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String result = (checkPrime(7) ? "TRUE: It is a prime number" : "FALSE: It is a composite number");
        System.out.println(result);
    }

}
