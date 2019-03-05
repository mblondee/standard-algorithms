package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class BellmanFordShortestPathTest {
    private static EdgeWeightedDiGraph<Integer> G;
    private static EdgeWeightedDiGraph<Integer> G1;

    @BeforeClass
    public static void initialize(){
        String fileName = "src/main/resources/tinyWeightedEdgeDagNegative.txt";
        G = new EdgeWeightedDiGraph<>();

        String line;
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            while ( (line = bufferReader.readLine()) != null){
                String lineString = line.trim();
                Integer v1 = Integer.parseInt(lineString.split("\\s+")[0]);
                Integer v2 = Integer.parseInt(lineString.split("\\s+")[1]);
                double w = Double.parseDouble(lineString.split("\\s+")[2]);
                G.addVertex(v1);
                G.addVertex(v2);
                DirectedEdge<Integer> e = new DirectedEdge<>(v1,v2, w);
                G.addEdge(e);
            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }


        fileName = "src/main/resources/tinyWeightedEdgeDagNegativeCycle.txt";
        G1 = new EdgeWeightedDiGraph<>();

        String line1;
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            while ( (line1 = bufferReader.readLine()) != null){
                String lineString = line1.trim();
                Integer v1 = Integer.parseInt(lineString.split("\\s+")[0]);
                Integer v2 = Integer.parseInt(lineString.split("\\s+")[1]);
                double w = Double.parseDouble(lineString.split("\\s+")[2]);
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
        BellmanFordShortestPath<Integer> bel = new BellmanFordShortestPath<>(G,0);
        assertTrue(bel.hasPathTo(0));
        assertEquals(0.0, bel.getDistance(0), 0.00001);
        assertFalse(bel.hasNegativeCycle());
        assertTrue(bel.hasPathTo(1));
        assertEquals(0.93, bel.getDistance(1), 0.00001);
        assertTrue(bel.hasPathTo(2));
        assertEquals(0.26, bel.getDistance(2), 0.00001);
        assertTrue(bel.hasPathTo(3));
        assertEquals(0.99, bel.getDistance(3), 0.00001);
        assertTrue(bel.hasPathTo(4));
        assertEquals(0.26, bel.getDistance(4), 0.00001);
        assertTrue(bel.hasPathTo(5));
        assertEquals(0.61, bel.getDistance(5), 0.00001);
        assertTrue(bel.hasPathTo(6));
        assertEquals(1.51, bel.getDistance(6), 0.00001);
        assertTrue(bel.hasPathTo(7));
        assertEquals(0.60, bel.getDistance(7), 0.00001);
    }

    @Test
    public void test1(){
        BellmanFordShortestPath<Integer> bel = new BellmanFordShortestPath<>(G1,0);
        assertTrue(bel.hasNegativeCycle());
    }

}
