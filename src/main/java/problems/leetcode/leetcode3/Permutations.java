package problems.leetcode.leetcode3;

/*Given a collection of distinct integers, return all possible permutations.*/

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0){
            return result;
        }
        helper(result, new ArrayList<>(), nums);
        return result;

    }

    // helper takes a partially filled temp and tries to add elements from nums that are not yet in there
    public static void helper(List<List<Integer>> result, List<Integer> temp, int[] nums){
        if(temp.size() == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(temp.contains(nums[i])){continue;} // find next one that is available
            temp.add(nums[i]);
            helper(result, temp,  nums);
            temp.remove(temp.size()-1);
        }
    }


}
