package problems.leetcode.leetcode41_60;

/*Implement pow(x, n), which calculates x raised to the power n (xn).
* -100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
* */

public class Pow_50 {
    public static double myPow1(double x, int n) {
        if(n < 0){
            return myPow1(1/x, -n);
        }
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return x;
        }
        else if(n%2 != 0){
            return myPow1(x,n-1)*x;
        }
        else{
            return myPow1(x*x, n/2);
        }
    }

    public static double myPow(double x, int n){
        double result = 1;
        if(n < 0){
            x = 1/x;
            // possibility of integer overflow when n is min integer
            if(n == Integer.MIN_VALUE){
                result *= x;
                n++;
            }
            n = -n;
        }

        while(n > 0){
            if(n%2 != 0){
                result *= x;
                n --;
            }
            else{
                x *= x;
                n /=2;
            }
        }
        return result;


    }

}
