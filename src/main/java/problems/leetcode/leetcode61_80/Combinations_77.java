package problems.leetcode.leetcode61_80;

/*Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.*/

import java.util.ArrayList;
import java.util.List;

public class Combinations_77 {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(result, temp, k, 1,n);
        return result;

    }

    // rest = how many still to at?
    // start = next available number
    public static void helper(List<List<Integer>> result, List<Integer> temp, int rest, int start, int n){
        if(rest == 0){
            result.add(new ArrayList<>(temp));
            return;
        }
        else{
            for(int i = start; i <= n; i++){
                temp.add(i);
                helper(result, temp, rest-1, i+1, n);
                temp.remove(temp.size()-1);
            }
        }

    }
}
