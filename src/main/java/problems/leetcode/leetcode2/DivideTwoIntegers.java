package problems.leetcode.leetcode2;

/*Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

        Return the quotient after dividing dividend by divisor.

        The integer division should truncate toward zero.

   Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
[−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.*/

/*
* binary division:
* 1) align leftmost digits in dividend and divisor such that dividend >= divisor
* 2)a) if dividend >= divisor : shift divisor one back, put 0 in quotient (most right), divisor shift
* 2)b) if dividend < divisor : shift divisor one back, put 1 in quotient (most right), dividend = dividend - divisor
* 3) repeat 2)
* */

public class DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {


        boolean positive = true;
        if(dividend>0 && divisor< 0 || dividend < 0 && divisor > 0){
            positive = false;
        }

        if(divisor == Integer.MAX_VALUE && dividend == Integer.MAX_VALUE){
            return 1;
        }
        else if(divisor == Integer.MAX_VALUE && dividend == Integer.MIN_VALUE){
            return -1;
        }
        else if(divisor == Integer.MIN_VALUE && dividend == Integer.MAX_VALUE){
            return 0;
        }
        else if(divisor == Integer.MIN_VALUE && dividend == Integer.MIN_VALUE){
            return 1;
        }
        else if(divisor == Integer.MAX_VALUE || divisor == Integer.MIN_VALUE){
            return 0;
        }



        if(Math.abs(divisor) == 1 && (dividend == Integer.MAX_VALUE || dividend == Integer.MIN_VALUE)){
            if(positive){
                return Integer.MAX_VALUE;
            }
            else{
                return Integer.MIN_VALUE;
            }

        }

        int quotient = 0;
        int keepTrack = 1; // to know how many bits the result will have
        boolean addOne = false;
        boolean subtractOne = false;


        if (dividend == Integer.MIN_VALUE) {
            // problem: there is one more min value than max values -> absolute values are not the same
            // min/div = q =>  (min+div)/div = q+1
            if(divisor > 1) {
                // min+d ->  no overflow
                // min/d = (min+d-d)/d = (min+d)/d -1
                // compute (min+d)/d and subtract one at the end
                dividend += divisor;
                subtractOne = true;
            }
            else{
                // min-d ->  no overflow
                // min/d = (min-d+d)/d = (min-d)/d +1
                // compute (min-d)/d and add one at the end
                dividend -= divisor;
                addOne = true;
            }
        }

        divisor = Math.abs(divisor);
        dividend = Math.abs(dividend);



        boolean haveToGoOneBack = true;

        // bitwise shift divisor to the left until divisor > dividend
        while(dividend >= divisor){
            if(divisor << 1 < 0){
                haveToGoOneBack = false;
//                divisor >>= 1;
//                keepTrack >>= 1;
                break;
            }
            divisor <<= 1;
            keepTrack <<=1;
        }

        if(haveToGoOneBack) {
            // now we need to go one back to the right to get the biggest number by which dividend can be divided
            divisor >>= 1;
            keepTrack >>= 1; // if divisor is larger than dividend keepTrack will be 0
        }

        while(keepTrack != 0) {
            if(dividend >= divisor) {
                //subtract divisor from dividend
                dividend -= divisor;
                //put 1 in correct spot in quotient (the 1 in keeptrack is the current position)
                quotient = quotient | keepTrack;
            }
            // put 0 in correct spot in quotient (there was already 0 -> do nothing)
            divisor >>= 1;
            keepTrack >>= 1; // no 1 was added in current position
        }


        if(! positive){
            quotient = -quotient;
        }

/*        if(problemCase){
            quotient--;
        }*/

if(subtractOne){
    quotient--;
}

if(addOne){
    quotient++;
}




        return quotient;

    }
}
