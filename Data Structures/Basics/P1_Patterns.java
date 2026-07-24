
public class P1_Patterns {

    static void displayPatternOne() {
        /*
        *****
        *****
        *****
        *****
        *****
         */
        System.out.println("\n==== PATTERN 1 ====");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void displayPatternTwo() {
        /*
        *
        **
        ***
        ****
        *****
         */
        System.out.println("\n==== PATTERN 2 ====");
        int N = 5;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void displayPatternThree() {
        /*
    1
    12
    123
    1234
    1235
         */
        System.out.println("\n==== PATTERN 3 ====");
        int count = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(j + 1);
            }
            System.out.println();
            count++;
        }
    }

    static void displayPatternFour() {
        /*
    1
    22
    333
    4444
    55555
         */
        System.out.println("\n==== PATTERN 4 ====");
        int count = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(count);
            }
            System.out.println();
            count++;
        }
    }

    static void displayPatternFive() {
        /*
        *****
        ****
        ***
        **
        *
         */
        System.out.println("\n==== PATTERN 5 ====");
        int count = 5;
        for (int i = 0; i < 5; i++) {
            for (int j = count--; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void displayPattenSix() {
        /*
        12345
        1234
        123
        12
        1
         */
        System.out.println("\n==== PATTERN 6 ====");
        int count = 5;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= count; j++) {
                System.out.print(j);
            }
            count--;
            System.out.println();
        }
    }

    static void displayPatternSeven(){
        /*
         
            *
           ***
          *****
         *******
        *********
         */
        System.out.println("\n==== PATTERN 7 ====");
        int N = 5;
        for(int row = 1; row <= N; row++){  
        
            // Printing the space
            for(int sp = 0; sp < N - row; sp++){
                System.out.print(" ");
            }

            // Printing the stars 
            for(int st = 0; st < ((2 * row) - 1); st++){  // formula = 2*row - 1
                System.out.print("*");
            }
            System.out.println();
        }
    }

        static void displayPatternEight(){
        /*

        *********
         *******
          *****
           ***
            *
         */
        System.out.println("\n==== PATTERN 8 ====");
        int N = 5;
        for(int row = N; row >= 0; row--){  
        
            // Printing the space
            for(int sp = 0; sp < N - row; sp++){
                System.out.print(" ");
            }

            // Printing the starts 
            for(int st = 0; st < ((2 * row) - 1); st++){  // formula = 2*row - 1
                System.out.print("*");
            }
            System.out.println();
        }
    }

        static void displayPatternNineA(){
        /*

            *
           ***
          *****
         *******
        *********
         *******
          *****
           ***
            *
         */
        System.out.println("\n==== PATTERN 9 A ====");
        int N = 10;
        // first half
        for(int row = 1; row <= N/2; row++){  
        
            // Printing the space
            for(int sp = 0; sp < N - row; sp++){
                System.out.print(" ");
            }

            // Printing the starts 
            for(int st = 0; st < ((2 * row) - 1); st++){  // formula = 2*row - 1
                System.out.print("*");
            }
            System.out.println();
        }
        // second half
        for(int row = N/2-1; row > 0; row--){  
        
            // Printing the space
            for(int sp = 0; sp < N - row; sp++){
                System.out.print(" ");
            }

            // Printing the starts 
            for(int st = 0; st < ((2 * row) - 1); st++){  // formula = 2*row - 1
                System.out.print("*");
            }
            System.out.println();
        }
        
    }

        static void printRowHelperPNine(int row, int height) {
            // Printing the space
            for(int sp = 0; sp < height - row; sp++){
                System.out.print(" ");
            }

            // Printing the starts 
            for(int st = 0; st < ((2 * row) - 1); st++){  // formula = 2*row - 1
                System.out.print("*");
            }
            System.out.println();
        }
        static void displayPatternNineB(){
        /*

            *
           ***
          *****
         *******
        *********
         *******
          *****
           ***
            *
         */
        System.out.println("\n==== PATTERN 9 B ====");
        int N = 10;
        // first half
        for(int row = 1; row <= N/2; row++){  
            printRowHelperPNine(row, N);
        }
        // second half
        for(int row = N/2-1; row > 0; row--){  
            printRowHelperPNine(row, N);
        }
        
    }

    static void displayPatternTen() {
        /*
        *
        **
        ***
        ****
        *****
        ****
        ***
        **
        *
         */
        System.out.println("\n==== PATTERN 10 ====");
        int height = 10;
        for (int row = 1; row <= height/2; row++) {
            for (int j = 0; j < row; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int row = (height/2)-1; row > 0; row--) {
            for (int j = 0; j < row; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }


    static void displayPatternEleven() {
        /*
        1
        01
        101
        0101
        10101
         */
        System.out.println("\n==== PATTERN 11 ====");
        int height = 5;
        
        for (int row = 1; row <= height; row++) {
            int prevNum = (row % 2 == 0) ? 0 : 1;  // Odd always starts with 1 and even with 0 (we giving opposite due to next switch)
            for (int j = 0; j < row; j++) {
                System.out.print(prevNum);
                prevNum = 1 - prevNum; // returns 1 or 0
            }
            System.out.println();
        }

    }

    static void displayPatternTwelve() {
        /*
        1      1
        12    21
        123  321
        12344321
        
         */
        System.out.println("\n==== PATTERN 12 ====");
        int height = 4;
        int rowSize = height * 2;
        
        for (int row = 1; row <= height; row++) {

            // - - - Printing ascending - - -
            for (int j = 1; j <= row; j++) {
                System.out.print(j);
            }

            // - - - Printing spaces - - -
            int spaces = rowSize - (row *2);
            for(int k = spaces; k > 0; k--){
                System.out.print(" ");
            }

            // - - - Printing descending - - -
            for (int l = row; l >= 1; l--) {
                System.out.print(l);
            }

            System.out.println();
        }

    }

    static void displayPatternThirteen() {
        /*
        1
        2 3
        4 5 6
        7 8 9 10
        11 12 13 14 15
         */
        System.out.println("\n==== PATTERN 13 ====");
        int height = 5;
        int counter = 1;
        for (int row = 1; row <= height; row++) {
            for(int j = 1; j<=row; j++){
                System.out.print(counter++ + " ");
            }
            System.out.println();
        }

    }

    static void displayPatternFourteen() {
        /*
        A
        AB
        ABC
        ABCD
        ABCDE
         */
        System.out.println("\n==== PATTERN 14 ====");
        int height = 5;
        for (int row = 1; row <= height; row++) {
            char ch = 'A';
            for(int j = 1; j<=row; j++){
                System.out.print(ch++);
            }
            System.out.println();
        }

    }

    static void displayPatternFifteen() {
        /*
        ABCDE
        ABCD
        ABC
        AB
        A
         */
        System.out.println("\n==== PATTERN 15 ====");
        int height = 5;
        for (int row = height; row >= 1; row--) {
            char ch = 'A';
            for(int j = 1; j<=row; j++){
                System.out.print(ch++);
            }
            System.out.println();
        }

    }

    static void displayPatternSixteen() {
        /*
        A
        BB
        CCC
        DDDD
        EEEEE
         */
        System.out.println("\n==== PATTERN 16 ====");
        int height = 5;
        char ch = 'A';
        for (int row = 1; row <= height; row++) {
            for(int j = 1; j<=row; j++){
                System.out.print(ch);
            }
            ch++;
            System.out.println();
        }

    }

    static void displayPatternSeventeen() {
        /*
        A
        AB
        ABC
        ABCD
        ABCDE
         */
        System.out.println("\n==== PATTERN 17 ====");
        int height = 5;
        
        for (int row = 1; row <= height; row++) {
            char ch = 'A';
            for(int j = 1; j<=row; j++){
                System.out.print(ch++);
            }
            System.out.println();
            
        }
    }

    static void displayPatternEighteen() {
        /*
            A
           ABA
          ABCBA
         ABCDCBA
        ABCDEDCBA
         */
        System.out.println("\n==== PATTERN 18 ====");
        int height = 5;
        for(int row = 1; row <= height; row++){  
        
            // Printing the space
            for(int sp = 0; sp < height - row; sp++){
                System.out.print(" ");
            }

            // Printing the left letters
            char ch = 'A';
            for(int x = 0; x < row; x++){  // formula = 2*row - 1
                System.out.print(ch++);
            }

            // Printing the right letters
            ch--; // decrementing as we dont want duplicate
            for(int x = row-1; x > 0; x--){  // formula = 2*row - 1
                System.out.print(ch--);
            }

            System.out.println();
        }
    }   

    static void displayPatternNineteen() {
        /*
        E
        DE
        CDE
        BCDE
        ABCDE
         */
        System.out.println("\n==== PATTERN 19 ====");
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int height = 5;
        for (int row = 1; row <= height; row++) {
    
            for(int j = 0; j<row; j++){
                char myChar = chars.charAt(height - row + j);
                System.out.print(myChar);
            }
            System.out.println();
        }
    }

    static void displayPatternTwenty() {
        /*
        *        *
        **      **
        ***    ***
        ****  ****
        **********
        ****  ****
        ***    ***
        **      **
        *        *
         */

        System.out.println("\n==== PATTERN 20 ====");
        int height = 10;

        // Printing upper half
        for (int row = 1; row <= height/2; row++) {

            // LOOP 1: Printing left side stars
            for (int j = 0; j < row; j++) {
                System.out.print("*");
            }

            // LOOP 2: Printing the spaces
            for(int space = 0; space < ((height/2)-row); space++){
                System.out.print(" ");
            }
            
            // LOOP 3: Printing right side stars
            for (int j = 0; j < row; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Print the spaces


        // for (int row = (height/2)-1; row > 0; row--) {
        //     for (int j = 0; j < row; j++) {
        //         System.out.print("*");
        //     }
        //     System.out.println();
        // }
    }


    static void displayPatternTwentyOne() {
        /*
        *****
        *   *
        *   *
        *   *
        *****
         */
    }

    static void displayPatternTwentyTwo() {
        /*
        4 4 4 4 4 4 4
        4 3 3 3 3 3 4
        4 3 2 2 2 3 4
        4 3 2 1 2 3 4
        4 3 2 2 2 3 4
        4 3 3 3 3 3 4
        4 4 4 4 4 4 4
         */
    }



    public static void main(String[] args) {
        displayPatternOne();
        displayPatternTwo();
        displayPatternThree();
        displayPatternFour();
        displayPatternFive();
        displayPattenSix();
        displayPatternSeven();
        displayPatternEight();
        displayPatternNineA();
        displayPatternNineB();
        displayPatternTen();
        displayPatternEleven();
        displayPatternTwelve();
        displayPatternThirteen();
        displayPatternFourteen();
        displayPatternFifteen();
        displayPatternSixteen();
        displayPatternSeventeen();
        displayPatternEighteen();
        displayPatternNineteen();
        displayPatternTwenty();
    }
}
