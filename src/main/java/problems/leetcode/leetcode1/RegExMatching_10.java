package problems.leetcode.leetcode1;

/*
* Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
* */


// recursive solution -> check first 2 chars of p and first of s


public class RegExMatching_10 {
    public static boolean isMatch(String s, String p) {
        return helper(s,p,0,0,s.length(),p.length());

    }

    public static boolean helper(String s, String p, int sIndex, int pIndex, int sLength, int pLength){
        // if p empty
        if(pLength == pIndex){
            return sLength == sIndex;
        }

        // p has length 1
        if(pLength - pIndex == 1){
            // p contains only a wildcard
            if(p.charAt(pIndex) == '.'){
                return sLength - sIndex == 1;
            }
            // p contains a character
            else if(sLength - sIndex != 1){
                return false;
            }
            else{
                return p.charAt(pIndex) == s.charAt(sIndex);
            }
        }

        // p has at least length 2
        // second char is not '*'
        if(p.charAt(pIndex + 1) != '*'){
            if(sLength == sIndex){
                // s is leeg
                return false;
            }
            else if(p.charAt(pIndex) == '.'){
                return helper(s,p, sIndex+1, pIndex+1, sLength, pLength);
            }
            else if(s.charAt(sIndex) != p.charAt(pIndex)){
                return false;
            }
            else{
                return helper(s,p,sIndex+1,pIndex+1, sLength, pLength);
            }
        }

        // second char is '*'

        else{
            // case where there is no element
            if(helper(s,p, sIndex, pIndex+2, sLength, pLength)){
                return true;
            }
            if(p.charAt(pIndex) == '.'){
                for(int k = sIndex; k <= sLength; k++){
                    if(helper(s,p,k, pIndex+2, sLength, pLength)){
                        return true;
                    }
                }
            }
            else{
                for(int k = sIndex; k < sLength; k++){
                    if(p.charAt(pIndex) == s.charAt(k)){
                        if(helper(s,p, k+1, pIndex+2, sLength, pLength)){
                            return true;
                        }
                    }
                    else{
                        break;
                    }
                }
            }


        }
        return false;

    }
}
