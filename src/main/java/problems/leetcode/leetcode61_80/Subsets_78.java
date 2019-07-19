package problems.leetcode.leetcode61_80;

/*Given a set of distinct integers, nums, return all possible subsets (the power set).

        Note: The solution set must not contain duplicate subsets.*/

// solution with bitwise operations (subsets1)
// solution with backtracking (subsets)
// backtracking: always 2 options: use nums[i] or don't use it

import java.util.ArrayList;
import java.util.List;

public class Subsets_78 {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0){
            return result;
        }
        List<Integer> temp = new ArrayList<>();
        helper(result, temp, nums, 0);
        return result;

    }

    // check all indices: either add or not
    public static void helper(List<List<Integer>> result, List<Integer> temp, int[] nums, int current){
        if(current == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }

        // do not use nums[current], skip current
        helper(result, temp, nums, current+1);

        // do use nums[current]
        temp.add(nums[current]);
        helper(result, temp, nums, current+1);
        temp.remove(temp.size()-1);

    }


    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0){
            return result;
        }
        // there are 2^n subsets of nums, with n = nums.length()
        // we can represent each one by a binary number 0 <= x < 2^n
        // x[n-i] = 0 means nums[i] not included and x[i] = 1 means nums[i] is included
        // eg n = 3, {a,b,c} x = 110 corresponds to
        // a not included (x[0]), b included (x[1]) and c included (x[2])

        // go over all binary numbers 0 <= x < 2^n
        // 1 << n is left bitwise shift (10000...0)
        for(int x = 0; x < (1 << nums.length); x++){

            List<Integer> newList = new ArrayList<>();

            // for every index in nums, check if it should be included
            for(int j = 0; j < nums.length ; j++){

                // ex 6 = 110, n = 3, {a,b,c}
                // j = 0: x[2] = 0 110 & (1<<0) = 110 & 1 = 110 = 0 => do not include a
                // j = 1: x[1] = 1 110 & (1<<1) = 110 & 10 = 10 != 0 => include b
                // j = 2: x[0] = 1 110 & (1<<2) = 110 & 100 = 100 != 0 => include c

                if((x & (1 << j)) > 0){
                    newList.add(nums[j]);
                }


            }
            result.add(newList);
        }

        return result;
    }
}
