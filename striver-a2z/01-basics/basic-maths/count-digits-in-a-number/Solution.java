// Count Digits in a Number : Given an integer N, return the number of digits in N. 

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        // Taking user input of the number
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int N = sc.nextInt();
        sc.close();

        int count = 0;

        while (N > 0){
            N = N / 10;
            count++;
        }

        System.out.println("The count is: " + count);
    }
}
