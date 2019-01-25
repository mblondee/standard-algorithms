package search;

import org.junit.Test;


import static org.junit.Assert.*;

public class BinarySearchSymbolTableTest {

    @Test
    public void testST(){
        BinarySearchSymbolTable<String, Integer> ST1 = new BinarySearchSymbolTable<>();
        assertTrue(ST1.isEmpty());
        ST1.put("a", 1);
        assertFalse(ST1.isEmpty());
        assertEquals(1, ST1.getSize());


        assertTrue(ST1.contains("a"));
        assertFalse(ST1.contains("b"));

        ST1.put("b", 1);
        assertEquals(2, ST1.getSize());
        assertTrue(ST1.contains("a"));
        assertTrue(ST1.contains("b"));


        assertEquals(1, (int) ST1.get("a"));
        assertEquals(1, (int) ST1.get("b"));
        assertNull(ST1.get("c"));

        ST1.put("a", 2);
        assertEquals(2, ST1.getSize());
        assertTrue(ST1.contains("a"));
        assertTrue(ST1.contains("b"));
        assertEquals(2, (int) ST1.get("a"));

        ST1.delete("a");
        assertFalse(ST1.contains("a"));
        assertTrue(ST1.contains("b"));

        ST1.delete("c");
        assertFalse(ST1.contains("a"));
        assertTrue(ST1.contains("b"));
        assertFalse(ST1.contains("c"));

        ST1.delete("b");
        assertTrue(ST1.isEmpty());



    }
}
