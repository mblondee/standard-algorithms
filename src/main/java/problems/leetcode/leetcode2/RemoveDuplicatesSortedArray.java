package problems.leetcode.leetcode2;

/*
* Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
* */

public class RemoveDuplicatesSortedArray {

    public static int removeDuplicates(int[] nums) {


        int current = 0;
        int nextToPut = 0;

        while(current < nums.length){
            if(nextToPut == nums.length){
                break;
            }
            while(nextToPut +1 < nums.length && nums[nextToPut] == nums[nextToPut + 1]){
                nextToPut++;
            }
            nums[current] = nums[nextToPut];
            nextToPut++;
            current++;
        }
        return current;

    }
}
