package problems.leetcode.leetcode4;

/*Validate if a given string can be interpreted as a decimal number.

        Some examples:
        "0" => true
        " 0.1 " => true
        "abc" => false
        "1 a" => false
        "2e10" => true
        " -90e3   " => true
        " 1e" => false
        "e3" => false
        " 6e-1" => true
        " 99e2.5 " => false
        "53.5e93" => true
        " --6 " => false
        "-+3" => false
        "95a54e53" => false*/


// valid number:
// 1) white spaces at beginning and end of string
// 2) + or -: either at start or preceded by e
// 3) . and e:
//     - can only be there once
//     - e cannot be at the beginning
//     - number after e has to be an integer
//     - e and i cannot be successive

public class ValidNumber_65 {
    public static boolean isNumber(String s) {
        if(s == null || s.isEmpty()){
            return false;
        }
        // if only one character
        if(s.length() == 1 && (s.charAt(0) < '0' || s.charAt(0) > '9') ){
            return false;
        }

        int start = 0;
        int end = s.length()-1;

        // remove white spaces
        while(start < end && s.charAt(start) == ' '){
            start++;
        }

        while(end > start && s.charAt(end) == ' '){
            end--;
        }

        // only one char: has to be numerical value
        if(start == end && (s.charAt(start) < '0' || s.charAt(start) > '9')){
            return false;
        }



        // + or - must be at beginning
        if(s.charAt(start) == '+' || s.charAt(start) == '-'){
            if(start + 1 == end && s.charAt(start+1) == '.' ){
                // if followed by a ., there must follow numbers
                return false;
            }
            start++;
        }

        //index of point and e
        int point = -1;
        int e = -1;

        // check remaining char
        for(int i = start; i <= end; i++){
            // non numerical char are not allowed (except . and e)
            // + and - are allowed if they are preceded by e andnot at the end
            if((s.charAt(i) < '0' || s.charAt(i) > '9') && s.charAt(i) != '.' && s.charAt(i) != 'e'){
                if((s.charAt(i) == '+' || s.charAt(i) == '-') && e == i-1 && i != end) {
                    continue;
                }
                return false;
            }

            if(s.charAt(i) == '.'){
                if(point == -1){
                    point = i;
                }
                else{
                    return false; // we cannot have two points
                }
            }

            if(s.charAt(i) == 'e'){
                if(e == -1){
                    e = i;
                }
                else{
                    return false;
                }
            }

            // e cannot be at the start or end
            if(e == end || e == start){
                return false;
            }
            // part after e has to be an integer (no point)
            if(e!=-1 && point > e){
                return false;
            }
            // part before e has to be a valid number
            if(point == start && e== start +1){
                return false;
            }


        }
        return true;

    }
}
