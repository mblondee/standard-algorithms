package strings;

import graphs.directed.DirectedEdge;
import org.junit.Assert;
import org.junit.Test;
import strings.search.NFA;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;

import static org.junit.Assert.*;

public class RegExpTest {

    @Test
    public void testnfa1(){
        NFA nfa = new NFA("(AB)*");
        assertEquals(6, nfa.getGraph().numberOfVertices());

        DirectedEdge<Integer>[] a = new DirectedEdge[5];
        a[0] = new DirectedEdge<>(0,1);
        a[1] = new DirectedEdge<>(0,4);
        a[2] = new DirectedEdge<>(3,4);
        a[3] = new DirectedEdge<>(4,0);
        a[4] = new DirectedEdge<>(4,5);


        Assert.assertThat(nfa.getGraph().getEdges(), IsIterableContainingInAnyOrder.containsInAnyOrder(a
        ));

       assertTrue(nfa.matches("AB"));
        assertTrue(nfa.matches(""));
        assertTrue(nfa.matches("ABABAB"));
        assertFalse(nfa.matches("BB"));
        assertFalse(nfa.matches("BA"));
        assertFalse(nfa.matches("C"));
    }

    @Test
    public void testnfa2(){
        NFA nfa = new NFA("(A|B)*");
        assertEquals(7, nfa.getGraph().numberOfVertices());

        DirectedEdge<Integer>[] a = new DirectedEdge[7];
        a[0] = new DirectedEdge<>(0,1);
        a[1] = new DirectedEdge<>(0,3);
        a[2] = new DirectedEdge<>(0,5);
        a[3] = new DirectedEdge<>(2,4);
        a[4] = new DirectedEdge<>(4,5);
        a[5] = new DirectedEdge<>(5,0);
        a[6] = new DirectedEdge<>(5,6);


        Assert.assertThat(nfa.getGraph().getEdges(), IsIterableContainingInAnyOrder.containsInAnyOrder(a
        ));

        assertTrue(nfa.matches("A"));
        assertTrue(nfa.matches("AA"));
        assertTrue(nfa.matches("AB"));
        assertTrue(nfa.matches(""));
        assertTrue(nfa.matches("BB"));
        assertFalse(nfa.matches("BC"));
        assertFalse(nfa.matches("CA"));
    }

    @Test
    public void testnfa3(){
        NFA nfa = new NFA("((A*B|AC)D)");
        assertEquals(12, nfa.getGraph().numberOfVertices());

        DirectedEdge<Integer>[] a = new DirectedEdge[9];
        a[0] = new DirectedEdge<>(0,1);
        a[1] = new DirectedEdge<>(1,2);
        a[2] = new DirectedEdge<>(1,6);
        a[3] = new DirectedEdge<>(2,3);
        a[4] = new DirectedEdge<>(3,2);
        a[5] = new DirectedEdge<>(3,4);
        a[6] = new DirectedEdge<>(5,8);
        a[7] = new DirectedEdge<>(8,9);
        a[8] = new DirectedEdge<>(10,11);


        Assert.assertThat(nfa.getGraph().getEdges(), IsIterableContainingInAnyOrder.containsInAnyOrder(a
        ));

        assertTrue(nfa.matches("AABD"));
        assertTrue(nfa.matches("AAAAAAAAAAAAAAAAAAAABD"));
        assertFalse(nfa.matches("AAAAAAAAAAAAAAAAAAAAD"));

        assertTrue(nfa.matches("ACD"));
        assertFalse(nfa.matches("AACD"));
    }

    @Test
    public void wildcardTest(){
        NFA nfa = new NFA("(A.B)*");
        assertTrue(nfa.matches("AAB"));
        assertTrue(nfa.matches("ACB"));
        assertTrue(nfa.matches("AABACBABB"));
        assertFalse(nfa.matches("AB"));
        assertFalse(nfa.matches("BCA"));
    }

    @Test
    public void plusTest(){
        NFA nfa = new NFA("(AB)+");
        assertFalse(nfa.matches(""));
        assertTrue(nfa.matches("AB"));
        assertTrue(nfa.matches("ABAB"));
    }

}
