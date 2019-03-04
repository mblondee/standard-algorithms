package graphs.directed;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

import static org.junit.Assert.*;

public class StrongComponentTest {

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


    @Test
    public void test1(){
        StrongComponent<Integer> sc = new StrongComponent<>(G1);
        assertTrue(sc.getPathFrom(6).contains(0));
        assertFalse(sc.getPathFrom(6).contains(1));
        assertTrue(sc.getPathFrom(6).contains(2));
        assertTrue(sc.getPathFrom(6).contains(3));
        assertTrue(sc.getPathFrom(6).contains(4));
        assertTrue(sc.getPathFrom(6).contains(5));
        assertTrue(sc.getPathFrom(6).contains(6));
        assertTrue(sc.getPathFrom(6).contains(7));

        assertTrue(sc.getPathTo(6).contains(0));
        assertTrue(sc.getPathTo(6).contains(1));
        assertTrue(sc.getPathTo(6).contains(2));
        assertTrue(sc.getPathTo(6).contains(3));
        assertFalse(sc.getPathTo(6).contains(4));
        assertFalse(sc.getPathTo(6).contains(5));
        assertTrue(sc.getPathTo(6).contains(6));
        assertFalse(sc.getPathTo(6).contains(7));

        assertTrue(sc.strongComponent(6).contains(0));
        assertFalse(sc.strongComponent(6).contains(1));
        assertTrue(sc.strongComponent(6).contains(2));
        assertTrue(sc.strongComponent(6).contains(3));
        assertFalse(sc.strongComponent(6).contains(4));
        assertFalse(sc.strongComponent(6).contains(5));
        assertTrue(sc.strongComponent(6).contains(6));
        assertFalse(sc.strongComponent(6).contains(7));




/*        for(Integer i: sc.getPathFrom(6)){
            System.out.println(i);
        }*/
/*        for(Integer i: sc.getPathTo(6)){
            System.out.println(i);
        }*/
    }
}
