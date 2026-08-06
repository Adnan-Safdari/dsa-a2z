import java.util.Arrays;

public class Solution {
    public static void reverseArray(int[] arr, int left, int right){
        if (left >= right){
            return;
        }

        // Swapping
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        reverseArray(arr, left+1, right-1); // recursive call
    }

    public static void main(String[] args){
        int[] myArray = {1, 2, 3, 4 , 5};
        // In java the array gets modified. No new array is created
        reverseArray(myArray, 0, myArray.length-1);
        System.out.println(Arrays.toString(myArray));
    }
}
