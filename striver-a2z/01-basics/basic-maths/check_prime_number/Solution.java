import java.util.ArrayList;

public class Solution {

    public static boolean check_prime(int n){
        ArrayList<Integer> divisors = listDivisors(n);

        if (divisors.size() <= 2){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String result = (check_prime(7) ? "TRUE: It is a prime number" : "FALSE: It is a composite number");
        System.out.println(result);
    }

    public static ArrayList<Integer> listDivisors(int n){

        ArrayList<Integer> divisors = new ArrayList<>();
        double limit = Math.sqrt(n); 
        
        for(int i=1; i<=limit; i++){
            if (n % i == 0){
                divisors.add(i);

                int partner = n / i;
                if (partner != i){
                    divisors.add(partner);
                }
            }
        }
        divisors.sort(null);
        return divisors;
    }
}
