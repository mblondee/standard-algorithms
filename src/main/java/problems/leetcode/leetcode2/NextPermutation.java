package problems.leetcode.leetcode2;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

        If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

        The replacement must be in-place and use only constant extra memory.

        Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

        1,2,3 → 1,3,2
        3,2,1 → 1,2,3
        1,1,5 → 1,5,1*/

/*
* next smallest number can be obtained by searching from the right (otherwise might skip numbers smaller)
* we look for first digit (index d) such that the digit in index d-1 is smaller
* (if does not exist -> **)
* this means that we can swap d-1 with something greater to make to overall number greater
* eg 12365
* d = 3, d-1 = 2 -> 3 can we swapped with at least one greater number to the right from it
* to find which one: swap with d = 3 and then sort subarray array[3.. end]
*
* ** sort subarray [0 ... end]
* */

import java.util.Arrays;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {

        if(nums.length == 0 || nums.length == 1){
            return;
        }

        // find index d such that it is the first from the right for which nums[d-1] < nums[d] holds
        // example 123653, for 653 there is no way to make it bigger
        // for 3653 there is way to make it bigger
        int d;
        for( d= nums.length -1; d>0; d--){
            if(nums[d] > nums[d-1]){
                break;
            }
        }

        // if d=0 it means that the array was sorted descending, hence there is no greater number
        // if d!=0 we need to rearrange it
        if(d != 0){
            // example 123653 -> d= 3 nums[d] = 6, nums[d-1] = 3
            // we want the first rearrangement of 3653 that is greater than 3653
            // swap 3 with smallest of 6,5,4,3 (strictly greater than 3)
            // 5633 and now sort 633
            // 125336
            // this min will always exist, otherwise we would in the case where d == 0
            int indexMin = d;
            for(int j = d+1; j< nums.length; j++){
                if(nums[j] > nums[d-1] && nums[j] < nums[indexMin]){
                    indexMin = j;
                }
            }

            int toSwap = nums[indexMin];
            nums[indexMin] = nums[d-1];
            nums[d-1] = toSwap;
        }

        Arrays.sort(nums, d, nums.length);

    }
}
