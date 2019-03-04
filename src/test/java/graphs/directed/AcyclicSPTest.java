package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class AcyclicSPTest {

    private static EdgeWeightedDiGraph<Integer> G;

    @BeforeClass
    public static void initialize(){
        String fileName = "src/main/resources/tinyWeightedEdgeDag.txt";
        G = new EdgeWeightedDiGraph<>();

        String line;
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            while ( (line = bufferReader.readLine()) != null){
                String lineString = line.trim();
                Integer v1 = Integer.parseInt(lineString.split(" ")[0]);
                Integer v2 = Integer.parseInt(lineString.split(" ")[1]);
                double w = Double.parseDouble(lineString.split(" ")[2]);
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
    }

    @Test
    public void test(){
        AcyclicShortestPath<Integer> SP = new AcyclicShortestPath<>(G, 5);
        assertTrue(SP.hasPathTo(0));
        assertEquals(0.73, SP.getDistance(0), 0.0001);


        assertTrue(SP.hasPathTo(1));
        assertEquals(0.32, SP.getDistance(1), 0.0001);

        assertTrue(SP.hasPathTo(2));
        assertEquals(0.62, SP.getDistance(2), 0.0001);

        assertTrue(SP.hasPathTo(3));
        assertEquals(0.61, SP.getDistance(3), 0.0001);

        assertTrue(SP.hasPathTo(4));
        assertEquals(0.35, SP.getDistance(4), 0.0001);

        assertTrue(SP.hasPathTo(5));
        assertEquals(0.00, SP.getDistance(5), 0.0001);

        assertTrue(SP.hasPathTo(6));
        assertEquals(1.13, SP.getDistance(6), 0.0001);

        assertTrue(SP.hasPathTo(7));
        assertEquals(0.28, SP.getDistance(7), 0.0001);


    }
}
