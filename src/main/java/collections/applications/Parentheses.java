package collections.applications;

/*
* determines whether parentheses (,),[,],{,} are properly balanced*/

import collections.stack.LinkedStack;

public class Parentheses {
    private static final char LEFTPAREN = '(';
    private static final char RIGHTPAREN = ')';
    private static final char LEFTBRACE = '{';
    private static final char RIGHTBRACE = '}';
    private static final char LEFTBLOCK = '[';
    private static final char RIGHTBLOCK = ']';
    private static final char SPACE = ' ';

    public static boolean isBalanced (String s){
        LinkedStack<Character> stack = new LinkedStack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            //if char is opening it is pushed onto the stack
            if(current == SPACE){continue;}
            else if (current == LEFTPAREN || current == LEFTBRACE || current == LEFTBLOCK) {
                stack.push(current);
            }
            // if char is closing we need to check whether there is the other opening on top of the stack
            else {
                // if stack is empty
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char checking = stack.pop();
                    if (current == RIGHTPAREN && checking != LEFTPAREN) {
                        return false;
                    } else if (current == RIGHTBRACE && checking != LEFTBRACE) {
                        return false;
                    } else if (current == RIGHTBLOCK && checking != LEFTBLOCK) {
                        return false;
                    }

                }
            }
        }
        // now we just need to check whether the stack is empty
        return stack.isEmpty();
    }


}
