package collections.applications;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParenthesesTest {

    @Test
    public void ParenTest(){
        Parentheses checker = new Parentheses();

        String input1 = "()";
        assertTrue(checker.isBalanced(input1));

        String input2 = "(";
        assertFalse(checker.isBalanced(input2));

        String input3 = "()()()[]{}";
        assertTrue(checker.isBalanced(input3));

        String input4 = "({()[]}[{()}])";
        assertTrue(checker.isBalanced(input4));

        String input5 = "({ ()[ ]}[{() }] )";
        assertTrue(checker.isBalanced(input5));


        String input6 = "(] ()[ ]}[{() }] )";
        assertFalse(checker.isBalanced(input6));


        String input7 = "([{";
        assertFalse(checker.isBalanced(input7));



    }
}
