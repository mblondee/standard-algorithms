package collections.applications;

import org.junit.Test;
import static org.junit.Assert.*;

public class EvaluateTest {

    @Test
    public void testEval() {
        Evaluate evaluator = new Evaluate();

        String input1 = "(4*(1 + 2))";
        String input2 = "(10 / ((5*6) - 20) ) ";



        assertEquals(12, evaluator.eval(input1), 0.0000001);
        assertEquals(1, evaluator.eval(input2), 0.0000001);
    }

}
