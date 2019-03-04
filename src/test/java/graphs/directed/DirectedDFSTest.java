package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DirectedDFSTest {

    private static DiGraph<Integer> G;
    private static EdgeWeightedDiGraph<Integer> G1;

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

        String fileName1 = "src/main/resources/tinyWeightedEdge.txt";
        G1 = new EdgeWeightedDiGraph<>();

        String line1;
        try {
            FileReader inputFile = new FileReader(fileName1);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            while ( (line1 = bufferReader.readLine()) != null){
                String lineString = line1.trim();
                Integer v1 = Integer.parseInt(lineString.split(" ")[0]);
                Integer v2 = Integer.parseInt(lineString.split(" ")[1]);
                double w = Double.parseDouble(lineString.split(" ")[2]);
                G1.addVertex(v1);
                G1.addVertex(v2);
                DirectedEdge<Integer> e = new DirectedEdge<>(v1,v2, w);
                G1.addEdge(e);
            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }
    }


    @Test
    public void test(){
        DirectedDFS<Integer> dfs = new DirectedDFS<>(G,4 );
        assertTrue(dfs.hasPathTo(2));
        assertTrue(dfs.hasPathTo(3));
        assertTrue(dfs.hasPathTo(0));
        assertTrue(dfs.hasPathTo(5));
        assertTrue(dfs.hasPathTo(1));
        assertTrue(dfs.hasPathTo(4));

        assertFalse(dfs.hasPathTo(6));
        assertFalse(dfs.hasPathTo(7));
        assertFalse(dfs.hasPathTo(8));
        assertFalse(dfs.hasPathTo(9));
        assertFalse(dfs.hasPathTo(10));
        assertFalse(dfs.hasPathTo(11));
        assertFalse(dfs.hasPathTo(12));

        System.out.println(dfs.pathTo(5));


    }

    @Test
    public void test1(){
        DirectedDFS<Integer> dfs = new DirectedDFS<>(G1,4 );
        assertFalse(dfs.hasPathTo(0));
        assertFalse(dfs.hasPathTo(1));
        assertFalse(dfs.hasPathTo(2));
        assertFalse(dfs.hasPathTo(3));
        assertTrue(dfs.hasPathTo(4));
        assertTrue(dfs.hasPathTo(5));
        assertFalse(dfs.hasPathTo(6));
        assertTrue(dfs.hasPathTo(7));
    }

}
