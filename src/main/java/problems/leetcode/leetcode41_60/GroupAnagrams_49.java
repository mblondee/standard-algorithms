package problems.leetcode.leetcode41_60;

/*Given an array of strings, group anagrams together.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams_49 {
    public static List<List<String>> groupAnagrams1(String[] strs) {

        if(strs.length == 0){
            return new ArrayList<>();
        }

        //HashMap<String, List<String>> anagrams = new HashMap<>();
        HashMap<HashMap<Character, Integer>, List<String>> counts = new HashMap<>();


        for(String s: strs){
            HashMap<Character, Integer> current = new HashMap<>();
            for(int i = 0; i < s.length(); i++){
                if(current.containsKey(s.charAt(i))) {
                    current.put(s.charAt(i), current.get(s.charAt(i)) + 1);
                }
                else{
                    current.put(s.charAt(i),1);
                }
            }
            if(counts.containsKey(current)){
                // it is an anagram of another string
                counts.get(current).add(s);
            }
            else{
                List<String> newList = new ArrayList<>();
                newList.add(s);
                counts.put(current, newList);
            }

        }


        return new ArrayList<> (counts.values());

    }


    // time complexity: n*k*log k with k largest length of strings in strs
    public static List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0){
            return new ArrayList<>();
        }
        HashMap<String, List<String>> result = new HashMap<>();

        for(int i = 0; i < strs.length; i++){
            //sort string
            char[] stringArray = strs[i].toCharArray();
            Arrays.sort(stringArray);
            String stringSorted = new String(stringArray);
            if(result.containsKey(stringSorted)){
                result.get(stringSorted).add(strs[i]);
            }
            else{
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                result.put(stringSorted, newList);
            }
        }
        return new ArrayList(result.values());
    }
}
