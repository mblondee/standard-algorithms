package problems.leetcode.leetcode1_20;

/*
* Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
* */

import java.util.HashMap;

public class RomanToInteger_13 {
    public static int romanToInt(String s) {
        HashMap<Character, Integer> dictionary = new HashMap<>();
        dictionary.put('I',1);
        dictionary.put('V',5);
        dictionary.put('X',10);
        dictionary.put('L',50);
        dictionary.put('C',100);
        dictionary.put('D',500);
        dictionary.put('M',1000);

        int result = 0;
        int length = s.length();

        for(int i = 0; i < length; i++){
            int value = dictionary.get(s.charAt(i));

            // check what the next symbol is (if it exists)
            if(i+1 < length){
                int nextValue = dictionary.get(s.charAt(i+1));
                //IV, IX, XL, XC, CD, CM -> special cases
                // only cases where second is larger than first
                if(nextValue > value){
                    result = result + nextValue - value;
                    i++; // we skip one i
                }
                else{
                    result = result + value;
                }
            }

            else{
                result = result + value;
            }
        }
        return result;

    }
}
