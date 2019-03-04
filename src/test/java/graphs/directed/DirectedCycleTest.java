package graphs.directed;


import graphs.directed.DiGraph;
import graphs.directed.DirectedCycle;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DirectedCycleTest {

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
        G1.addEdge(new DirectedEdge<>("a", "b"));
        G1.addEdge(new DirectedEdge<>("b", "c"));
        G1.addEdge(new DirectedEdge<>("a", "c"));
        DirectedCycle<String> cyc = new DirectedCycle<>(G1);
        assertFalse(cyc.hasCycle());
        G1.addEdge(new DirectedEdge<>("c", "a"));
        DirectedCycle<String> cyc1 = new DirectedCycle<>(G1);
        assertTrue(cyc1.hasCycle());
        for(String i : cyc1.getCycle()){
            System.out.println(i);
        }
    }

    @Test
    public void test2(){
        DirectedCycle<Integer> cyc = new DirectedCycle<>(G1);
        assertTrue(cyc.hasCycle());

/*        System.out.println(G1);
        System.out.println("----");*/
        G1.removeEdge(new DirectedEdge<>(6,2,0.40));
        cyc = new DirectedCycle<>(G1);
        assertTrue(cyc.hasCycle());

        G1.removeEdge(new DirectedEdge<>(6,0,0.58));
        cyc = new DirectedCycle<>(G1);

        assertFalse(cyc.hasCycle());
        //System.out.println(G1);

        for(Integer i: cyc.getCycle()){
            System.out.println(i);
        }


    }
}
