import java.util.ArrayList;
import java.util.Arrays;


public class Solution {
    public static void main(String[] args) {
        System.out.println(listDivisorsBruteForce(36));
    }

    // Method 1: Brute Force
    // Time Complexity: O(n)
    public static ArrayList<Integer> listDivisorsBruteForce(int n){
        ArrayList<Integer> divisors = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if (n % i == 0){
                divisors.add(i);
            }
        }
        return divisors;
    }

    // Method 2: Non negative integer property
    // if d is a divisor of n then n/d is also a divisor of n

    @
}
