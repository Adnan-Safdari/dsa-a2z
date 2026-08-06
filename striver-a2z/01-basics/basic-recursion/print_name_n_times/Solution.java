public class Solution {
    public static int printName(int x, String name){
        
        // Base Case
        if (x == 0){
            return 0;
        }

        System.out.println(name);
        return printName(x-1, name);
    }

    public static void main(String[] args) {
        printName(5, "Adnan");
    }
}
