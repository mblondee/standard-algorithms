package problems.leetcode.leetcode21_40;

/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

        (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

        You are given a target value to search. If found in the array return its index, otherwise return -1.

        You may assume no duplicate exists in the array.

        Your algorithm's runtime complexity must be in the order of O(log n).*/


public class SearchRotatedSortedArray_33 {

    public static int search(int[] nums, int target) {
        if(nums.length == 0){
            return -1;
        }

        //if nums is divided into 2 parts, at least one will be sorted
        //if a subarray is sorted can easily be checked by comparing first and last el
        //if it is sorted and target is between first and last we can look for it using binary search
        //otherwise half and repeat
        return search(nums, target, 0, nums.length-1);


    }

    private static int search(int[] nums, int target, int start, int end){
        if(isSorted(nums, start, end) && (target < nums[start] || target > nums[end])){
            return -1;
        }
        else if(isSorted(nums, start, end)){
            return binarySearch(nums, target, start, end);
        }
        else{
            // divide in start->pivot and pivot->end
            int pivot = start + (end-start)/2;
            int resultFirst = search(nums, target, start, pivot);
            if(resultFirst == -1){
                return search(nums, target, pivot+1, end);
            }
            else{
                return resultFirst;
            }
        }
    }

    private static int binarySearch(int[] nums, int target, int start, int end){
        if(end < start){
            return -1;
        }
        int mid = start + (end-start)/2;
        if(target == nums[mid]){
            return mid;
        }
        else if(target < nums[mid]){
            return binarySearch(nums, target, start, mid-1);
        }
        else{
            return binarySearch(nums, target, mid+1, end);
        }
    }

    private static boolean isSorted(int[] nums, int start, int end){
        return nums[end] >= nums[start];
    }
}
