package problems.leetcode.leetcode41_60;

/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII_47 {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0){
            return result;
        }

        Arrays.sort(nums);
        helper(result, new ArrayList<>(), nums, new boolean[nums.length]);

        return result;

    }

    // helper is used to add the next available (i.e. not in usedIndex) to temp
    public static void helper(List<List<Integer>> result, List<Integer> temp, int[] nums, boolean[] used){
        if(temp.size() == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && used[i-1])){
                continue;
            }
            temp.add(nums[i]);
            used[i] = true;
            helper(result, temp, nums, used);
            temp.remove(temp.size() -1);
            used[i] = false;
        }

    }
}
