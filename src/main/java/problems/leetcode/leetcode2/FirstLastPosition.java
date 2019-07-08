package problems.leetcode.leetcode2;

/*Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

        Your algorithm's runtime complexity must be in the order of O(log n).

        If the target is not found in the array, return [-1, -1].*/

public class FirstLastPosition {

    public static int[] searchRange(int[] nums, int target) {
        int index = binarysearch(nums, target, 0, nums.length-1);
        if(index == -1){
            return new int[]{index,index};
        }
        int pointer1 = index;
        int pointer2 = index;
        while(pointer1 > 0 && nums[pointer1-1] == target){
            pointer1--;
        }
        while(pointer2 < nums.length-1 && nums[pointer2+1] == target){
            pointer2++;
        }
        return new int[]{pointer1, pointer2};

    }

    private static int binarysearch(int[] nums, int target, int start, int end){
        if(end < start){
            return -1;
        }
        int mid = start + (end-start)/2;
        if(nums[mid] == target){
            return mid;
        }
        else if(nums[mid] < target){
            return binarysearch(nums, target, mid+1, end);
        }
        else{
            return binarysearch(nums, target, start, mid-1);
        }
    }
}
