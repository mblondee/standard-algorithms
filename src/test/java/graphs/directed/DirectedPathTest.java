package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DirectedPathTest {


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
        DirectedPath<Integer> path = new DirectedPath<>(G, 4);
        for(Integer i = 0; i <= 5; i++){
            assertTrue(path.onPath(i));
        }

        for(Integer i = 6; i <= 12; i++){
            assertFalse(path.onPath(i));
        }

        System.out.println("on path from 4");
        for(Integer i : path.getPathFromSource()){
            System.out.println(i);
        }

        DirectedPath<Integer> path1 = new DirectedPath<>(G, 11);
        for(Integer i = 0; i <= 5; i++){
            assertTrue(path1.onPath(i));
        }

        for(Integer i = 6; i <= 8; i++){
            assertFalse(path1.onPath(i));
        }

        for(Integer i = 9; i <= 12; i++){
            assertTrue(path1.onPath(i));
        }

        System.out.println("on path from 11");
        for(Integer i : path1.getPathFromSource()){
            System.out.println(i);
        }
    }

    @Test
    public void test1(){
        DirectedPath<Integer> path = new DirectedPath<>(G1, 0);
        assertTrue(path.onPath(0));
        assertFalse(path.onPath(1));
        assertTrue(path.onPath(2));
        assertTrue(path.onPath(3));
        assertTrue(path.onPath(4));
        assertTrue(path.onPath(5));
        assertTrue(path.onPath(6));
        assertTrue(path.onPath(7));

        DirectedPath<Integer> path1 = new DirectedPath<>(G1, 4);
        assertFalse(path1.onPath(0));
        assertFalse(path1.onPath(1));
        assertFalse(path1.onPath(2));
        assertFalse(path1.onPath(3));
        assertTrue(path1.onPath(4));
        assertTrue(path1.onPath(5));
        assertFalse(path1.onPath(6));
        assertTrue(path1.onPath(7));

        System.out.println(G1);
    }
}
