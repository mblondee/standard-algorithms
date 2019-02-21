package graphs;


import graphs.directed.DiGraph;
import graphs.directed.DirectedCycle;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DirectedCycleTest {

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
        DirectedCycle<Integer> cyc = new DirectedCycle<>(G);
        assertTrue(cyc.hasCycle());
        for(Integer i : cyc.getCycle()){
            System.out.println(i);
        }
    }

    @Test
    public void test1(){
        DiGraph<String> G1 = new DiGraph<>();
        G1.addVertex("a");
        G1.addVertex("b");
        G1.addVertex("c");
        G1.addEdge("a", "b");
        G1.addEdge("b", "c");
        G1.addEdge("a", "c");
        DirectedCycle<String> cyc = new DirectedCycle<>(G1);
        assertFalse(cyc.hasCycle());
        G1.addEdge("c", "a");
        DirectedCycle<String> cyc1 = new DirectedCycle<>(G1);
        assertTrue(cyc1.hasCycle());
        for(String i : cyc1.getCycle()){
            System.out.println(i);
        }
    }
}
