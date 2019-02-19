package graphs;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DFSTest {

    private static Graph<Integer> G;
    private static Integer source;

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
    public void test1(){
        assertEquals(250, G.numberOfVertices());
        assertEquals(1273, G.numberOfEdges());
        source = 244;
        DFS<Integer> dfs = new DFS<>(G, source );
    }

    @Test
    public void smallTest(){
        //System.out.println(G);
        source = 0;
        assertEquals(13, G.numberOfVertices());
        assertEquals(13, G.numberOfEdges());
        DFS<Integer> dfs = new DFS<>(G, source );
        // connected to 0: 1,2,5,3,4,6
        // not connected to 0: 7,8, 9,10,11,12
        assertTrue(dfs.hasPathTo(1) && dfs.hasPathTo(2) && dfs.hasPathTo(3) && dfs.hasPathTo(4)
        && dfs.hasPathTo(5) && dfs.hasPathTo(6));
        assertFalse(dfs.hasPathTo(7) || dfs.hasPathTo(8) ||dfs.hasPathTo(9) || dfs.hasPathTo(10)
                || dfs.hasPathTo(11) && dfs.hasPathTo(12));
        System.out.println("path to 6");
        for(Integer i : dfs.pathTo(6)){
            System.out.println(i);
        }
        System.out.println("path to 8");
        for(Integer i : dfs.pathTo(8)){
            System.out.println(i);
        }
        System.out.println("path to 2");
        for(Integer i : dfs.pathTo(2)){
            System.out.println(i);
        }
    }
}
