package problems.leetcode.leetcode1_20;

/*
* Given a 32-bit signed integer, reverse digits of an integer.
* */

public class ReverseInteger_7 {

    public static int reverse(int x) {

        boolean negative = false;
        if(x < 0){
            negative = true;
            x = -x;
        }

        int reversed = 0;
        // first digit to inspect
        int digit = x%10;

        // reverse x
        while(x != 0){
            reversed = reversed*10 + digit;
            x = x/10;
            // check if we can still continue without having overflow in next step
            if(x != 0 && reversed > (Integer.MAX_VALUE - digit)/10){
                return 0;
            }
            digit = x%10;
        }


        if(negative){
            reversed = - reversed;
        }
        return reversed;
    }
}
