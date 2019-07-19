package problems.leetcode.leetcode4;

/*Given two binary strings, return their sum (also a binary string).

        The input strings are both non-empty and contains only characters 1 or 0.*/

public class AddBinary_67 {
    public static String addBinary(String a, String b) {
        int aIndex = a.length()-1;
        int bIndex = b.length()-1;

        int carry = 0; // carry is max 1
        StringBuffer result = new StringBuffer();

        while(aIndex >= 0 && bIndex >= 0){
            // res is 0,1,2 or 3
            int res = a.charAt(aIndex) - '0' + b.charAt(bIndex) - '0' + carry;
            result.append(res%2);
            carry = res /2;
            aIndex--;
            bIndex--;
        }

        while(aIndex >= 0){
            int res = a.charAt(aIndex) - '0' + carry;
            result.append(res%2);
            carry = res /2;
            aIndex--;
        }

        while(bIndex >= 0){
            int res = b.charAt(bIndex) - '0' + carry;
            result.append(res%2);
            carry = res /2;
            bIndex--;
        }

        if(carry != 0){
            result.append(carry);
        }

        return result.reverse().toString();
    }
}
