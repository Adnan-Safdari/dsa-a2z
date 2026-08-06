public class Solution {
    public static int sumNaturalNumbersFormula(int N){
        // FORMULA: N(N-1)/2
        return (N * (N + 1)) / 2;
    }    
    public static int sumNaturalNumbersRecursion(int N){
        if (N == 1){
            return 1;
        }

        return N + sumNaturalNumbersFormula(N - 1);
    }

    public static void main(String[] args){
        System.out.println(sumNaturalNumbersFormula(5));
        System.out.println(sumNaturalNumbersRecursion(5));
    }
}
