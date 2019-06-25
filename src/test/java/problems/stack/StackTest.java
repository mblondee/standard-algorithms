package problems.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void testStack(){
        Stack<String> st = new Stack<>(false);
        assertEquals(0, st.getSize());
        st.push("a");
        assertEquals(1, st.getSize());
        st.push("b");
        assertEquals(2, st.getSize());
        st.push("c");
        assertEquals(3, st.getSize());
        assertEquals("c", st.pop());
        assertEquals(2, st.getSize());
        assertEquals("b", st.pop());
        assertEquals(1, st.getSize());
        assertEquals("a", st.pop());
        assertEquals(0, st.getSize());
    }

    @Test
    public void testMin(){
        Stack<Integer> st = new Stack<>(true);
        st.push(10);
        assertEquals(new Integer(10), st.getMinElement());
        assertEquals(new Integer(10), st.getMaxElement());

        st.push(4);
        assertEquals(new Integer(4), st.getMinElement());
        assertEquals(new Integer(10), st.getMaxElement());

        st.push(11);
        assertEquals(new Integer(4), st.getMinElement());
        assertEquals(new Integer(11), st.getMaxElement());

        st.push(9);
        assertEquals(new Integer(4), st.getMinElement());
        assertEquals(new Integer(11), st.getMaxElement());

        st.push(8);
        assertEquals(new Integer(4), st.getMinElement());
        assertEquals(new Integer(11), st.getMaxElement());

        st.pop();
        assertEquals(new Integer(4), st.getMinElement());
        assertEquals(new Integer(11), st.getMaxElement());

        st.pop();
        assertEquals(new Integer(4), st.getMinElement());
        assertEquals(new Integer(11), st.getMaxElement());

        st.pop();
        assertEquals(new Integer(4), st.getMinElement());
        assertEquals(new Integer(10), st.getMaxElement());

        st.pop();
        assertEquals(new Integer(10), st.getMinElement());
        assertEquals(new Integer(10), st.getMaxElement());


    }

    @Test
    public void testParen(){
        String test1 = "()()()()";
        String test2 = "(1)(2)(a)";
        String test3 = "";
        String test4 = "[(a)] {((w))p}";
        String test5 = "[(aaa11) ] [{}{}{}]";

        assertTrue(Parentheses.isBalanced(test1));
        assertTrue(Parentheses.isBalanced(test2));
        assertTrue(Parentheses.isBalanced(test3));
        assertTrue(Parentheses.isBalanced(test4));
        assertTrue(Parentheses.isBalanced(test5));

        String test6 = ")";
        String test7 = "[{]}";
        String test8 = "{}{}{}aaaaa[";

        assertFalse(Parentheses.isBalanced(test6));
        assertFalse(Parentheses.isBalanced(test7));
        assertFalse(Parentheses.isBalanced(test8));

    }
}
