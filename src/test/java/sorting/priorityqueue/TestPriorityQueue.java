package sorting.priorityqueue;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPriorityQueue {


    @Test
    public void testUnorderedArrayMaxPQ() {
        UnorderedArrayMaxPQ<String> pq1 = new UnorderedArrayMaxPQ<>();
        assertTrue(pq1.isEmpty());
        assertEquals(0, pq1.size());

        pq1.insert("s");
        assertFalse(pq1.isEmpty());
        assertEquals(1, pq1.size());

        pq1.insert("h");
        pq1.insert("e");
        pq1.insert("l");
        pq1.insert("l");
        pq1.insert("x");
        pq1.insert("a");
        assertEquals(7, pq1.size());

        assertEquals("x", pq1.delMax());
        assertEquals(6, pq1.size());

        assertEquals("s", pq1.delMax());
        assertEquals(5, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(4, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(3, pq1.size());

        assertEquals("h", pq1.delMax());
        assertEquals(2, pq1.size());

        assertEquals("e", pq1.delMax());
        assertEquals(1, pq1.size());

        assertEquals("a", pq1.delMax());
        assertEquals(0, pq1.size());
        assertTrue(pq1.isEmpty());
    }


    //String::compareToIgnoreCase

    @Test
    public void testUnorderedArrayMaxPQComparator() {
        UnorderedArrayMaxPQComparator<String> pq1 = new UnorderedArrayMaxPQComparator<>(String::compareToIgnoreCase);
        assertTrue(pq1.isEmpty());
        assertEquals(0, pq1.size());

        pq1.insert("s");
        assertFalse(pq1.isEmpty());
        assertEquals(1, pq1.size());

        pq1.insert("h");
        pq1.insert("e");
        pq1.insert("l");
        pq1.insert("l");
        pq1.insert("x");
        pq1.insert("a");
        assertEquals(7, pq1.size());

        assertEquals("x", pq1.delMax());
        assertEquals(6, pq1.size());

        assertEquals("s", pq1.delMax());
        assertEquals(5, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(4, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(3, pq1.size());

        assertEquals("h", pq1.delMax());
        assertEquals(2, pq1.size());

        assertEquals("e", pq1.delMax());
        assertEquals(1, pq1.size());

        assertEquals("a", pq1.delMax());
        assertEquals(0, pq1.size());
        assertTrue(pq1.isEmpty());

    }

    @Test
    public void testOrderedArrayMaxPQ() {
        OrderedArrayMaxPQ<String> pq1 = new OrderedArrayMaxPQ<>();
        assertTrue(pq1.isEmpty());
        assertEquals(0, pq1.size());

        pq1.insert("s");
        assertFalse(pq1.isEmpty());
        assertEquals(1, pq1.size());

        pq1.insert("h");
        pq1.insert("e");
        pq1.insert("l");
        pq1.insert("l");
        pq1.insert("x");
        pq1.insert("a");
        assertEquals(7, pq1.size());

        assertEquals("x", pq1.delMax());
        assertEquals(6, pq1.size());

        assertEquals("s", pq1.delMax());
        assertEquals(5, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(4, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(3, pq1.size());

        assertEquals("h", pq1.delMax());
        assertEquals(2, pq1.size());

        assertEquals("e", pq1.delMax());
        assertEquals(1, pq1.size());

        assertEquals("a", pq1.delMax());
        assertEquals(0, pq1.size());
        assertTrue(pq1.isEmpty());
    }

    @Test
    public void testOrderedArrayMaxPQComparator() {
        OrderedArrayMaxPQComparator<String> pq1 = new OrderedArrayMaxPQComparator<>(String::compareToIgnoreCase);
        assertTrue(pq1.isEmpty());
        assertEquals(0, pq1.size());

        pq1.insert("s");
        assertFalse(pq1.isEmpty());
        assertEquals(1, pq1.size());

        pq1.insert("h");
        pq1.insert("e");
        pq1.insert("l");
        pq1.insert("l");
        pq1.insert("x");
        pq1.insert("a");
        assertEquals(7, pq1.size());

        assertEquals("x", pq1.delMax());
        assertEquals(6, pq1.size());

        assertEquals("s", pq1.delMax());
        assertEquals(5, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(4, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(3, pq1.size());

        assertEquals("h", pq1.delMax());
        assertEquals(2, pq1.size());

        assertEquals("e", pq1.delMax());
        assertEquals(1, pq1.size());

        assertEquals("a", pq1.delMax());
        assertEquals(0, pq1.size());
        assertTrue(pq1.isEmpty());

    }

    @Test
    public void testMaxPQ() {
        MaxPQ<String> pq1 = new MaxPQ<>();
        assertTrue(pq1.isEmpty());
        assertEquals(0, pq1.size());

        pq1.insert("s");
        assertFalse(pq1.isEmpty());
        assertEquals(1, pq1.size());

        pq1.insert("h");
        pq1.insert("e");
        pq1.insert("l");
        pq1.insert("l");
        pq1.insert("x");
        pq1.insert("a");
        assertEquals(7, pq1.size());

        for(String s : pq1){
            System.out.println(s);
        }

        assertEquals("x", pq1.delMax());
        assertEquals(6, pq1.size());

        assertEquals("s", pq1.delMax());
        assertEquals(5, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(4, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(3, pq1.size());

        assertEquals("h", pq1.delMax());
        assertEquals(2, pq1.size());

        assertEquals("e", pq1.delMax());
        assertEquals(1, pq1.size());

        assertEquals("a", pq1.delMax());
        assertEquals(0, pq1.size());
        assertTrue(pq1.isEmpty());
    }


    @Test
    public void testMaxPQComparator() {
        MaxPQ<String> pq1 = new MaxPQ<>(String::compareToIgnoreCase);
        assertTrue(pq1.isEmpty());
        assertEquals(0, pq1.size());

        pq1.insert("s");
        assertFalse(pq1.isEmpty());
        assertEquals(1, pq1.size());

        pq1.insert("h");
        pq1.insert("e");
        pq1.insert("l");
        pq1.insert("l");
        pq1.insert("x");
        pq1.insert("a");
        assertEquals(7, pq1.size());

        for(String s : pq1){
            System.out.println(s);
        }

        assertEquals("x", pq1.delMax());
        assertEquals(6, pq1.size());

        assertEquals("s", pq1.delMax());
        assertEquals(5, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(4, pq1.size());

        assertEquals("l", pq1.delMax());
        assertEquals(3, pq1.size());

        assertEquals("h", pq1.delMax());
        assertEquals(2, pq1.size());

        assertEquals("e", pq1.delMax());
        assertEquals(1, pq1.size());

        assertEquals("a", pq1.delMax());
        assertEquals(0, pq1.size());
        assertTrue(pq1.isEmpty());
    }

    @Test
    public void testMinPQ() {
        MinPQ<String> pq1 = new MinPQ<>();
        assertTrue(pq1.isEmpty());
        assertEquals(0, pq1.size());

        System.out.println("inserting s");
        pq1.insert("s");
        assertFalse(pq1.isEmpty());
        assertEquals(1, pq1.size());
        System.out.println("----");
        System.out.println("inserting h");
        pq1.insert("h");

        System.out.println("----");
        System.out.println("inserting e");
        pq1.insert("e");

        System.out.println("inserting l");
        System.out.println("----");
        pq1.insert("l");

        System.out.println("inserting l");
        System.out.println("----");
        pq1.insert("l");

        System.out.println("inserting x");
        System.out.println("----");
        pq1.insert("x");

        System.out.println("inserting a");
        System.out.println("----");
        pq1.insert("a");

        System.out.println("----");
        assertEquals(7, pq1.size());

        for(String s : pq1){
            System.out.println(s);
        }

        System.out.println("deleting");
        assertEquals("a", pq1.delMin());
        assertEquals(6, pq1.size());

        System.out.println("deleting");
        assertEquals("e", pq1.delMin());
        assertEquals(5, pq1.size());

        System.out.println("deleting");
        assertEquals("h", pq1.delMin());
        assertEquals(4, pq1.size());

        System.out.println("deleting");
        assertEquals("l", pq1.delMin());
        assertEquals(3, pq1.size());

        System.out.println("deleting");
        assertEquals("l", pq1.delMin());
        assertEquals(2, pq1.size());

        System.out.println("deleting");
        assertEquals("s", pq1.delMin());
        assertEquals(1, pq1.size());

        System.out.println("deleting");
        assertEquals("x", pq1.delMin());
        assertEquals(0, pq1.size());
        assertTrue(pq1.isEmpty());
    }

    @Test
    public void testMinPQComparator() {
        MinPQ<String> pq1 = new MinPQ<>(String::compareToIgnoreCase);
        assertTrue(pq1.isEmpty());
        assertEquals(0, pq1.size());

        System.out.println("inserting s");
        pq1.insert("s");
        assertFalse(pq1.isEmpty());
        assertEquals(1, pq1.size());
        System.out.println("----");
        System.out.println("inserting h");
        pq1.insert("h");

        System.out.println("----");
        System.out.println("inserting e");
        pq1.insert("e");

        System.out.println("inserting l");
        System.out.println("----");
        pq1.insert("l");

        System.out.println("inserting l");
        System.out.println("----");
        pq1.insert("l");

        System.out.println("inserting x");
        System.out.println("----");
        pq1.insert("x");

        System.out.println("inserting a");
        System.out.println("----");
        pq1.insert("a");

        System.out.println("----");
        assertEquals(7, pq1.size());

        for(String s : pq1){
            System.out.println(s);
        }

        System.out.println("deleting");
        assertEquals("a", pq1.delMin());
        assertEquals(6, pq1.size());

        System.out.println("deleting");
        assertEquals("e", pq1.delMin());
        assertEquals(5, pq1.size());

        System.out.println("deleting");
        assertEquals("h", pq1.delMin());
        assertEquals(4, pq1.size());

        System.out.println("deleting");
        assertEquals("l", pq1.delMin());
        assertEquals(3, pq1.size());

        System.out.println("deleting");
        assertEquals("l", pq1.delMin());
        assertEquals(2, pq1.size());

        System.out.println("deleting");
        assertEquals("s", pq1.delMin());
        assertEquals(1, pq1.size());

        System.out.println("deleting");
        assertEquals("x", pq1.delMin());
        assertEquals(0, pq1.size());
        assertTrue(pq1.isEmpty());
    }


}
