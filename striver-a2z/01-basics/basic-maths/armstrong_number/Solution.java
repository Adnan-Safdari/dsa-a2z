import java.util.Scanner;

public class Solution {
    public static void main(String[] args){

        // Taking user input of the number
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        sc.close();   
        
        int original = n;
        int length = countDigits(n);
        int result = 0;
        // Looping through each digit and adding it to check armstrong
        while(n > 0){
            int digit = n % 10;
            result += Math.pow(digit, length); // += is a special compound assignment operator. Java automatically performs a narrowing conversion. (Math.pow() returns Double)
            n = n / 10;
        }
        
        if (result == original){
            System.out.println("TRUE: It is an Armstrong number");
        } else {
            System.out.println("FALSE: It is not an Armstrong number");
        }
    }

    public static int countDigits(int x) {
        int length=0;
        while (x > 0){
            x = x / 10;
            length++;
        }
        return length;
    }
}
