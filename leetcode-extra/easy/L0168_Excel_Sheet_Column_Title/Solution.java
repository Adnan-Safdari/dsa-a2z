package L168_Excel_Sheet_Column_Title;

/**
 * Given an integer 'columnNumber', return its corresponding column title as it appears in an Excel Sheet.
 * For example:
 *      A  -> 1
 *      B  -> 2
 *      C  -> 3
 * 
 *      Z  -> 26
 *      AA -> 27
 *      AB -> 28
 * 
 * Example 1:
 *      Input: columnNummber = 1
 *      Output: "A"
 * 
 * Example 2:
 *      Input: columnNumber = 28 
 *      Output: "AB"
 * 
 * Example 3:
 *      Input: columnNumber = 701
 *      Output: "ZY"
 * 
 */
                                                       
                                                       
public class Solution {

    private String resolveChar(int n){
        String alphabets[] = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
        int index = (n-1)%26;
        return alphabets[index];
    }

    public String convertToTitle(int columnNumber) {

    String output = "";
    while (columnNumber > 0){
        output = output + resolveChar(columnNumber);
        columnNumber = (columnNumber-1)%26;
    }

    return output;
    }
}
