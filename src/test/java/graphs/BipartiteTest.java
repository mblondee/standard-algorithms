package graphs;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;


public class BipartiteTest {
    private static Graph<Integer> G;

    @BeforeClass
    public static void initialize(){
        //String fileName = "src/main/resources/graph.txt";
        String fileName = "src/main/resources/smallgraph.txt";
        G = new Graph();

        String line;
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            while ( (line = bufferReader.readLine()) != null){
                String lineString = line.trim();
                Integer v1 = Integer.parseInt(lineString.split(" ")[0]);
                Integer v2 = Integer.parseInt(lineString.split(" ")[1]);
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
    public void test(){
        Bipartite<Integer> bip = new Bipartite<>(G);
        assertFalse(bip.isBipartite());
    }

    @Test
    public void test1(){
        Graph<String> G1 = new Graph();
        G1.addVertex("a");
        G1.addVertex("b");
        G1.addVertex("c");
        G1.addEdge("a", "b");
        G1.addEdge("b", "c");
        Bipartite<String> bip = new Bipartite<>(G1);
        assertTrue(bip.isBipartite());
        G1.addEdge("a", "c");
        Bipartite<String> bip1 = new Bipartite<>(G1);
        assertFalse(bip1.isBipartite());
    }
}
