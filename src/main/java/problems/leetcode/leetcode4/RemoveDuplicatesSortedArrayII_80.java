package problems.leetcode.leetcode4;

/*Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

        Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.*/

public class RemoveDuplicatesSortedArrayII_80 {
    public static int removeDuplicates(int[] nums) {
        // filling the array from start
        int start = 0;

        // next group of same numbers
        // if difference is too big we need to fill in array
        int nextStart = 0;
        int nextEnd= 0;

        while(nextEnd < nums.length){
            while(nextEnd < nums.length - 1 && nums[nextStart] == nums[nextEnd+1]){
                nextEnd++;
            }

            int diff = nextEnd - nextStart;

            // there are no more than 2 duplicates
            if(diff <= 1){
                while(diff >= 0){
                    nums[start++] = nums[nextEnd]; // (use start and increment)
                    diff--;
                }
            }
            // if there are more than 2 duplicates
            else {
                nums[start++] = nums[nextStart];
                nums[start++] = nums[nextStart];
            }

            nextStart = nextEnd + 1;
            nextEnd = nextStart;


        }

        return start;



    }
}
