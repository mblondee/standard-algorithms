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

    @Test
    public void testOthers(){
        BinarySearchSymbolTable<String, Integer> ST1 = new BinarySearchSymbolTable<>();
        for(String s: ST1.keys()){
            System.out.println(s);
        }
        System.out.println("--------");
        ST1.put("a", 1);
        ST1.put("e", 4);
        for(String s: ST1.keys()){
            System.out.println(s);
        }
        System.out.println("--------");
        ST1.put("b", 2);
        ST1.put("k", 7);
        for(String s: ST1.keys()){
            System.out.println(s);
        }
        System.out.println("--------");
        ST1.put("a", 8);
        ST1.put("f", 14);

        ST1.put("a", 10);
        ST1.put("g", 7);
        ST1.put("a", 11);
        ST1.put("e", 1);
        for(String s: ST1.keys()){
            System.out.println(s);
        }
        System.out.println("--------");
        ST1.delete("a");
        ST1.delete("e");
        for(String s: ST1.keys()){
            System.out.println(s);
        }
    }

    @Test
    public void r(){
        Double d = 0.1;
        Double e = 0.10;
        System.out.println(d == e);
        System.out.println(d.equals(e));




    }
}
