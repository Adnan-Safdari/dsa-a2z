public class Solution {
    public static void main(String[] args) {

        int n1 = 9;
        int n2 = 12;


        System.out.println(gcd_brute_force(n1, n2));
        System.out.println(gcd_euclids(n1, n2));
    }


    // Method 1: Brute Force Method 
    // Time Complexity: O(n)
    public static int gcd_brute_force(int n1, int n2){

        for(int i=Math.min(n1, n2); i>0; i--){
            if( n1 % i == 0 && n2 % i == 0){
                return i;
            }
        }
        return 1;
    }

    // Method 2: Optimal Approach
    // Time Complexity: O(log n)
    public static int gcd_euclids(int n1, int n2){
        if (n1 == n2){
            return n1;
        }

        int a = Math.max(n1, n2);
        int b = Math.min(n1, n2);

        while (b > 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

