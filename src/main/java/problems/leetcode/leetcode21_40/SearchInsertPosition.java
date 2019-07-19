package problems.leetcode.leetcode21_40;
/*
Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.*/

public class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        if(nums.length == 0){
            return 0;
        }
        return searchInsert(nums, target, 0, nums.length);

    }

    private static int searchInsert(int[] nums, int target, int start, int end){
        if(start == end){
            return start;
        }

        int mid = start + (end-start)/2;
        if(nums[mid] == target){
            return mid;
        }
        else if(nums[mid] > target){
            return searchInsert(nums, target, 0, mid);
        }
        else{
            return searchInsert(nums, target, mid+1, end);
        }
    }
}
