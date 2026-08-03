public class Solution {
    public static void main(String[] args) {

        int n1 = 9;
        int n2 = 12;

        System.out.println(gcd_brute_force(n1, n2));
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
}
