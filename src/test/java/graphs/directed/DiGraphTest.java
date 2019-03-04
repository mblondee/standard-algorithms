package graphs.directed;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DiGraphTest {

    private static DiGraph<Integer> G;

    @BeforeClass
    public static void initialize(){
        //String fileName = "src/main/resources/graph.txt";
        String fileName = "src/main/resources/smalldigraph.txt";
        G = new DiGraph();

        String line;
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            while ( (line = bufferReader.readLine()) != null){
                String lineString = line.trim();
                Integer v1 = Integer.parseInt(lineString.split("\\s+")[0]);
                Integer v2 = Integer.parseInt(lineString.split("\\s+")[1]);
                G.addVertex(v1);
                G.addVertex(v2);
                G.addEdge(new DirectedEdge<>(v1,v2));
            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }
    }

    @Test
    public void test1(){
        DiGraph<String> G1 = new DiGraph<>();
        G1.addVertex("a");
        G1.addVertex("b");
        G1.addVertex("c");
        G1.addVertex("d");
        assertEquals(4, G1.numberOfVertices());
        assertEquals(0, G1.numberOfEdges());
        G1.addEdge(new DirectedEdge<>("a", "b"));
        assertEquals(4, G1.numberOfVertices());
        assertEquals(1, G1.numberOfEdges());
        G1.addEdge(new DirectedEdge<>("a", "c"));
        G1.addEdge(new DirectedEdge<>("a", "d"));
        assertEquals(3, G1.numberOfEdges());
        G1.addEdge(new DirectedEdge<>("a", "d"));
        assertEquals(3, G1.numberOfEdges());
        G1.addEdge(new DirectedEdge<>("b", "d"));
        assertEquals(4, G1.numberOfEdges());
        assertEquals(0, G1.getInDegree("a"));
        assertEquals(3, G1.getOutDegree("a"));
        assertEquals(1, G1.getInDegree("b"));
        assertEquals(1, G1.getOutDegree("b"));
        assertEquals(1, G1.getInDegree("c"));
        assertEquals(0, G1.getOutDegree("c"));
        assertEquals(2, G1.getInDegree("d"));
        assertEquals(0, G1.getOutDegree("d"));

        //System.out.println(G1);
        //System.out.println("----");
        G1.removeEdge(new DirectedEdge<>("b", "d"));
        //System.out.println(G1);
        assertEquals(3, G1.numberOfEdges());
        G1.removeVertex("a");
        //System.out.println(G1);
        assertEquals(3, G1.numberOfVertices());
        assertEquals(0, G1.numberOfEdges());
    }


    @Test
    public void test2(){
        DiGraph<Integer> reverseG = G.reverse();

        assertEquals(13,G.numberOfVertices());
        assertEquals(22,G.numberOfEdges());

        assertEquals(13,reverseG.numberOfVertices());
        assertEquals(22,reverseG.numberOfEdges());

        assertEquals(4, G.getOutDegree(6));
        assertEquals(2, G.getOutDegree(0));
        assertEquals(0, G.getOutDegree(1));

        assertEquals(1, G.getInDegree(1));
        assertEquals(0, G.getInDegree(7));
        assertEquals(3, G.getInDegree(4));


        assertEquals(4, reverseG.getInDegree(6));
        assertEquals(2, reverseG.getInDegree(0));
        assertEquals(0, reverseG.getInDegree(1));

        assertEquals(1, reverseG.getOutDegree(1));
        assertEquals(0, reverseG.getOutDegree(7));
        assertEquals(3, reverseG.getOutDegree(4));

        System.out.println(reverseG);


    }

/*    @Test
    public void test1(){
        //System.out.println(G);
        assertEquals(13,G.numberOfVertices());
        assertEquals(22,G.numberOfEdges());

        assertEquals(4, G.getOutDegree(6));
        assertEquals(2, G.getOutDegree(0));
        assertEquals(0, G.getOutDegree(1));

        assertEquals(1, G.getInDegree(1));
        assertEquals(0, G.getInDegree(7));
        assertEquals(3, G.getInDegree(4));

        G.removeVertex(6);
        assertEquals(12,G.numberOfVertices());
        assertEquals(18,G.numberOfEdges());

        G.removeEdge(4,2);
        assertEquals(12,G.numberOfVertices());
        assertEquals(17,G.numberOfEdges());

        G.removeEdge(9,8); // does not exist
        assertEquals(12,G.numberOfVertices());
        assertEquals(17,G.numberOfEdges());

    }*/


}
