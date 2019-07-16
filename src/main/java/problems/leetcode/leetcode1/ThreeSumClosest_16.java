package problems.leetcode.leetcode1;

/*
* Given an array nums of n integers and an integer target,
* find three integers in nums such that the sum is closest to target.
* Return the sum of the three integers. You may assume that each input would have exactly one solution.
* */

import java.util.Arrays;

public class ThreeSumClosest_16 {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int difference = Integer.MAX_VALUE;
        int closest = 0;

        for(int index = 0; index < nums.length; index++){
            if(index == nums.length - 2){
                break;
            }
            int i = index +1;
            int j = nums.length -1;

            while(i < j){
                if(nums[index] + nums[i] + nums[j] == target){
                    return target;
                }
                else if(nums[index] + nums[i] + nums[j] < target){
                    if(difference > target - nums[index] - nums[i] - nums[j]){
                        difference = target -nums[index] - nums[i] - nums[j];
                        closest = nums[index] + nums[i] + nums[j];
                    }
                    while(i < j && nums[i] == nums[i+1]){
                        i++;
                    }
                    i++;
                }
                else{
                    if(difference > nums[index] + nums[i] + nums[j] - target){
                        difference = nums[index] + nums[i] + nums[j] - target;
                        closest = nums[index] + nums[i] + nums[j];
                    }
                    while(i<j && nums[j] == nums[j-1]){
                        j--;
                    }
                    j--;
                }
            }

        }

        return closest;

    }
}
