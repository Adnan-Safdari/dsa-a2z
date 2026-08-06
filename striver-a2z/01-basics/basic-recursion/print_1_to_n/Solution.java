public class Solution {
    public static int printToN(int n, int curr){
        
        // Base Case
        if (curr > n){
            return 0;
        }

        System.out.println(curr);
        return printToN(n, curr+1);
    }

    public static void main(String[] args) {
        printToN(5, 1);
    }
}
