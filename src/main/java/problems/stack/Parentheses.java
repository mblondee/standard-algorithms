package problems.stack;

/*
* given a string of text, check whether all parentheses, square brackets and curly braces are balanced
* O(n) time complexity
* */

public class Parentheses {

    private static char LEFTPARANTHESIS = '(';
    private static char RIGHTPARANTHESIS = ')';

    private static char LEFTSQUARE = '[';
    private static char RIGHTSQUARE = ']';

    private static char LEFTCURLY = '{';
    private static char RIGHTCURLY = '}';

    public static boolean isBalanced(String input){
        Stack<Character> st = new Stack<>(false);

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(c == LEFTPARANTHESIS ||
                    c == LEFTSQUARE ||
                    c == LEFTCURLY){
                st.push(c);
            }

            else if( c == RIGHTPARANTHESIS && (st.isEmpty() || st.pop() != LEFTPARANTHESIS)){
                return false;
            }

            else if( c == RIGHTCURLY && (st.isEmpty() || st.pop()!= LEFTCURLY)){
                return false;
            }

            else if( c == RIGHTSQUARE && (st.isEmpty() || st.pop() != LEFTSQUARE)){
                return false;
            }

            else{
                continue;
            }
        }
        return st.isEmpty();

    }
}
