package problems.leetcode.leetcode4;

/*Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

        The digits are stored such that the most significant digit is at the head of the list,
        and each element in the array contain a single digit.

        You may assume the integer does not contain any leading zero, except the number 0 itself.*/



public class PlusOne_66 {
    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        if(digits[length-1] < 9){
            digits[length-1] += 1;
            return digits;
        }

        digits[length-1] = 0;
        int i = length-2;
        while(i >= 0){
            digits[i] += 1;
            if(digits[i] < 10){
                return digits;
            }
            digits[i] = 0;
            i--;
        }

        // if we are here there is still a carry of 1
        int[] newDigits = new int[length+1];
        newDigits[0] = 1;
        for(int j = 1; j < length+1; j++){
            newDigits[j] = digits[j-1];
        }

        return newDigits;



    }
}
