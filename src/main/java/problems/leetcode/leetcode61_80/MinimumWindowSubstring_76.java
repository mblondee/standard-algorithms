package problems.leetcode.leetcode61_80;

/*Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
*
* If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.*/



import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring_76 {
    public static String minWindow(String s, String t) {
        if(t.length() == 0 || s.length() < t.length()){
            return "";
        }

        Map<Character, Integer> charsInT = new HashMap<>();

        for(int i = 0; i < t.length(); i++){
            if(charsInT.containsKey(t.charAt(i))){
                charsInT.put(t.charAt(i), charsInT.get(t.charAt(i)) + 1);
            }
            else{
                charsInT.put(t.charAt(i), 1);
            }
        }

        // sliding window parameters
        int start = 0;
        int end = 0;

        // actual start and end index (end index not included)
        int startIndex = -1;
        int endIndex = -1;
        int counter = t.length(); // chars that have to be matched
        int minLength = Integer.MAX_VALUE; // we need to keep track of current length of window

        while(end < s.length()){

            // current is in t
            if(charsInT.containsKey(s.charAt(end))){
                charsInT.put(s.charAt(end), charsInT.get(s.charAt(end))-1);
                // if this means that the count of this character is negative, then we have an unnecessary duplicate char
                // we only update counter if this is not the case
                if(charsInT.get(s.charAt(end)) >= 0) {
                    counter--;
                }
            }

            end++;


            // suppose at this pointer we have matched all characters from t
            if(counter == 0){
                // trim window from left to right
                // ignore chars that are not in t
                // ignore unnecessary chars
                while(start < end){
                    if(charsInT.containsKey(s.charAt(start))){
                        if(charsInT.get(s.charAt(start)) >= 0){
                            // this a char in t that we need
                            break;
                        }
                        else{
                            // unnecessary char from t
                            charsInT.put(s.charAt(start), charsInT.get(s.charAt(start)) + 1);
                            start++;
                        }
                    }
                    else{
                        // char not in t
                        start++;
                    }
                }

                // is current window smaller than the one we already had? (minLength)
                if(end-start < minLength){
                    minLength = end-start;
                    startIndex = start;
                    endIndex = end;
                }

                // slide window: if it still exists, next char must be such that it is in t and it was an unnecessary one
                if(start < end){
                    assert(charsInT.containsKey(s.charAt(start)) && charsInT.get(s.charAt(start)) >= 0);
                    charsInT.put(s.charAt(start), charsInT.get(s.charAt(start)) + 1);
                    counter++;
                    start++;
                }

                // trim more if possible
                while(start < end && ! charsInT.containsKey(s.charAt(start))){
                    start++;
                }


            }
        }



        if(startIndex == -1){
            // no window found
            return "";
        }

        StringBuffer str = new StringBuffer();
        for(int i = startIndex; i < endIndex; i++){
            str.append(s.charAt(i));
        }

        return str.toString();





    }
}
