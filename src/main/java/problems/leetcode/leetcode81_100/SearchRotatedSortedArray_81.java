package problems.leetcode.leetcode81_100;

/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

        (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

        You are given a target value to search. If found in the array return true, otherwise return false.*/

// worst case time complexity: O(n)
// maybe we always have left and right not sorted and nums[mid] != target

public class SearchRotatedSortedArray_81 {
    public static boolean search(int[] nums, int target) {
        if(nums.length == 0){
            return false;
        }
        return binSearch(nums, 0, nums.length-1, target);
    }

    private static boolean binSearch(int[] nums, int low, int high, int target){
        if(low > high){
            return false;
        }
        int mid = low + (high - low)/2;

        if(nums[mid] == target){
            return true;
        }
        // the question is, in which half do we need to search
        // if the right half is sorted
        if(mid < high && isSorted(nums, mid+1, high)){
            if(nums[mid+1] <= target && target <= nums[high]){
                // right half
                return binSearch(nums, mid+1, high, target);
            }
            else{
                //left half
                return binSearch(nums, low, mid-1, target);
            }
        }
        // if the left half is sorted
        else if(mid > low && isSorted(nums, low, mid-1)){
            if(nums[low] <= target && target <= nums[mid-1]){
                // left half
                return binSearch(nums, low, mid-1, target);
            }
            else{
                //right half
                return binSearch(nums, mid+1, high, target);
            }
        }

        // if neither half is sorted
        // eg 1 1 0 0 0 0 1 1 => half one: 1 1 0 0, half two: 0 0 1 1
        // the only thing we know is that mid != target
        else{
            return binSearch(nums, low, mid-1, target) || binSearch(nums, mid+1, high, target);

        }

    }

    // more restrictive isSorted
    private static boolean isSorted(int[] nums, int start, int end){
        return nums[end] > nums[start];
    }
}
