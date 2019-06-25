package problems;

/*
* compute (approximate) the cube root of a double
*
* Newton's method
* calculate k-th root of x
* suppose you have an approximation y then the following is a better approximation
* ( x / y^{k-1} + (k-1)*y ) / k
* */

public class CubeRoot {

    private static double PRECISION = 0.0001;
    private static double guess = 1;

    public static double approximate(double x){
        while(Math.abs(Math.pow(guess, 3) - x) > PRECISION){
            guess = ( (x / Math.pow(guess, 2) + 2*guess)   )/3;
        }

        return guess;
    }

    public static void main(String[] args){
        System.out.println(approximate(8));
        System.out.println(approximate(8));
        System.out.println(approximate(27));
        System.out.println(approximate(1));
        System.out.println(approximate(20));
    }
}
