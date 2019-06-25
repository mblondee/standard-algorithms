package problems.leetcode.leetcode1;

/*
* Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".
* */

// time complexity: O(nm) with m max length of the strings
// for loop in longestCommonPrefix: n times
// each iteration in for loop : at most length k of shortest string (of the two)
// stringbuilder adding

public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        for(int i = 1; i <strs.length; i++){
            prefix = commonPrefix(prefix, strs[i]);
        }

        return prefix;

    }

    public static String commonPrefix(String str1, String str2){
        int length1 = str1.length();
        int length2 = str2.length();

        StringBuilder str = new StringBuilder();

        for(int i = 0; i < length1 && i < length2; i++){
            if(str1.charAt(i) != str2.charAt(i)){
                break;
            }
            else{
                str.append(str1.charAt(i));
            }
        }
        return str.toString();
    }
}
