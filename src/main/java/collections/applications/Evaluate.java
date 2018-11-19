package collections.applications;

/*
* evaluates fully parenthesized arithmetic expressions (+, -, *, /)
* using Dijkstra's two-stack algorithm
* does not check whether the expression is fully parenthesized
* */

import collections.stack.LinkedStack;

public class Evaluate {
    private static final char PLUS = '+';
    private static final char MIN = '-';
    private static final char TIMES = '*';
    private static final char DIVIDE = '/';
    private static final char LEFTPAREN = '(';
    private static final char RIGHTPAREN = ')';
    private static final char SPACE = ' ';

    public static Double apply(Character operator, Double operand1, Double operand2){
        if(operator == PLUS){return operand1 + operand2;}
        else if(operator == MIN){return operand1 - operand2;}
        else if(operator == TIMES){return operand1 * operand2;}
        else if(operator == DIVIDE){return operand1 / operand2;}
        else {throw new UnsupportedOperationException();}
    }

    public static Double eval(String s){
        LinkedStack<Double> operandStack = new LinkedStack<Double>(); // stack for values
        LinkedStack<Character> operatorStack = new LinkedStack<Character>(); // stack for operators

        for(int i = 0; i < s.length(); i++ ){
            Character token = s.charAt(i);
            // token is space or opening (: do nothing
            if(token == SPACE || token == LEFTPAREN){
                continue;
            }
            // token is operator: push on operations stack
            else if (token == PLUS || token == MIN || token == TIMES || token == DIVIDE){
                operatorStack.push(token);
            }
            //token is a number
            else if (token >= '0' && token <= '9'){
                String buffer = Character.toString(token);
                //there might be several digits
                // check next if it is a number add to buffer and increase i
                while(s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9'){
                    buffer = buffer + s.charAt(i+1);
                    i++;
                }
                // now put buffer as a double in operandStack
                operandStack.push(Double.parseDouble(buffer));

            }
            // if token is ) we need to find last operator and last 2 operands (pop from stacks)
            // apply operator to operands and push result to the operandstack
            else if (token == RIGHTPAREN){
                Character operator = operatorStack.pop();
                Double lastOperand = operandStack.pop();
                Double firstOperand = operandStack.pop();
                Double result = apply(operator, firstOperand, lastOperand);
                operandStack.push(result);
            }
            else{throw new IllegalArgumentException();}
        }
    if(operandStack.isEmpty()){
        throw new IllegalArgumentException();}
    return operandStack.pop();

    }


}
