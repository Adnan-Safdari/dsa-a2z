public class Solution {
    public static int printToN(int n){
        
        // Base Case
        if (n == 0){
            return 0;
        }

        System.out.println(n);
        return printToN(n-1);
    }

    public static void main(String[] args) {
        printToN(5);
    }
}
