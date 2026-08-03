
public class Solution {

    public static int reverse(int x) {
        int reversed = 0;

        while(x != 0){
            // Handling Positive overflow
            if (reversed > (Integer.MAX_VALUE/10) || 
            (reversed == Integer.MAX_VALUE/10) && (x % 10) > 7){ // We are also an edge case where what if the last digit is greater causing an oveflow
                return 0;
            }
            // Handling negative overflow
            if (reversed < Integer.MIN_VALUE / 10 ||
                (reversed == Integer.MIN_VALUE / 10 && (x % 10) < -8)) {
                return 0;
            }

            reversed = (reversed * 10) +  x % 10;
            x = x/10; // removing the last digit
        }   
        return reversed;
    }

    public static void main(String[] args){
        System.out.println(reverse(15342364));
    }
}
