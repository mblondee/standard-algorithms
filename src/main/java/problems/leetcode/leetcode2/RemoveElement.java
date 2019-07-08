package problems.leetcode.leetcode2;

/*
* Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
* */

public class RemoveElement {
    public static int removeElement(int[] nums, int val) {
        int current = 0;
        int nextToKeep = 0;
        while(nextToKeep < nums.length){
            if(nums[nextToKeep] == val){
                //nums[nextToKeep] == val -> skip
                nextToKeep++;
            }
            else {
                nums[current] = nums[nextToKeep];
                current++;
                nextToKeep++;
            }
            }
        return current;

    }
}
