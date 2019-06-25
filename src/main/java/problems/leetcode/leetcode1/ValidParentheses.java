package problems.leetcode.leetcode1;

/*
* Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.
*
* */

import java.util.Stack;

public class ValidParentheses {
    public static char LEFTPAR = '(';
    public static char RIGHPAR = ')';
    public static char LEFTCURL = '{';
    public static char RIGHTCURL = '}';
    public static char LEFTBLOCK = '[';
    public static char RIGHBLOCK = ']';

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i <s.length(); i++){
            char current = s.charAt(i);
            if(current == LEFTBLOCK || current == LEFTCURL || current == LEFTPAR){
                stack.push(current);
            }
            else{
                // on top of the stack there must be the corresponding left bracket
                if(stack.empty()){
                    return false;
                }
                char popped = stack.pop();
                if(current == RIGHBLOCK && popped != LEFTBLOCK){
                    return false;
                }
                if(current == RIGHPAR && popped != LEFTPAR){
                    return false;
                }
                if(current == RIGHTCURL && popped != LEFTCURL){
                    return false;
                }

            }
        }
        return stack.empty();

    }
}
