package graphs.undirected;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeightedGraphTest {

    @Test
    public void test(){
        EdgeWeightedGraph<Integer> G = new EdgeWeightedGraph<>();
        for(Integer i = 0; i<6; i++){
            G.addVertex(i);
        }
        assertEquals(6, G.numberOfVertices());

        Edge<Integer> e1 = new Edge(0,1 , 0.20);
        Edge<Integer> e2 = new Edge(0,5 , 0.10);
        Edge<Integer> e3 = new Edge(1,4 , 0.35);
        Edge<Integer> e4 = new Edge(2,3 , 0.10);
        Edge<Integer> e5 = new Edge(2,0 , 0.90);

        G.addEdge(e1);
        G.addEdge(e2);
        G.addEdge(e3);
        G.addEdge(e4);
        G.addEdge(e5);

        assertEquals(5, G.numberOfEdges());

        G.removeEdge(e1);
        assertEquals(4, G.numberOfEdges());

        G.addEdge(e1);
        G.removeVertex(2);
        assertEquals(3, G.numberOfEdges());
        assertEquals(5, G.numberOfVertices());

        System.out.println(G);

        System.out.println("-------");

        for(Edge<Integer> e : G.getEdges()){
            System.out.println(e);
        }
    }
}
