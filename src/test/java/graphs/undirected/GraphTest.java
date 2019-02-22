package graphs.undirected;


import graphs.undirected.Graph;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

/*    @Test
    public void test1(){
        Graph G = new Graph(10);
        G.addEdge(1, 5);
        assertEquals(1, G.getEdges());
        assertEquals(10, G.getVertices());
        assertEquals(0, G.getDegree(2));
        assertEquals(1, G.getDegree(1));
        assertEquals(1, G.getDegree(5));
        System.out.println(G);
    }*/

    @Test
    public void test1(){
        Graph<Integer> G = new Graph();
        assertEquals(0,G.numberOfVertices());
        assertEquals(0,G.numberOfEdges());

        G.addVertex(1);
        assertEquals(1,G.numberOfVertices());
        assertEquals(0,G.numberOfEdges());
        //System.out.println(G);

        G.addVertex(5);
        G.addEdge(1,5);
        assertEquals(2,G.numberOfVertices());
        assertEquals(1,G.numberOfEdges());
        //System.out.println(G);

        G.addVertex(2);
        G.addEdge(2,5);
        //System.out.println(G);

        G.removeVertex(1);
        assertEquals(2,G.numberOfVertices());
        assertEquals(1,G.numberOfEdges());
        //System.out.println(G);


    }

    @Test
    public void test2(){
        Graph<Integer> G = new Graph();
        for(Integer i = 1; i<=10; i++){
            G.addVertex(i);
        }
        assertEquals(10, G.numberOfVertices());
        assertEquals(0, G.numberOfEdges());

        G.addEdge(1,5);
        G.addEdge(1,8);
        G.addEdge(1,6);

        G.addEdge(4,6);
        G.addEdge(4,1);
        G.addEdge(4,5);

        G.addEdge(6,1);
        G.addEdge(6,4);

        G.addEdge(10,8);
        G.addEdge(10,5);

        assertEquals(8, G.numberOfEdges());
        System.out.println(G);

        G.removeVertex(2);
        assertEquals(8, G.numberOfEdges());
        assertEquals(9, G.numberOfVertices());

        G.removeVertex(9);
        assertEquals(8, G.numberOfEdges());
        assertEquals(8, G.numberOfVertices());

        G.removeVertex(5);
        assertEquals(5, G.numberOfEdges());
        assertEquals(7, G.numberOfVertices());
        //System.out.println(G);

        G.removeVertex(3);
        G.removeVertex(7);
        assertEquals(5, G.numberOfEdges());
        assertEquals(5, G.numberOfVertices());
        //System.out.println(G);

        G.removeEdge(10,8);
        assertEquals(4, G.numberOfEdges());
        assertEquals(5, G.numberOfVertices());
        //System.out.println(G);

        G.removeEdge(10,8);
        assertEquals(4, G.numberOfEdges());
        assertEquals(5, G.numberOfVertices());

        G.removeEdge(1,4);
        assertEquals(3, G.numberOfEdges());
        assertEquals(5, G.numberOfVertices());
        //System.out.println(G);

        G.removeVertex(8);
        G.removeVertex(10);
        assertEquals(2, G.numberOfEdges());
        assertEquals(3, G.numberOfVertices());
        //System.out.println(G);

    }
}
