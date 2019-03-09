package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DijkstraTest {

    private static EdgeWeightedDiGraph<Integer> G;

    @BeforeClass
    public static void initialize(){
        String fileName = "src/main/resources/tinyWeightedEdge.txt";
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
    }

    @Test
    public void test(){
        Dijkstra<Integer> SP = new Dijkstra<>(G, 0);
        assertTrue(SP.hasPathTo(0));
        assertEquals(0.0, SP.getDistance(0), 0.0001);

        assertFalse(SP.hasPathTo(1));

        assertTrue(SP.hasPathTo(2));
        assertEquals(0.26, SP.getDistance(2), 0.0001);

        assertTrue(SP.hasPathTo(3));
        assertEquals(0.43, SP.getDistance(3), 0.0001);

        assertTrue(SP.hasPathTo(4));
        assertEquals(0.38, SP.getDistance(4), 0.0001);

        assertTrue(SP.hasPathTo(5));
        assertEquals(0.73, SP.getDistance(5), 0.0001);

        assertTrue(SP.hasPathTo(6));
        assertEquals(0.95, SP.getDistance(6), 0.0001);

        assertTrue(SP.hasPathTo(7));
        assertEquals(0.16, SP.getDistance(7), 0.0001);


    }

    @Test
    public void test1(){
        EdgeWeightedDiGraph<String> G = new EdgeWeightedDiGraph<>();
        G.addVertex("a");
        G.addVertex("b");
        G.addVertex("c");
        G.addVertex("d");
        G.addVertex("e");
        G.addVertex("f");
        G.addVertex("g");

        G.addEdge(new DirectedEdge<>("a", "b", 10 ));
        G.addEdge(new DirectedEdge<>("a", "c", 15 ));
        G.addEdge(new DirectedEdge<>("b", "f", 15 ));
        G.addEdge(new DirectedEdge<>("b", "d", 12 ));
        G.addEdge(new DirectedEdge<>("c", "e", 10 ));
        G.addEdge(new DirectedEdge<>("d", "e", 2 ));
        G.addEdge(new DirectedEdge<>("d", "f", 1 ));
        G.addEdge(new DirectedEdge<>("f", "e", 5 ));
        G.addEdge(new DirectedEdge<>("g", "e", 5 ));

        Dijkstra<String> SP = new Dijkstra<>(G, "a");

        assertEquals(0.0, SP.getDistance("a"), 0.001);
        assertEquals(10.0, SP.getDistance("b"), 0.001);
        assertEquals(15.0, SP.getDistance("c"), 0.001);
        assertEquals(22.0, SP.getDistance("d"), 0.001);
        assertEquals(24.0, SP.getDistance("e"), 0.001);
        assertEquals(23.0, SP.getDistance("f"), 0.001);
        assertTrue(SP.hasPathTo("f"));
        assertFalse(SP.hasPathTo("g"));

        for(DirectedEdge<String> edge : SP.getPathTo("e")){
            System.out.println(edge);
        }


    }
}
