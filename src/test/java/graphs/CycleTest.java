package graphs;

import graphs.undirected.Cycle;
import graphs.undirected.Graph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class CycleTest {

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
        Cycle<Integer> cyc = new Cycle<>(G);
//        for(Integer i : cyc.getCycle()){
//            System.out.println(i);
//        }
        assertTrue(cyc.hasCycle());
    }

    @Test
    public void test1(){
        Graph<Integer> G1 = new Graph();
        G1.addVertex(0);
        G1.addVertex(1);
        G1.addVertex(2);
        G1.addVertex(3);
        G1.addVertex(4);
        G1.addEdge(1,2);
        G1.addEdge(2,3);
        Cycle<Integer> cyc1 = new Cycle<>(G1);
        assertFalse(cyc1.hasCycle());
        G1.addEdge(3,1);
        Cycle<Integer> cyc2 = new Cycle<>(G1);
        assertTrue(cyc2.hasCycle());
        Cycle<Integer> cyc = new Cycle<>(G1);
        for(Integer i : cyc.getCycle()){
            System.out.println(i);
        }



    }

}
