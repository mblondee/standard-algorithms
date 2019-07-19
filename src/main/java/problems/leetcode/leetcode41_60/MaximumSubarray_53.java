package problems.leetcode.leetcode41_60;

/*Given an integer array nums, find the contiguous subarray (containing at least one number)
        which has the largest sum and return its sum.*/

// dynamic programming
// sums[i] = max sum of subarray ending in i (starting somewhere before)
// max number in sums will contain the answer

// sums[i-1] is max sum until i-1,
// so when looking for max sum ending in i
// either is it sums[i-1] + nums[i] and the sum cannot be made larger by not considering
// all indices that were used in sums[i-1] (because it was the max sum)
// or it is the subarray starting and ending in i

// because sums[i] only depends on sums[i-1] it can be stored in a variable

public class MaximumSubarray_53 {
    public static int maxSubArray(int[] nums) {
        //int[] lengths = new int[nums.length];
        int previous = nums[0];
        int maxSum = previous;

        for(int i = 1; i < nums.length; i++){
            previous = Math.max(previous + nums[i], nums[i]);
            if(previous > maxSum){
                maxSum = previous;
            }
        }

        return maxSum;

    }
}
