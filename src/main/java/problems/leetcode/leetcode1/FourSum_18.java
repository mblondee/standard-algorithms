package problems.leetcode.leetcode1;

/*
* Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums
*  such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
The solution set must not contain duplicate quadruplets.
* */

/*
* k-sum problem is always time complexity O(n^{k-1})
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum_18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 4){
            return result;
        }

        Arrays.sort(nums);


        for(int index1 = 0; index1 <= nums.length - 4; index1++){
            if(index1 > 0 && nums[index1] == nums[index1-1]){
                continue;
            }
            for(int index2 = index1 + 1; index2 <= nums.length - 3; index2++){
                if(index2 > index1 +1  && nums[index2] == nums[index2-1]){
                    continue;
                }
                int i = index2+1;
                int j = nums.length -1;

                while(i<j) {

                    if (nums[index1] + nums[index2] + nums[i] + nums[j] == target) {
                        List<Integer> toAdd = new ArrayList<>();
                        toAdd.add(nums[index1]);
                        toAdd.add(nums[index2]);
                        toAdd.add(nums[i]);
                        toAdd.add(nums[j]);
                        result.add(toAdd);
                        while (i < j && nums[i] == nums[i + 1]) {
                            i++;
                        }
                        i++;
                        while (i < j && nums[j] == nums[j - 1]) {
                            j--;
                        }
                        j--;
                    } else if (nums[index1] + nums[index2] + nums[i] + nums[j] < target) {
                        while (i < j && nums[i] == nums[i + 1]) {
                            i++;
                        }
                        i++;
                    } else {
                        while (i < j && nums[j] == nums[j - 1]) {
                            j--;
                        }
                        j--;
                    }
                }
            }
        }



        return result;
    }
}
