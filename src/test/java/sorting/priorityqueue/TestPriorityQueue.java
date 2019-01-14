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

}
