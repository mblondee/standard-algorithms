package graphs.directed;

import graphs.directed.DiGraph;
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
                G.addEdge(v1,v2);
            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }
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
