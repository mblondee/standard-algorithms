package problems.leetcode.leetcode2;

/*
* Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
* */

/*
* idea string of well formed parentheses of length 2i are of the form
* ( x ) y with x and y strings of well formed parentheses with len(x) + len(y) = 2*i - 2
* -> dynamic programming
* x: 0 to i-1 pairs
* y: i-1 to 0 pairs
* and sum of pairs x and y = i-1
* disadvantage = will produce duplicates
* */

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {

        List<List<String>> balanced = new ArrayList<>();//balanced[i] => all possible balanced strings of length 2i

        // parentheses of length 2i with i = 0: only "";
        List<String> toAdd = new ArrayList<>();
        toAdd.add("");
        balanced.add(0, toAdd);

        for(int i = 1; i <= n ; i++){
            toAdd = new ArrayList<>();
            for(int x = 0; x <= i-1; x++){
                // x+y = i-1
                int y = i-1-x;
                for(String strx : balanced.get(x)){
                    for(String stry: balanced.get(y)){
                        toAdd.add("(" + strx + ")" + stry);
                    }
                }
            }
            balanced.add(i, toAdd);
        }

        return balanced.get(n);
    }
}
