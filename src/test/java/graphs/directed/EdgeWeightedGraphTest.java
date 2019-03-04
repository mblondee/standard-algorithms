package graphs.directed;

import org.junit.Test;
import static org.junit.Assert.*;

public class EdgeWeightedGraphTest {

    @Test
    public void Edge(){
        DirectedEdge<String> e = new DirectedEdge<>("a", "b", 0.3);
        assertEquals("a", e.startVertex());
        assertEquals("b", e.endVertex());
        assertEquals(0.3, e.weight(), 0.0001);
        System.out.println(e);
    }

    @Test
    public void Graph(){
        EdgeWeightedDiGraph<String> G = new EdgeWeightedDiGraph<>();
        String s = "abcdef";
        for(int i = 0; i<s.length(); i++){
            String c = s.substring(i,i+1);
            G.addVertex(c);
        }
        assertEquals(6, G.numberOfVertices());

        DirectedEdge<String> edge1 = new DirectedEdge<>("a", "b", 0.5);
        G.addEdge(edge1);
        DirectedEdge<String> edge2 = new DirectedEdge<>("a", "c", 0.6);
        G.addEdge(edge2);
        DirectedEdge<String> edge3 = new DirectedEdge<>("b", "e", 0.1);
        G.addEdge(edge3);
        assertEquals(3, G.numberOfEdges());

        DirectedEdge<String> edge4 = new DirectedEdge<>("d", "a", 0.9);
        G.addEdge(edge4);
        DirectedEdge<String> edge5 = new DirectedEdge<>("d", "e", 0.8);
        G.addEdge(edge5);
        DirectedEdge<String> edge6 = new DirectedEdge<>("b", "e", 0.1);
        G.addEdge(edge6);
        assertEquals(5, G.numberOfEdges());


        G.removeVertex("d");
        assertEquals(5, G.numberOfVertices());
        assertEquals(3, G.numberOfEdges());
        //System.out.println(G);

        G.removeEdge(edge6);
        //System.out.println(G);
        assertEquals(5, G.numberOfVertices());
        assertEquals(2, G.numberOfEdges());

        //System.out.println(G);
    }

    @Test
    public void iterate(){
        EdgeWeightedDiGraph<String> G = new EdgeWeightedDiGraph<>();
        String s = "abcdef";
        for(int i = 0; i<s.length(); i++){
            String c = s.substring(i,i+1);
            G.addVertex(c);
        }
        DirectedEdge<String> edge1 = new DirectedEdge<>("a", "b", 0.5);
        G.addEdge(edge1);
        DirectedEdge<String> edge2 = new DirectedEdge<>("a", "c", 0.6);
        G.addEdge(edge2);
        DirectedEdge<String> edge3 = new DirectedEdge<>("b", "e", 0.1);
        G.addEdge(edge3);

        DirectedEdge<String> edge4 = new DirectedEdge<>("d", "a", 0.9);
        G.addEdge(edge4);
        DirectedEdge<String> edge5 = new DirectedEdge<>("d", "e", 0.8);
        G.addEdge(edge5);

        System.out.println("vertices: ");
        for(String str: G.getVertices()){
            System.out.println(str);
        }
        System.out.println("edges: ");
        for(DirectedEdge<String> edge : G.getEdges()){
            System.out.println(edge);
        }
        System.out.println("neighbours of a: ");
        for(DirectedEdge<String> edge: G.getNeighbours("a")){
            System.out.println(edge.endVertex());
        }

        assertEquals(1, G.getInDegree("a"));
        assertEquals(2, G.getOutDegree("a"));
        assertEquals(1, G.getInDegree("b"));
        assertEquals(1, G.getOutDegree("b"));
        assertEquals(1, G.getInDegree("c"));
        assertEquals(0, G.getOutDegree("c"));
        assertEquals(0, G.getInDegree("d"));
        assertEquals(2, G.getOutDegree("d"));
        assertEquals(2, G.getInDegree("e"));
        assertEquals(0, G.getOutDegree("e"));
        assertEquals(0, G.getInDegree("f"));
        assertEquals(0, G.getOutDegree("f"));




    }
}
