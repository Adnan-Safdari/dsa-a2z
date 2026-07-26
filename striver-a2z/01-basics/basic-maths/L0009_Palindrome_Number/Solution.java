package L09_Palindrome_Number;

public class Solution {

    // Approach 1
    public boolean isPalindromeA(int x) {
        if (x < 0) return false;

        int sum = 0;
        int temp = x;

        while (temp != 0) {
            int rem = temp % 10;
            sum = sum * 10 + rem;
            temp /= 10;
        }
        return sum == x;
    }

    // Approach 2
    public boolean isPalindromeB(int x) {
        if (x < 0) return false;

        int rev=0;
        int num = x;

        while (num !=0){
            rev = (rev * 10) + (num % 10); // adding the last digit to reversed num
            num /= 10; // removing the last digit
        }
        return (x == rev);
    }
}