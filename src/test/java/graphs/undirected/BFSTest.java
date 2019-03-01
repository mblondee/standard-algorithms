package graphs.undirected;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class BFSTest {

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
    public void smallTest(){
        //System.out.println(G);
        source = 0;
        assertEquals(13, G.numberOfVertices());
        assertEquals(13, G.numberOfEdges());
        BFS<Integer> bfs = new BFS<>(G, source );
        // connected to 0: 1,2,5,3,4,6
        // not connected to 0: 7,8, 9,10,11,12
        assertTrue(bfs.hasPathTo(1) && bfs.hasPathTo(2) && bfs.hasPathTo(3) && bfs.hasPathTo(4)
                && bfs.hasPathTo(5) && bfs.hasPathTo(6));
        assertFalse(bfs.hasPathTo(7) || bfs.hasPathTo(8) ||bfs.hasPathTo(9) || bfs.hasPathTo(10)
                || bfs.hasPathTo(11) && bfs.hasPathTo(12));

        assertEquals(0, bfs.distanceTo(0));
        assertEquals(1, bfs.distanceTo(1));
        assertEquals(1, bfs.distanceTo(2));
        assertEquals(2, bfs.distanceTo(3));
        assertEquals(2, bfs.distanceTo(4));
        assertEquals(1, bfs.distanceTo(5));
        assertEquals(1, bfs.distanceTo(6));
        assertEquals(Integer.MAX_VALUE, bfs.distanceTo(7));
        assertEquals(Integer.MAX_VALUE, bfs.distanceTo(8));
        assertEquals(Integer.MAX_VALUE, bfs.distanceTo(9));
        assertEquals(Integer.MAX_VALUE, bfs.distanceTo(10));
        assertEquals(Integer.MAX_VALUE, bfs.distanceTo(11));
        assertEquals(Integer.MAX_VALUE, bfs.distanceTo(12));


        System.out.println("path to 6");
        for(Integer i : bfs.pathTo(6)){
            System.out.println(i);
        }
        System.out.println("path to 8");
        for(Integer i : bfs.pathTo(8)){
            System.out.println(i);
        }
        System.out.println("path to 2");
        for(Integer i : bfs.pathTo(2)){
            System.out.println(i);
        }
        System.out.println("path to 4");
        for(Integer i : bfs.pathTo(4)){
            System.out.println(i);
        }
    }


}
