package problems;


/*
* Given an array of n+2 elements, all in range 1 to n. All elements occur once except for two numbers. Find the
* two repeating numbers.
*
* solution 1: using two loops
* time complexity: O(n^2)
* auxiliary space: O(1)
*
* solution 2: counting
* time complexity: O(n)
* auxiliary space: O(n)
*
* solution 3: algebraic (maybe multiplication overflow)
* let T be the sum of integers 1 to n, and x and y the repeating numbers
* let S be the sum of the integers in the array
* let P be the product of the integers in the array
*
* T = n(n+1)/2
*
* S = n(n+1)/2 + x + y
* P = n! * x * y
*
*(x-y)^2 = x^2 - 2xy + y^2 = (x+y)^2 -4xy
* x - y = sqrt( (x+y)^2 -4xy )
*
* time complexity: O(n)
* auxiliary space: O(1)
*
* * */

public class DuplicatesInArray {

    public static void printRepeating1(int[] array){
        for(int i = 0; i < array.length; i++){
            for(int j = i+1; j < array.length; j++ ){
                if(array[i] == array[j]){
                    System.out.println(array[i]);
                }
            }
        }
    }


    public static void printRepeating2(int[] array){
        int[] counts = new int[array.length];
        for(int i = 0; i<array.length; i++){
            if(counts[array[i]] == 1){
                System.out.println(array[i]);
            }
            counts[array[i]]++;
        }
    }

    public static void printRepeating3(int[] array){
        // calculate sum and product
        int S = 0;
        int P = 1;
        for(int i = 0; i < array.length; i++){
            S = S + array[i];
            P = P * array[i];
        }

        int n = array.length -2;
        int fact = 1;
        for(int j = 1; j <=  n; j++){
            fact = fact*j;
        }

        int s = S - n*(n+1) / 2; // x+y
        int p = P / fact; // x*y

        int d = (int) Math.sqrt(s*s - 4*p); // x-y

        //x = d+y
        //s = d+y+y => y = (s-d)/2

        int y = (s-d)/2;

        System.out.println(y);
        System.out.println(d+y);

    }

    public static void main(String[] args){
        int[] array = {4,2,4,5,2,3,1};
        System.out.println("solution 1");
        printRepeating1(array);

        System.out.println("solution 2");
        printRepeating2(array);

        System.out.println("solution 3");
        printRepeating3(array);
    }
}
