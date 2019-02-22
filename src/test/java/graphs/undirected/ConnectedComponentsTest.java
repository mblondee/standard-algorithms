package graphs.undirected;

import graphs.undirected.ConnectedComponents;
import graphs.undirected.Graph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class ConnectedComponentsTest {
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
        ConnectedComponents<Integer> cc = new ConnectedComponents<>(G);
        //component: 0, 1,2,3,4,5,6
        // component: 7,8
        //component: 9,10,11,12
        assertTrue(cc.connected(1,2));
        assertTrue(cc.connected(1,3));
        assertTrue(cc.connected(1,4));
        assertTrue(cc.connected(1,5));
        assertTrue(cc.connected(1,6));
        assertTrue(cc.connected(6,1));

        assertTrue(cc.connected(7,8));

        assertTrue(cc.connected(9,10));
        assertTrue(cc.connected(10,11));
        assertTrue(cc.connected(9,11));

        assertFalse(cc.connected(1,7));
        assertFalse(cc.connected(1,9));
        assertFalse(cc.connected(1,12));

        assertEquals(7, cc.size(1));
        assertEquals(7, cc.size(6));
        assertEquals(2, cc.size(7));
        assertEquals(4, cc.size(11));

        assertEquals(3, cc.numberOfConnectedComponents());

    }
}
