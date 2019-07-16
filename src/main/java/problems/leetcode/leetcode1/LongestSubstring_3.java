package problems.leetcode.leetcode1;

/*
* Given a string, find the length of the longest substring without repeating characters.
* */

/*
* time complexity O(n): worst case every character is visited twice
* space complexity O(min(m,n)) where m is the size of the char set (space for visited is bounded by n and m)
* */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring_3 {

    public static int lengthOfLongestSubstring(String s) {

        if(s.length() == 0){
            return 0;
        }

        int maxLenght = 0;

        Map<Character, Integer> visited = new HashMap<>();


        for (int start = 0, current = 0; current <= s.length(); current++) {
            if(current == s.length()){
                if (current - start > maxLenght) {
                    maxLenght = current - start;
                }
                return maxLenght;
            }
            Character currentChar = s.charAt(current);
            if (visited.containsKey(currentChar) && visited.get(currentChar) >= start) {
                // a repeating character has been found between start and current
                // check if word start -> current-1 is larger than maxLength
                if (current - start > maxLenght) {
                    maxLenght = current - start;
                }
                // find new word starting from visited.get(currentChar) + 1
                start = visited.get(currentChar) + 1;
            }
            visited.put(currentChar, current);

        }
        return maxLenght;
    }
}
