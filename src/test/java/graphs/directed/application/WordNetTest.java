package graphs.directed.application;


import graphs.directed.DiGraph;
import graphs.directed.DirectedEdge;
import graphs.directed.application.wordnet.AncestorBFS;
import graphs.directed.application.wordnet.Outcast;
import graphs.directed.application.wordnet.ShortestAncestralPath;
import graphs.directed.application.wordnet.WordNet;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WordNetTest {

    @Test
    public void test1(){
        String synsetstxt = "src/main/resources/synsets.txt";
        String hypernymstxt = "src/main/resources/hypernyms.txt";
        WordNet net = new WordNet(synsetstxt, hypernymstxt);
        assertTrue(net.getGraphOfSynsets().containsVertex(new Integer(79541)));
        assertEquals(82192, net.getGraphOfSynsets().numberOfVertices());

        assertTrue(net.isNoun("1750s"));
        assertTrue(net.isNoun("Chukchi"));
        assertFalse(net.isNoun("cuucki"));
    }

    @Test
    public void test15(){
        String synsetstxt = "src/main/resources/synsets15.txt";
        String hypernymstxt = "src/main/resources/hypernyms15tree.txt";
        WordNet net = new WordNet(synsetstxt, hypernymstxt);

        assertEquals(15, net.getGraphOfSynsets().numberOfVertices());
        assertEquals(14, net.getGraphOfSynsets().numberOfEdges());

        assertTrue(net.isNoun("a"));
        assertTrue(net.isNoun("one"));
        assertTrue(net.isNoun("twelve"));
        assertFalse(net.isNoun("z"));
        assertFalse(net.isNoun("sixteen"));

        assertEquals(4, net.distance("l", "o"));
        assertEquals(4, net.distance("l", "fifteen"));
        assertEquals(4, net.distance("twelve", "o"));
        assertEquals(4, net.distance("twelve", "fifteen"));

        assertEquals("k,eleven", (net.sap("l", "fifteen")));
        assertEquals("a,one", (net.sap("five", "three")));
    }

    @Test
    public void testOutcast(){
        String synsetstxt = "src/main/resources/synsets15.txt";
        String hypernymstxt = "src/main/resources/hypernyms15tree.txt";
        WordNet net = new WordNet(synsetstxt, hypernymstxt);
        Outcast outcastNet = new Outcast(net);
        String[] nouns = {"two", "e", "eight", "o"};
        assertEquals("o", outcastNet.outcast(nouns));

        String[] nouns1 = {"l", "m", "seven"};
        assertEquals("l", outcastNet.outcast(nouns1));
    }

    @Test
    public void AncestorBFSTest(){
        DiGraph<Integer> G = new DiGraph<>();
        for(Integer i = 0; i<12; i++){
            G.addVertex(i);
        }
        G.addEdge(new DirectedEdge<>(6,3));
        G.addEdge(new DirectedEdge<>(7,3));
        G.addEdge(new DirectedEdge<>(3,1));
        G.addEdge(new DirectedEdge<>(4,1));
        G.addEdge(new DirectedEdge<>(8,5));
        G.addEdge(new DirectedEdge<>(10,9));
        G.addEdge(new DirectedEdge<>(11,9));
        G.addEdge(new DirectedEdge<>(9,5));
        G.addEdge(new DirectedEdge<>(5,1));
        G.addEdge(new DirectedEdge<>(1,0));
        G.addEdge(new DirectedEdge<>(2,0));


        assertEquals(11, G.numberOfEdges());

        AncestorBFS<Integer> bfs = new AncestorBFS<>(G, 3, 10);
        assertEquals(new Integer(1), bfs.getMinAncestor());
        assertEquals(4, bfs.getMinLengthPath());


        AncestorBFS<Integer> bfs1 = new AncestorBFS<>(G, 6, 9);
        assertEquals(new Integer(1), bfs1.getMinAncestor());
        assertEquals(4, bfs1.getMinLengthPath());

        AncestorBFS<Integer> bfs2 = new AncestorBFS<>(G, 10, 2);
        assertEquals(new Integer(0), bfs2.getMinAncestor());
        assertEquals(5, bfs2.getMinLengthPath());

        G.removeEdge(new DirectedEdge<>(2,0)); // werkt nog??
        AncestorBFS<Integer> bfs3 = new AncestorBFS<>(G, 10, 2);
        assertEquals(null,  bfs3.getMinAncestor());
        assertEquals(Integer.MAX_VALUE, bfs3.getMinLengthPath());
    }

    @Test
    public void testShortestPath(){
        DiGraph<Integer> G = new DiGraph<>();
        for(Integer i = 0; i<12; i++){
            G.addVertex(i);
        }
        G.addEdge(new DirectedEdge<>(6,3));
        G.addEdge(new DirectedEdge<>(7,3));
        G.addEdge(new DirectedEdge<>(3,1));
        G.addEdge(new DirectedEdge<>(4,1));
        G.addEdge(new DirectedEdge<>(8,5));
        G.addEdge(new DirectedEdge<>(10,9));
        G.addEdge(new DirectedEdge<>(11,9));
        G.addEdge(new DirectedEdge<>(9,5));
        G.addEdge(new DirectedEdge<>(5,1));
        G.addEdge(new DirectedEdge<>(1,0));
        G.addEdge(new DirectedEdge<>(2,0));


        ShortestAncestralPath<Integer> sap = new ShortestAncestralPath<>(G);
        assertEquals(5, sap.getLength(6,10));
        assertEquals(new Integer(1), sap.getAncestor(6,10));

        assertEquals(3, sap.getLength(4,2));
        assertEquals(new Integer(0), sap.getAncestor(4,2));

        G.removeEdge(new DirectedEdge<>(2,0)); // werkt???
        assertEquals(-1, sap.getLength(4,2));
        assertEquals(null, sap.getAncestor(4,2));

    }

    @Test
    public void testShortestPathBetweenIter(){
        DiGraph<Integer> G = new DiGraph<>();
        for(Integer i = 0; i<12; i++){
            G.addVertex(i);
        }

        G.addEdge(new DirectedEdge<>(6,3));
        G.addEdge(new DirectedEdge<>(7,3));
        G.addEdge(new DirectedEdge<>(3,1));
        G.addEdge(new DirectedEdge<>(4,1));
        G.addEdge(new DirectedEdge<>(8,5));
        G.addEdge(new DirectedEdge<>(10,9));
        G.addEdge(new DirectedEdge<>(11,9));
        G.addEdge(new DirectedEdge<>(9,5));
        G.addEdge(new DirectedEdge<>(5,1));
        G.addEdge(new DirectedEdge<>(1,0));
        G.addEdge(new DirectedEdge<>(2,0));


        ShortestAncestralPath<Integer> sap = new ShortestAncestralPath<>(G);
        List<Integer> lst1 = new ArrayList<>();
        List<Integer> lst2 = new ArrayList<>();

        lst1.add(6);
        lst1.add(4);
        lst2.add(8);
        lst2.add(11);

        assertEquals(3, sap.getLength(lst1, lst2));
        assertEquals(new Integer(1), sap.getAncestor(lst1, lst2));

        List<Integer> lst3 = new ArrayList<>();
        List<Integer> lst4 = new ArrayList<>();
        lst3.add(6);
        lst3.add(1);
        lst3.add(8);
        lst4.add(2);
        lst4.add(10);

        assertEquals(2, sap.getLength(lst3, lst4));
        assertEquals(new Integer(0), sap.getAncestor(lst3, lst4));

    }
}
