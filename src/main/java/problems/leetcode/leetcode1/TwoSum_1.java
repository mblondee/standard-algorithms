package problems.leetcode.leetcode1;

/*
* Given an array of integers, return indices of the two numbers such that they add up to a specific target.
* You may assume that each input would have exactly one solution, and you may not use the same element twice.
* */

import java.util.HashMap;

public class TwoSum_1 {

    public static int[] twoSum(int[] nums, int target){
        int[] result = new int[2];
        HashMap<Integer, Integer> keepTrack = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int lookup = target - nums[i];
            if(keepTrack.containsKey(lookup)){
                result[0] = i;
                result[1] = keepTrack.get(lookup);
            }
            keepTrack.put(nums[i], i);
        }

        return result;
    }
}
