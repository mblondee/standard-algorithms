package problems.leetcode.leetcode41_60;

/*The set [1,2,3,...,n] contains a total of n! unique permutations.

        By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

        "123"
        "132"
        "213"
        "231"
        "312"
        "321"
        Given n and k, return the kth permutation sequence.

        Note:

        Given n will be between 1 and 9 inclusive.
        Given k will be between 1 and n! inclusive.*/

// idea there are 2 strings starting with 1 and the same holds for 2 en 3
// hence for n! permutations for each i =  1...n there are (n-1)! strings that start with i
// if we want to know the k-th permutation (index = k-1, since we will start with zero)
// index / (n-1)! will point to the first number
// we repeat this with n = n-1 and k = k % (n-1)!

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence_60 {
    public static String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i <=n ; i++){
            numbers.add(i);
        }

        // compute (n-1)!
        int fact = 1;
        for(int i = 1; i <= n-1; i++){
            fact *= i;
        }

        // we start from index 0
        int index = k-1;

        StringBuffer sb = new StringBuffer();

        // adding all numbers from numbers
        while(! numbers.isEmpty()){
            int num = index/fact;
            sb.append(numbers.get(num));
            numbers.remove(num);
            index = index % fact;
            if(numbers.size() != 0) {
                fact /= numbers.size();
            }
        }

        return sb.toString();



    }
}
