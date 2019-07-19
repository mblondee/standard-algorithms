package problems.leetcode.leetcode1_20;

/*
* Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
* */

/*
* time complexity O(n^2)
* space complexity O(1)
* */

public class LongestPalindromicSubstring_5 {

    public static String longestPalindrome(String s) {
        if(s.length()== 0){
            return "";
        }

        int longestLength = 1;
        int startLongest = 0;

        int checkingLeft;
        int checkingRight;

        for(int current = 0; current < s.length(); current++){
            // find even palindrome around current and current+1
            checkingLeft = current;
            checkingRight = current + 1;
            while(checkingLeft >=0 && checkingRight < s.length() && s.charAt(checkingLeft)==s.charAt(checkingRight)){
                if(checkingRight - checkingLeft +1 > longestLength){
                    longestLength = checkingRight - checkingLeft + 1;
                    startLongest = checkingLeft;
                }
                checkingLeft--;
                checkingRight++;
            }



            //find odd palindrome around current
            checkingLeft = current -1;
            checkingRight = current +1;
            while(checkingLeft >=0 && checkingRight < s.length() && s.charAt(checkingLeft)==s.charAt(checkingRight)){
                if(checkingRight - checkingLeft + 1 > longestLength){
                    longestLength = checkingRight - checkingLeft + 1;
                    startLongest = checkingLeft;
                }
                checkingLeft--;
                checkingRight++;
            }

        }

        StringBuffer longestPalindrome = new StringBuffer();
        for(int i = 0; i < longestLength; i++){
            longestPalindrome.append(s.charAt(startLongest+i));
        }

        return longestPalindrome.toString();
    }
}
