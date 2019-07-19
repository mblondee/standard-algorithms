package problems.leetcode.leetcode21_40;

/*
* Given a string containing just the characters '(' and ')',
* find the length of the longest valid (well-formed) parentheses substring.
* */

import java.util.Stack;

public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        int largestLength = 0;
        int newStart = 0; //looking for a valid sequence from this index

        //stack to store '('
        Stack<Integer> indexStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                indexStack.push(i);
            }
            //else there is a ')'
            else{
                // if stack is empty there is no '(' to close -> we have a valid sequence up until i-1
                // we have already the length taken into account before when we found the valid sequence
                if(indexStack.empty()){
                    newStart = i+1;
                }
                else{
                    //remove the '('
                    indexStack.pop();

                    //if stack is empty we have found a valid sequence
                    if(indexStack.empty()){
                        largestLength = Math.max(largestLength, i - newStart + 1);
                    }
                    else{
                        largestLength = Math.max(largestLength, i-indexStack.peek()); // current length starts from peek-1
                        // i - (peek + 1) + 1 = i - peek
                    }
                }
            }
        }

        return largestLength;
    }

/*    public static int dynamicprogramming(String s){
        int[] lengths = new int[s.length()]; // lengths[i] longest length for substring 0 to i (i included)

        // lengths[0] = 0
        // lengths[1] = 0

        // lengths[i] = ?
        // if s[i] == '(' : lengths[i-1]
        // if s[i] == ')'

        for(int i = 2; i < s.length(); i++){
            if(s.charAt(i) == '('){
                lengths[i] = lengths[i-1];
            }
            else{


            }
        }

    }*/


}
