package problems.leetcode.leetcode21_40;

/*
You are given a string, s, and a list of words, words, that are all of the same length.
Find all starting indices of substring(s) in s that is a concatenation of each word in
words exactly once and without any intervening characters. Order does not matter.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringConcatenation {
    public static List<Integer> findSubstring(String s, String[] words) {
        int numberOfWords = words.length;
        int stringLength = s.length();

        List<Integer> result = new ArrayList<>();
        if(numberOfWords == 0 || stringLength == 0){
            return result;
        }

        // each word in words has same length
        int wordLength = words[0].length();

        // expected words with expected count
        Map<String, Integer> allWords = new HashMap<>();
        for(String word : words){
            if(allWords.containsKey(word)){
                allWords.put(word, allWords.get(word) +1);
            }
            else{
                allWords.put(word, 1);
            }
        }

        // checking all indices where the concatenated words can start
        loopString:
        for(int index = 0; index <= stringLength-numberOfWords*wordLength; index++){
            // keep track of encountered words
            Map<String, Integer> currentWords = new HashMap<>();
            int wordsFound = 0;
            for(int j = 0; j < numberOfWords*wordLength; j+= wordLength){
                String current = s.substring(index+j, index+j+wordLength);
                if(allWords.get(current) == null){
                    continue loopString;
                }
                else if(currentWords.get(current) == null){
                    currentWords.put(current,1);
                }
                else{
                    currentWords.put(current, currentWords.get(current) + 1);
                }

                // current word has been found too many times
                if(currentWords.get(current) > allWords.get(current)){
                    continue loopString;
                }

                // if not we have found an extra word
                wordsFound++;

                // of number of words equals numberOfWords we have found an index that satisfies
                // the conditions since all words don't exceed the expected count
                if(wordsFound == numberOfWords){
                    result.add(index);
                }

            }
        }




        return result;
    }
}
