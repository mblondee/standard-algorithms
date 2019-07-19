package problems.leetcode.leetcode21_40;

/*Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

        The same repeated number may be chosen from candidates unlimited number of times.

        Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
//        int end = 0;
//        // candidates that are larger than target can never be part of the sum
//        while(end < candidates.length && candidates[end] <= target){
//            end++;
//        }
        List<List<Integer>> result = new ArrayList<>();
        helper(candidates, result, new ArrayList<>(), target, 0);


        return result;
    }

    private static void helper(int[] candidates, List<List<Integer>> result, List<Integer> temp, int remain, int start){
        if(remain < 0){
            return;
        }
        else if(remain == 0){
            result.add(new ArrayList<>(temp));
        }
        else{
            for(int i = start; i < candidates.length; i++){
                if(candidates[i] > remain){
                    break;
                }
                // set has no duplicates!
                temp.add(candidates[i]);
                helper(candidates, result, temp, remain - candidates[i], i);
                temp.remove(temp.size()-1);
            }
        }
    }
}
