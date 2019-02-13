package search;

import org.junit.Test;


import static org.junit.Assert.*;

public class SeparateChainingTest {

    @Test
    public void test1(){
        SeparateChainingHashSymbolTable<String, Integer> HST = new SeparateChainingHashSymbolTable<>(97);
        assertTrue(HST.isEmpty());
        assertEquals(0, HST.getSize());

        HST.put("a", 1);
        assertEquals(1, HST.getSize());
        HST.put("b", 3);
        assertEquals(2, HST.getSize());
        HST.put("c", 5);
        assertEquals(3, HST.getSize());
        HST.put("a", 10);
        assertEquals(3, HST.getSize());
    }

    @Test
    public void testLinked(){
        LinkedList<String, Integer> linked = new LinkedList<>();
        linked.put("a", 1);
        assertEquals(new Integer(1), linked.get("a"));
        linked.put("b", 3);
        assertEquals(new Integer(3), linked.get("b"));
        linked.put("c", 5);
        assertEquals(new Integer(5), linked.get("c"));
        assertEquals(3, linked.getSize());
        linked.put("a", 10);
        assertEquals(new Integer(10), linked.get("a"));
        assertEquals(3, linked.getSize());
        assertTrue(linked.contains("a"));
        assertFalse(linked.contains("d"));
        linked.delete("a");
        assertFalse(linked.contains("a"));
        assertEquals(2, linked.getSize());
        linked.delete("d");
        assertEquals(2, linked.getSize());

    }
}
