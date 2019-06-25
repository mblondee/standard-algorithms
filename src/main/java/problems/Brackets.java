package problems;

/*
* given a positive number n, print all strings of length 2n consisting of open and closed parentheses that are balanced
*
* idea: any string of balanced parenthesis will start with (
* at some point it will have to be closed
* => ( x ) y
* with x and y strings of balanced parenthesis
* */


import java.util.ArrayList;
import java.util.List;

public class Brackets {

    private List<List<String>> balancedParentheses; // balancedParenteses[i] = list of all balanced parenthesis of length 2i
    private int numberOfParentheses; // given number n

    public Brackets(int n){
        this.numberOfParentheses = n;
        balancedParentheses = new ArrayList<>();
        makeTable();

    }

    public List<String> getBalancedParentheses(){
        return balancedParentheses.get(numberOfParentheses);
    }

    public List<List<String>> getTableBalancedParentheses(){
        return balancedParentheses;
    }

    /*
    * fill balancedParentheses
    * */
    private void makeTable(){
        // balanced parentheses of length 2*0 = 0 contains only the empty string
        List<String> list0 = new ArrayList<>();
        list0.add("");
        balancedParentheses.add(0, list0);

        for (int i = 1; i <= numberOfParentheses; i++){
            List<String> currentList = new ArrayList<>();
            // all other are of the form ( x ) y where len(x) + len(y) = 2i - 2
            for (int j = 0; j <= i-1; j++){
                //parentheses in x: j, len(x) = 2j
                //parentheses in y: i-1-j, len(y) = 2i-2-j
                for(String s1 : balancedParentheses.get(j)){
                    for(String s2 : balancedParentheses.get(i-1-j)){
                        currentList.add("(" + s1 + ")" + s2);
                    }
                }
            }
            balancedParentheses.add(i, currentList);
        }
    }


    public static void main(String[] args){
        Brackets brackets = new Brackets(3);
/*        for(List<String> lst : brackets.getTableBalancedParentheses()){
            System.out.println("-----");
            for(String s : lst){
                System.out.print(s + " ");
            }
        }*/

        for(String s: brackets.getBalancedParentheses()){
            System.out.print(s + " ");
        }

    }


}
