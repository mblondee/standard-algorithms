package search;

import org.junit.Test;


import static org.junit.Assert.*;

public class LinearProbingHashSymbolTableTest {

    @Test
    public void test1(){
        LinearProbingHashSymbolTable<String, Integer> HST = new LinearProbingHashSymbolTable<>();
        assertTrue(HST.isEmpty());
        assertEquals(0, HST.getSize());

        HST.put("a", 1);
        assertEquals(1, HST.getSize());
        assertEquals(new Integer(1), HST.get("a"));
        HST.put("b", 2);
        assertEquals(new Integer(2), HST.get("b"));

        HST.put("a", 10);
        assertEquals(2, HST.getSize());
        assertEquals(new Integer(10), HST.get("a"));

        assertTrue(HST.contains("a"));
        assertFalse(HST.contains("d"));

        HST.delete("a");
        assertEquals(1, HST.getSize());
        HST.delete("b");
        assertEquals(0, HST.getSize());

    }

    @Test
    public void testResizing(){
        LinearProbingHashSymbolTable<String, Integer> HST = new LinearProbingHashSymbolTable<>();
        HST.put("a", 1);
        assertEquals(new Integer(1), HST.get("a"));
        HST.put("b", 11);
        assertEquals(new Integer(11), HST.get("b"));
        HST.put("c", 3);
        assertEquals(new Integer(3), HST.get("c"));
        HST.put("d", 5);
        assertEquals(new Integer(5), HST.get("d"));
        HST.put("e", 1);
        assertEquals(new Integer(1), HST.get("e"));
        HST.put("f", 2);
        assertEquals(new Integer(2), HST.get("f"));
        HST.put("g", 4);
        assertEquals(new Integer(4), HST.get("g"));
        HST.put("h", 9);
        assertEquals(new Integer(9), HST.get("h"));
        HST.put("i", 12);
        assertEquals(new Integer(12), HST.get("i"));
        HST.put("j", 8);
        assertEquals(new Integer(8), HST.get("j"));
        HST.put("k", 80);
        assertEquals(new Integer(8), HST.get("j"));
        HST.put("l", 81);


        assertEquals(new Integer(1), HST.get("a"));
        assertEquals(new Integer(11), HST.get("b"));
        assertEquals(new Integer(3), HST.get("c"));
        assertEquals(new Integer(5), HST.get("d"));
        assertEquals(new Integer(1), HST.get("e"));
        assertEquals(new Integer(2), HST.get("f"));
        assertEquals(new Integer(4), HST.get("g"));
        assertEquals(new Integer(9), HST.get("h"));
        assertEquals(new Integer(12), HST.get("i"));
        assertEquals(new Integer(8), HST.get("j"));
        assertEquals(new Integer(80), HST.get("k"));
        assertEquals(new Integer(81), HST.get("l"));

        HST.delete("a");
        HST.delete("j");

        assertEquals(new Integer(11), HST.get("b"));
        assertEquals(new Integer(3), HST.get("c"));
        assertEquals(new Integer(5), HST.get("d"));
        assertEquals(new Integer(1), HST.get("e"));
        assertEquals(new Integer(2), HST.get("f"));
        assertEquals(new Integer(4), HST.get("g"));
        assertEquals(new Integer(9), HST.get("h"));
        assertEquals(new Integer(12), HST.get("i"));

        HST.delete("d");
        HST.delete("e");
        HST.delete("g");
        HST.delete("h");



        assertEquals(new Integer(11), HST.get("b"));
        assertEquals(new Integer(3), HST.get("c"));
        assertEquals(new Integer(2), HST.get("f"));
        assertEquals(new Integer(12), HST.get("i"));


        HST.delete("i");
        HST.delete("f");

        assertEquals(new Integer(11), HST.get("b"));
        assertEquals(new Integer(3), HST.get("c"));
        assertEquals(new Integer(80), HST.get("k"));
        assertEquals(new Integer(81), HST.get("l"));

        HST.delete("k");
        HST.delete("l");

        assertEquals(new Integer(11), HST.get("b"));
        assertEquals(new Integer(3), HST.get("c"));

    }

}
