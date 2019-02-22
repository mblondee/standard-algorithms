package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DirectedPathTest {


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
}
