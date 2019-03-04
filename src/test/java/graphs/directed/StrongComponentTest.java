package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

import static org.junit.Assert.*;

public class StrongComponentTest {

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
                G.addEdge(new DirectedEdge<>(v1,v2));
            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }
    }

    @Test
    public void test(){

        StrongComponent<Integer> sc = new StrongComponent<>(G);
        HashSet<Integer> elFromFour = sc.getPathFrom(4);
        for(Integer i = 0; i <= 5; i++){
            assertTrue(elFromFour.contains(i));
        }
        for(Integer i = 6; i <= 12; i++){
            assertFalse(elFromFour.contains(i));
        }

        HashSet<Integer> elToFour = sc.getPathTo(4);
        for(Integer i = 2; i <= 12; i++){
            assertTrue(elToFour.contains(i));
        }
        assertFalse(elToFour.contains(1));
        assertTrue(elToFour.contains(0));


        HashSet<Integer> componentFour = sc.strongComponent(4);
        assertTrue(componentFour.contains(0));
        assertFalse(componentFour.contains(1));
        for(Integer i = 2; i<= 5; i++){
            assertTrue(componentFour.contains(i));
        }
        for(Integer i = 6; i<= 12; i++){
            assertFalse(componentFour.contains(i));
        }


        HashSet<Integer> elFromEleven = sc.getPathFrom(11);

        for(Integer i = 0; i <= 5; i++){
            assertTrue(elFromEleven.contains(i));
        }
        for(Integer i = 6; i <= 8; i++){
            assertFalse(elFromEleven.contains(i));
        }
        for(Integer i = 9; i <= 12; i++){
            assertTrue(elFromEleven.contains(i));
        }

        HashSet<Integer> elToEleven = sc.getPathTo(11);
        for(Integer i = 0; i <= 5; i++){
            assertFalse(elToEleven.contains(i));
        }
        for(Integer i = 6; i <= 12; i++){
            assertTrue(elToEleven.contains(i));
        }

        HashSet<Integer> componentEleven = sc.strongComponent(11);

        for(Integer i = 9; i<= 12; i++){
            assertTrue(componentEleven.contains(i));
        }
        for(Integer i = 0; i<= 8; i++){
            assertFalse(componentEleven.contains(i));
        }
    }

}
