package graphs.undirected;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;


import static org.junit.Assert.*;

public class MSTTest {


    private static EdgeWeightedGraph<Integer> G;

    @BeforeClass
    public static void initialize(){
        String fileName = "src/main/resources/tinyWeightedEdge.txt";
        G = new EdgeWeightedGraph<>();

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
                Edge<Integer> e = new Edge<>(v1,v2, w);
                G.addEdge(e);
            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }
    }

    @Test
    public void KruksalTest(){
        //System.out.println(G);
        MSTKruskal<Integer> mst = new MSTKruskal<>(G);
        for(Edge<Integer> e: mst.MST()){
            System.out.println(e);
        }
        assertEquals(1.81, mst.getWeight(), 0.00001);

    }
}
