package graphs.undirected;

import org.junit.Test;
import static org.junit.Assert.*;

public class EdgeTest {

    @Test
    public void test(){
        Edge<String> edge = new Edge<>("a", "b");
        Edge<String> edge1 = new Edge<>("a", "c");
        Edge<String> edge2 = new Edge<>("a", "b");
        Edge<String> edge3 = new Edge<>("b", "a");
        assertTrue(edge.equals(edge2));
        assertFalse(edge.equals(edge1));
        assertTrue(edge.equals(edge3));
        assertEquals(null, edge.weight());


        Edge<Integer> edge4 = new Edge<>(0,1,0.2);
        Edge<Integer> edge5 = new Edge<>(0,1,0.6);
        Edge<Integer> edge6 = new Edge<>(1,0,0.2);
        Edge<Integer> edge7 = new Edge<>(4,1,0.2);
        Edge<Integer> edge8 = new Edge<>(0,1,0.2);
        assertTrue(edge4.equals(edge8));
        assertTrue(edge6.equals(edge8));
        assertTrue(edge4.equals(edge5));
        assertFalse(edge4.equals(edge7));


    }
}
