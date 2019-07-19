package problems.leetcode.leetcode4;

/*Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent,
with the colors in the order red, white and blue.

        Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

        Note: You are not suppose to use the library's sort function for this problem.*/



public class SortColors_75 {


    public static void sortColors(int[] nums) {
        int index0 = 0; // next 0 should go here
        int index2 = nums.length-1; // next 2 should go here

        int index = 0;
        while(index <= index2){
            if(nums[index] == 0){
                // has to be put in index0
                swap(nums, index, index0);
                index0++;
                index++;
            }
            else if(nums[index] == 2){
                swap(nums, index, index2);
                index2--;
                // do not update index because we get a not yet processed number at index
            }
            else{
                index ++;
            }
        }
    }

    public static void swap(int[] nums, int i, int j ){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // using counting sort
    public static void sortColors1(int[] nums) {
        int[] counts = new int[3];
        for(int i = 0; i < nums.length; i++){
            counts[nums[i]]++;
        }

        int index = 0;

        for(int i = 0; i < counts.length; i++){
            while(counts[i] > 0){
                nums[index] = i;
                index++;
                counts[i]--;
            }
        }
    }
}
