package problems.leetcode.leetcode21_40;

/*
* Implement strStr(). (indexOf())

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*
* example:
* Input: haystack = "hello", needle = "ll"
Output: 2
*
* time complexity: O(m * n) with m and n length of string haystack and needle
*
* efficient algorithm is KMP: time complexity O(m+n)
* works better in cases where there are many matching characters followed by a mismatch
* preprocess string: for every index how much should we skip before next iteration (in naive just +1)
* array lps
* lps[i] the length of the longest proper prefix in needle[0..i] (i included) that is a proper suffix in needle[0..i]
* eg: needle = "abababca"
* lps = [0,0,1,2,3,4,0,1]
* i = 0 "a" 0
* i = 1 "ab" 0
* i = 2 "aba" 1 a
* i = 3 "abab" 2 ab
* i = 4 "ababa" 3 aba
* i = 5 "ababab" 4 abab
* i = 6 "abababc" 0
* i = 7 "abababca" 1 a
*
* haystack “bacbababaabcbab”
* example partial match i = 4 "ababa" (length 5)
* question: in "ababa" is there a prefix that is also a suffix, yes -> lps[4] = 3
* we can skip the 3 first chars in "ababa": skip: length - lps[length-1]
* */

public class ImplementstrStr {
    public static int strStr(String haystack, String needle) {
        if(needle.length() == 0){
            return 0;
        }
        // all possible starting points for needle in haystack
        int index = 0;


        while(index <= haystack.length() - needle.length()){
            if(haystack.charAt(index) == needle.charAt(0)){
                // we have found possible start
                int pointer = 1;
                while(pointer < needle.length()){
                    if(haystack.charAt(index+pointer) == needle.charAt(pointer)){
                        pointer++;
                    }
                    else{
                        break;
                    }
                }
                if(pointer == needle.length()){
                    return index;
                }
            }

            index++;

        }

        return -1;

    }

    public static int strStrKMP(String haystack, String needle) {
        if(needle.length() == 0){
            return 0;
        }

        int[] lps = computeArray(needle);

        int indexHaystack = 0;
        int indexNeedle = 0;


        while(indexHaystack <= haystack.length() - needle.length()){
            if(indexNeedle == needle.length()){
                return indexHaystack;
            }

            if(haystack.charAt(indexHaystack + indexNeedle) == needle.charAt(indexNeedle)){
                indexNeedle++;
            }
            else{
                //partialMatchLength = indexNeedle + 1;
                if(lps[indexNeedle] == 0){
                    indexHaystack++;
                    indexNeedle = 0;
                }
                else{
                    indexHaystack += lps[indexNeedle];
                    indexNeedle = 0;
                }

            }

        }


        return -1;

    }

    public static int[] computeArray(String needle){
        int[] lps = new int[needle.length()];
        lps[0] = 0; // there is no proper prefix for a string of length 1
        int i = 1;
        int previous = 0; // for i previous is lps[i-1]
        while(i < needle.length()){

            // eg "abababca"
            // (lps = [0,0,1,2,3,4,0,1])
            //previous = lps[i-1];
            // eg i = 4, subpattern "ababa"
            // previous = lps[3] = 2 subpatternprevious "abab" proper prefix "ab" and ends proper suffix with "ab"
            // a == subpattern[previous] == subpattern[i] => proper prefix "aba" and proper suffix "aba"
            if(needle.charAt(previous) == needle.charAt(i)){
                lps[i] = previous + 1;
                previous++;
                i++;
            }
            else{
                //eg i = 6
                // previous = lps[5] = 4 subpatternprevious (i=5) "ababab" proper prefix "abab" and proper suffix "abab"
                //needle.charAt(4) == a and needle.charAt(6) == c
                // length of longest prefix that is suffix cannot increase but maybe it can decrease!
                // -> keep same i and check with previous = lps[previous-1]
                // previous = lps[3] = 2 : check if "aba" is prefix that is also suffix in "abababc"
                // previous = lps[2] = 1 : check if "ab" is prefix that is also suffix in "abababc"
                // previous = lps[1] = 0 : hence: 0
                if (previous != 0) {
                    previous = lps[previous -1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }

            }



        }

        return lps;

    }
}
