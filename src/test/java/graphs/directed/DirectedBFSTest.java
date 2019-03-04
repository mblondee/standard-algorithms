package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DirectedBFSTest {

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
        DirectedBFS<Integer> bfs = new DirectedBFS<>(G, 3);
        assertTrue(bfs.hasPathTo(0));
        assertTrue(bfs.hasPathTo(1));
        assertTrue(bfs.hasPathTo(3));
        assertTrue(bfs.hasPathTo(4));
        assertTrue(bfs.hasPathTo(5));


        assertFalse(bfs.hasPathTo(6));
        assertFalse(bfs.hasPathTo(7));
        assertFalse(bfs.hasPathTo(8));
        assertFalse(bfs.hasPathTo(9));
        assertFalse(bfs.hasPathTo(10));
        assertFalse(bfs.hasPathTo(11));
        assertFalse(bfs.hasPathTo(12));

        assertEquals(2, bfs.distanceTo(0));
        assertEquals(3, bfs.distanceTo(1));
        assertEquals(1, bfs.distanceTo(2));
        assertEquals(0, bfs.distanceTo(3));
        assertEquals(2, bfs.distanceTo(4));
        assertEquals(1, bfs.distanceTo(5));


    }

    @Test
    public void printpath(){
        DirectedBFS<Integer> bfs = new DirectedBFS<>(G, 3);
        for(Integer v: G.getVertices()){
            System.out.println(v + " :");
            if(bfs.hasPathTo(v)){
                for(Integer i : bfs.pathTo(v)){
                    if (i.equals(3)) {
                        System.out.print(i);
                    }
                    else{
                        System.out.print(" -> " + i );
                    }
                    }
                }
                System.out.println();
            }
        }

    @Test
    public void test1(){
        DirectedBFS<Integer> bfs = new DirectedBFS<>(G1,4 );
        assertFalse(bfs.hasPathTo(0));
        assertFalse(bfs.hasPathTo(1));
        assertFalse(bfs.hasPathTo(2));
        assertFalse(bfs.hasPathTo(3));
        assertTrue(bfs.hasPathTo(4));
        assertTrue(bfs.hasPathTo(5));
        assertFalse(bfs.hasPathTo(6));
        assertTrue(bfs.hasPathTo(7));

        assertEquals(0, bfs.distanceTo(4));
        assertEquals(1, bfs.distanceTo(5));
        assertEquals(1, bfs.distanceTo(7));
    }


}
