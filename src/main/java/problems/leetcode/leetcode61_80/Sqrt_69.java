package problems.leetcode.leetcode61_80;

/*Implement int sqrt(int x).

        Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

        Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.*/

public class Sqrt_69 {
    public static int mySqrt(int x) {
        if(x == 0 || x == 1){
            return x;
        }
        // else 1 < sqrt(x) < x -> do binary search
        // x > 0: sqrt(x) <= x/2 <=> 4 x <= x^2 <=> x(4-x) <= 0 <=> 4-x <= 0 <=> 4 <= x
        // guess sqrt(x) <= x/2 +2
        // proof: 2 sqrt(x) <= x+ 4 <=> 4x <= x^2 + 8x + 16 <=> 0 <= x^2 + 4x + 16 <=> 0 <= (x+2)^2 + 12

        int left = 1;
        int right = x/2 + 2;
        int guess = 0;

        while(left <= right){
            int mid = left + (right-left)/2;
            if(mid == x/mid){
                return mid;
            }
            else if(mid > x/mid){
                right = mid-1;
            }
            else if(mid > guess){
                guess = mid; // it could be mid (we need floor), if we cannot get a better guess we will return this value
                left = mid+1; // try to find a better guess

            }
        }

        return guess;





    }


}
