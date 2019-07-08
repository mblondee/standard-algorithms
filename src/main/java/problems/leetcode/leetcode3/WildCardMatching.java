package problems.leetcode.leetcode3;

/*Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

        '?' Matches any single character.
        '*' Matches any sequence of characters (including the empty sequence).
        The matching should cover the entire input string (not partial).

        Note:

        s could be empty and contains only lowercase letters a-z.
        p could be empty and contains only lowercase letters a-z, and characters like ? or *.*/

public class WildCardMatching {

    // this one does too many backtracking
    public static boolean isMatch1(String s, String p) {
        return helper(s,p,0,0, s.length(), p.length());
    }

    public static boolean helper(String s, String p, int indexs, int indexp, int lengths, int lengthp){
        if(indexp == lengthp){
            //p is empty
            return indexs == lengths;
        }
        //p has at least length 1
        else if(p.charAt(indexp) == '*'){
            for(int i = indexs; i <= lengths; i++) {
                if (helper(s, p, i, indexp + 1, lengths, lengthp)) {
                    return true;
                }
            }
        }
        // if s is empty at this point there cannot be a match
        else if(indexs == lengths){
            return false;
        }
        else if (p.charAt(indexp) == '?'){
            return helper(s,p, indexs+1, indexp+1, lengths, lengthp);
        }
        else if (s.charAt(indexs) != p.charAt(indexp)){
            return false;
        }
        else{
            return helper(s,p, indexs+1, indexp+1, lengths, lengthp);
        }
        return false;
    }


    public static boolean isMatch(String s, String p) {
        int sindex = 0;
        int pindex = 0;
        int starIndex = -1; // when encountered a *, save index of * in p
        int sStar = 0; // when encountered a *, save index in s


        while(sindex < s.length()){
            // advance both pointer is char match or in p there is a '?'
            if(pindex < p.length() && (p.charAt(pindex) == '?' || p.charAt(pindex) == s.charAt(sindex))){
                sindex++;
                pindex++;
            }
            // a * has been found -> only advance pindex
            // keep track of index in p that has * and to where it was matched in s
            else if (pindex < p.length() && p.charAt(pindex) == '*'){
                starIndex = pindex;
                sStar = sindex;
                pindex++;
            }
            else if(starIndex != -1){
                // we may advance as long as the next char in pindex is another char
                pindex = starIndex +1;
                sStar ++;
                sindex = sStar;
            }
            else{
                return false;
            }



        }

        // check for remaining char in p
        // if there are remaining char they may only be *
        // advance pointer if this is the case
        while(pindex < p.length() && p.charAt(pindex) == '*'){
            pindex++;
        }
        // if there are still char left there is no match
        return pindex == p.length();
    }




}
