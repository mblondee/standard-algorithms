package problems.leetcode.leetcode3;

/*
* Given an unsorted integer array, find the smallest missing positive integer.
* */

/*
* convert array A such that
* A[0] = 1
* A[1] = 2
* ...
* for each possible index i in A: try to find i+1 in A and put it in A[i]
* 1) check if A[i] = i+1
* 2) if not A[i] = k, then A[k-1] should be k, is k-1 valid index for array?
* 3) if so swap should that A[k-1] = A[i]
*
* all positive numbers will be in their correct place, i.e A[l] = l+1
*
* the first i such that A[i] != i+1 represents the missing positive number i+1
* */

public class FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {

        if(nums.length == 0){
            return 1;
        }


        for(int index = 0; index < nums.length; index++) {
            while (index < nums.length && nums[index] != index + 1 && nums[index] - 1 >= 0
                    && nums[index] - 1 < nums.length && nums[nums[index] -1 ] != nums[index]) {
                    int toSwap = nums[nums[index] - 1];
                    nums[nums[index] - 1] = nums[index];
                    nums[index] = toSwap;
            }
        }
        int firstMssing = 0;

        while(firstMssing < nums.length && nums[firstMssing] == firstMssing + 1){
            firstMssing++;
        }

        return firstMssing+1;
    }
}
