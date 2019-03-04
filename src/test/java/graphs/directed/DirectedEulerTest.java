package graphs.directed;


import org.junit.Test;



import static org.junit.Assert.*;


public class DirectedEulerTest {


    @Test
    public void test(){
        DiGraph<Integer> G1 = new DiGraph<>();
        G1.addVertex(0);
        G1.addVertex(1);
        G1.addVertex(2);
        G1.addVertex(3);
        G1.addVertex(4);
        G1.addEdge(new DirectedEdge<>(0,1));
        G1.addEdge(new DirectedEdge<>(1,2));
        G1.addEdge(new DirectedEdge<>(2,0));
        G1.addEdge(new DirectedEdge<>(0,3));
        G1.addEdge(new DirectedEdge<>(3,4));
        G1.addEdge(new DirectedEdge<>(4,0));

        DirectedEuler<Integer> euler = new DirectedEuler<>(G1);
        assertTrue(euler.hasEulerianCycle());

        for(Integer i : euler.getEulerianCycle()){
            System.out.println(i);
        }

        G1.removeEdge(new DirectedEdge<>(4,0)); // werkt??
        DirectedEuler<Integer> euler1 = new DirectedEuler<>(G1);
        assertFalse(euler1.hasEulerianCycle());
    }



/*    @Test
    public void privatetest(){
        DiGraph<Integer> G1 = new DiGraph<>();
        G1.addVertex(0);
        G1.addVertex(1);
        G1.addVertex(2);
        G1.addVertex(3);
        G1.addVertex(4);
        G1.addEdge(0,1);
        G1.addEdge(1,2);
        G1.addEdge(2,0);
        G1.addEdge(0,3);
        G1.addEdge(3,4);
        G1.addEdge(4,0);
        DirectedEuler<Integer> euler = new DirectedEuler<>(G1);
        for(Integer i = 0; i<=4; i++) {
            assertTrue(euler.checkComponent(i));
        }
        G1.removeEdge(4,0);
        DirectedEuler<Integer> euler1 = new DirectedEuler<>(G1);
        for(Integer i = 0; i<=4; i++) {
            assertFalse(euler1.checkComponent(i));
        }

    }*/
}
