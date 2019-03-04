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

}
