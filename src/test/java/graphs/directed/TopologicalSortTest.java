package graphs.directed;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class TopologicalSortTest {
    @Test
    public void test(){
        DiGraph<Integer> G = new DiGraph<>();
        for(Integer i = 0; i<=5; i++){
            G.addVertex(i);
        }
        G.addEdge(5,2);
        G.addEdge(5,0);
        G.addEdge(4,0);
        G.addEdge(4,1);
        G.addEdge(2,3);
        G.addEdge(3,1);

        TopologicalSort<Integer> top = new TopologicalSort<>(G);
        for(Integer i : top.getOrder()){
            System.out.println(i);
        }
    }

}
