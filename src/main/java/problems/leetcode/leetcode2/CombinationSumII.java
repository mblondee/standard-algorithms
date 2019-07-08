package problems.leetcode.leetcode2;

/*Given a collection of candidate numbers (candidates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

        Each number in candidates may only be used once in the combination.

        Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
                if(i > start && candidates[i] == candidates[i-1]){
                    continue;
                }
                // set has no duplicates!
                temp.add(candidates[i]);
                helper(candidates, result, temp, remain - candidates[i], i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
}
