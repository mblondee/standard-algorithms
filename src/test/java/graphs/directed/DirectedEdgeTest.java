package graphs.directed;

import org.junit.Test;
import static org.junit.Assert.*;

public class DirectedEdgeTest {

    @Test
    public void test(){
        DirectedEdge<String> edge = new DirectedEdge<>("a", "b");
        assertEquals("a", edge.startVertex());
        assertEquals("b", edge.endVertex());
        assertEquals(null, edge.weight());

        DirectedEdge<Integer> edge1 = new DirectedEdge<>(1, 2, 0.3);
        assertEquals(new Integer(1), edge1.startVertex());
        assertEquals(new Integer(2), edge1.endVertex());
        assertEquals(0.3, edge1.weight(), 0.01);


        DirectedEdge<String> edge2 = new DirectedEdge<>("a", "b");
        assertTrue(edge.equals(edge2));

        DirectedEdge<String> edge3 = new DirectedEdge<>("c", "b");
        assertFalse(edge.equals(edge3));

        DirectedEdge<Integer> edge4 = new DirectedEdge<>(1, 2, 0.3);
        assertTrue(edge1.equals(edge4));


        DirectedEdge<Integer> edge5 = new DirectedEdge<>(1, 2, 0.2);
        assertFalse(edge1.equals(edge5));
    }
}
