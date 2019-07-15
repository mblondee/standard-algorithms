package problems.leetcode.leetcode3;

/*Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

        If the last word does not exist, return 0.

        Note: A word is defined as a character sequence consists of non-space characters only.*/

public class LengthOfLastWord_58 {
    public static int lengthOfLastWord(String s) {
        int length = 0;
        if(s.length() == 0){
            return length;
        }

        int i = s.length()-1;
        while(i >=0  && s.charAt(i) == ' '){
            i--;
        }

        while(i >=0  && s.charAt(i) != ' '){
            length++;
            i--;
        }
        return length;

    }
}
