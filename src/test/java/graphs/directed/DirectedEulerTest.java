package graphs.directed;

import graphs.directed.DiGraph;
import graphs.directed.DirectedEuler;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;


public class DirectedEulerTest {

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
        DiGraph<Integer> G1 = new DiGraph<>();
        G1.addVertex(0);
        G1.addVertex(1);
        G1.addVertex(2);
        G1.addVertex(3);
        G1.addVertex(4);
        G1.addEdge(0,1);
        G1.addEdge(1,2);
        G1.addEdge(2,0);
        G1.addEdge(0,3);
        G1.addEdge(3,4);
        G1.addEdge(4,0);

        DirectedEuler<Integer> euler = new DirectedEuler<>(G1);
    }
}
