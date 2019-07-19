package problems.leetcode.leetcode1_20;

/*
* Implement atoi which converts a string to an integer.
*
* The function first discards as many whitespace characters as necessary
* until the first non-whitespace character is found.
* Then, starting from this character, takes an optional initial plus or minus sign
* followed by as many numerical digits as possible, and interprets them as a numerical value.
*
* The string can contain additional characters after those that form the integral number,
* which are ignored and have no effect on the behavior of this function.
*
* If the first sequence of non-whitespace characters in str is not a valid integral number,
* or if no such sequence exists because either str is empty or it contains only whitespace characters,
* no conversion is performed.
*
* If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
* Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
* [−231,  231 − 1]. If the numerical value is out of the range of representable values,
* INT_MAX (231 − 1) or INT_MIN (−231) is returned.
* */

public class StringToInteger_8 {

    public static int myAtoi(String str) {
        if(str.length() == 0){
            return 0;
        }


        int i = 0;
        while(i < str.length() && str.charAt(i) == ' '){
            i++;
        }

        if(i < str.length() && !Character.isDigit(str.charAt(i)) && str.charAt(i) != '+' && str.charAt(i) != '-'){
            return 0;
        }

        boolean negative = false;
        boolean hasSign = false;

        // check optional sign
        if(i < str.length() && str.charAt(i) == '-'){
            negative = true;
            hasSign = true;
            i++;
        }
        else if(i < str.length() && str.charAt(i) == '+'){
            negative = false;
            hasSign = true;
            i++;
        }

        if(i < str.length() && hasSign && !Character.isDigit(str.charAt(i))){
            // after a sign there needs to be number
            return 0;
        }

        int result = 0;
        boolean haveEncounteredNumber = false; // keep track of whether we have seen a number already

        while(i < str.length()){
            if(haveEncounteredNumber && !Character.isDigit(str.charAt(i))){
                // we have seen a number and now encounter a char that is not a number
                break;
            }
            if(Character.isDigit(str.charAt(i))){
                haveEncounteredNumber = true;
                int digit = Character.getNumericValue(str.charAt(i));
                // check for possible overflow when adding this digit
                if(result > (Integer.MAX_VALUE - digit) /10){
                    if(negative){
                        return Integer.MIN_VALUE;
                    }
                    else{
                        return Integer.MAX_VALUE;
                    }
                }
                result = result*10 + digit;
            }
            i++;
        }

        if(negative){
            result = -result;
        }
        return result;
    }
}
