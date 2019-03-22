package strings.search;

/*
* a data type for creating a non deterministic finite state automaton (NFA)
* from a regular expression that tests whether a given string is matched by that regular expression
*
* a regular expression (RE) is
* - empty: the empty set of strings
* - a single character: set of strings with this 1 element (this one)
* - a regular expression enclosed in parentheses
* - two or more concatenated regular expressions: the cross product of the two sets of strings
* - two or more regular expressions separated  by | (or): the union of the two sets of strings
* - a regular expression followed by * (closure): any number of copies of the RE (also 0)
* a regular expression followed by +: any number of copies of the RE (at least 1)
*
* assumption: all regexps are well defined, regexp may have wildcard '.' (can be any char)
* string to test has no (,),*,|, +
*
* */

import collections.bag.LinkedBag;
import collections.stack.LinkedStack;
import graphs.directed.DiGraph;
import graphs.directed.DirectedDFS;
import graphs.directed.DirectedEdge;

public class NFA {
    private DiGraph<Integer> graph; // digraph of epsilon (no match) transitions
    private String regexp; // regular expression
    private int reglength; // length of regular expression

    public NFA(String regexp){
        this.regexp = regexp;
        this.reglength = regexp.length();

        // stack to keep track of parentheses and |
        // each left parenthesis is pushed on the stack, when a right parenthesis is encountered
        // the corresponding left one or | is popped
        LinkedStack<Integer> operators = new LinkedStack<>();

        graph = new DiGraph<>();
        // the nfa has a state for each character in the regexp + an accept state
        for(int i = 0; i<=reglength; i++){
          graph.addVertex(i);
        }

        for(int i = 0; i<reglength; i++){
            int leftParenthesis = i; //left parenthesis or current state

            if(regexp.charAt(i) == '(' || regexp.charAt(i) == '|'){
                operators.push(i);
            }

            else if(regexp.charAt(i) == ')'){
                int op = operators.pop();

                //check if op is ) or |
                if(regexp.charAt(op) == '|'){
                    // get state of left parenthesis
                    leftParenthesis = operators.pop();
                    // add edge from left parenthesis to state after | (choice of second operand)
                    graph.addEdge(new DirectedEdge<>(leftParenthesis, op+1));
                    // add edge from | to right parenthesis (choice of first operand)
                    graph.addEdge(new DirectedEdge<>(op, i));
                }

                else if(regexp.charAt(op) == '('){
                    leftParenthesis = op;
                }

            }

            // is next char *?
            if(i < reglength -1 && regexp.charAt(i+1) == '*'){
                graph.addEdge(new DirectedEdge<>(leftParenthesis, i+1));
                graph.addEdge(new DirectedEdge<>(i+1, leftParenthesis));
            }

            // is next char +?
            if(i < reglength -1 && regexp.charAt(i+1) == '+'){
                graph.addEdge(new DirectedEdge<>(i+1, leftParenthesis));
            }

            // add epsilon transitions
            if(regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')' || regexp.charAt(i) == '+'){
                graph.addEdge(new DirectedEdge<>(i, i+1));
            }

        }


    }

    /*
    * is text matched by regexp?
    * */
    public boolean matches(String text){
        // searching path from state 0
        DirectedDFS<Integer> dfs = new DirectedDFS<>(graph, 0);

        LinkedBag<Integer> reachableStates = new LinkedBag<>();

        // add all states reachable from state 0 with epsilon transitions (nfa)
        for(int i = 0; i <= reglength; i++){
            if(dfs.hasPathTo(i)){
                reachableStates.add(i);
            }
        }

        // going through text
        for(int i = 0; i < text.length(); i++){
            LinkedBag<Integer> matches = new LinkedBag<>();

            for(int state : reachableStates){
                // add end of regexp
                if(state == reglength){
                    continue;
                }
                // char is one of the states: add next state
                // or regexp has wildcard
                if( regexp.charAt(state) == text.charAt(i) || regexp.charAt(state) == '.' ){
                    matches.add(state +1);
                }
            }

            //reset reachable states
            reachableStates = new LinkedBag<>();

            // add states reachable by epsilon transitions from the states in matches
            for(int state : matches){
                dfs = new DirectedDFS<>(graph, state);
                // add all states reachable from state with epsilon transitions (nfa)
                for(int otherState = 0; otherState <= reglength; otherState++){
                    if(dfs.hasPathTo(otherState)){
                        reachableStates.add(otherState);
                    }
                }
            }

            // there are no reachable states
            if(reachableStates.size() == 0){
                return false;
            }

        }

        // when gone through text, check for accept state
        for(int state : reachableStates){
            if(state == reglength){
                return true;
            }

        }
        return false;
    }



    @Override
    public String toString(){
        return graph.toString();
    }

    public DiGraph<Integer> getGraph(){
        return graph;
    }


}
