package problems.leetcode.leetcode1;

/*
* Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
*
* For example, two is written as II in Roman numeral, just two one's added together.
* Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
*
* Roman numerals are usually written largest to smallest from left to right. However,
* the numeral for four is not IIII. Instead, the number four is written as IV.
* Because the one is before the five we subtract it making four. The same principle applies to the number nine,
* which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
*
* Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
* */

import java.util.HashMap;

public class IntegerToRoman_12 {

    public static String intToRoman(int num) {
        StringBuilder str = new StringBuilder();
        HashMap<Integer, Character> dictionary = new HashMap<>();
        dictionary.put(1, 'I');
        dictionary.put(5, 'V');
        dictionary.put(10, 'X');
        dictionary.put(50, 'L');
        dictionary.put(100, 'C');
        dictionary.put(500, 'D');
        dictionary.put(1000, 'M');

        // get first digit in num
        int divisor = 1;
        while (num / divisor >= 10) {
            divisor *= 10;
        }

        while(num !=0) {
            int leadingDigit = num / divisor;

            if(leadingDigit < 4){
                // we need to add leadingDigit times the letter corresponding to divisor to the string
                while(leadingDigit >0){
                    str.append(dictionary.get(divisor));
                    leadingDigit--;
                }
            }
            else if(leadingDigit == 4){
                //we need to add divisor and 5*divisor to the string
                str.append(dictionary.get(divisor));
                str.append(dictionary.get(5*divisor));
            }
            else if(leadingDigit <9){
                // we need to add 5*divisor to the string
                str.append(dictionary.get(5*divisor));
                // we need to add as many divisors as given by leadingDigit
                while(leadingDigit > 5){
                    str.append(dictionary.get(divisor));
                    leadingDigit--;
                }
            }
            else if (leadingDigit == 9){
                // we need to add divisor and divisor*10
                str.append(dictionary.get(divisor));
                str.append(dictionary.get(10*divisor));
            }
            num = num%divisor;
            divisor = divisor/10;
        }
        return str.toString();

    }



}
