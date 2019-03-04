package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class TopologicalSortTest {

    private static EdgeWeightedDiGraph<Integer> G1;

    @BeforeClass
    public static void initialize(){

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
        DiGraph<Integer> G = new DiGraph<>();
        for(Integer i = 0; i<=5; i++){
            G.addVertex(i);
        }
        G.addEdge(new DirectedEdge<>(5,2));
        G.addEdge(new DirectedEdge<>(5,0));
        G.addEdge(new DirectedEdge<>(4,0));
        G.addEdge(new DirectedEdge<>(4,1));
        G.addEdge(new DirectedEdge<>(2,3));
        G.addEdge(new DirectedEdge<>(3,1));

        TopologicalSort<Integer> top = new TopologicalSort<>(G);
        for(Integer i : top.getOrder()){
            System.out.println(i);
        }
    }

    @Test
    public void test1(){
        TopologicalSort<Integer> top = new TopologicalSort<>(G1);
        for(Integer i : top.getOrder()){
            System.out.println(i);
        }
        G1.removeEdge(new DirectedEdge<>(6,2,0.40));
        G1.removeEdge(new DirectedEdge<>(6,0,0.58));
        top = new TopologicalSort<>(G1);
        System.out.println(G1);
        for(Integer i : top.getOrder()){
            System.out.println(i);
        }

    }

}
