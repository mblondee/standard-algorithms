package problems.leetcode.leetcode1_20;

/*
* Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
* */

public class PalindromeNumber_9 {
    public static boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }

        // to get leading digit: if x has m digits, them x/10^m will give the leading digit
        int divisor = 1;
        while(x/divisor >= 10){
            divisor *= 10;
        }


        while(divisor > 1){
            int leadingDigit = x / divisor;
            int lastDigit = x%10;

            if(leadingDigit != lastDigit){
                return false;
            }

            //remove leading and last digit
            x = x-(divisor*leadingDigit);
            x = x/10;

            //update leading and last digit
            divisor = divisor/100; // we have removed two digits (since divisor > 1 we had at least two digits)

        }
        return true;


    }
}
