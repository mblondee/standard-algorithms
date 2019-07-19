package problems.leetcode.leetcode1_20;

import java.util.*;

public class LetterCombinationsPhone_17 {

    public static List<String> letterCombinations(String digits) {

        int lengthOfStrings = digits.length();
        // phone[i] = lettercombination corresponding to i
        Map<Character, String> phone = new HashMap<>();
        phone.put('2', "abc");
        phone.put('3', "def");
        phone.put('4', "ghi");
        phone.put('5', "jkl");
        phone.put('6', "mno");
        phone.put('7', "pqrs");
        phone.put('8', "tuv");
        phone.put('9', "wxyz");

        List<String> result = new ArrayList<>();

        Queue<String> processing = new LinkedList<>();
        if(digits.length() == 0){
            return result;
        }
        processing.add("");

        while(!processing.isEmpty()){
            String current = processing.remove();
            int currentLength = current.length();
            if(currentLength == lengthOfStrings){
                // current is completed
                result.add(current);
            }
            else{
                // we have to look for correct digit in digits to at -> currentLength
                // and look for this digit in the phone dictionary
                String toAdd = phone.get(digits.charAt(currentLength));
                for(int i = 0; i < toAdd.length(); i++){
                    // push string back to queue with new character added at the end
                    processing.add(current + toAdd.charAt(i));
                }
            }

        }


        return result;
    }
}
