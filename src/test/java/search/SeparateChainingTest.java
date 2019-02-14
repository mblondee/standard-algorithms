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
        assertEquals(new Integer(1), HST.get("a"));


        HST.put("b", 3);
        assertEquals(2, HST.getSize());
        assertEquals(new Integer(3), HST.get("b"));
        HST.put("c", 5);
        assertEquals(3, HST.getSize());
        assertEquals(new Integer(5), HST.get("c"));
        assertFalse(HST.contains("d"));
        assertTrue(HST.contains("a"));
        HST.put("a", 10);
        assertEquals(3, HST.getSize());
        assertEquals(new Integer(10), HST.get("a"));

        HST.delete("a");
        assertEquals(2, HST.getSize());
        assertFalse(HST.contains("a"));

        HST.delete("b");
        assertEquals(1, HST.getSize());
        assertFalse(HST.contains("b"));

        HST.delete("c");
        assertEquals(0, HST.getSize());
        assertFalse(HST.contains("c"));


    }

    @Test
    public void resizing(){
        SeparateChainingHashSymbolTable<String, Integer> HST = new SeparateChainingHashSymbolTable<>(2);
        HST.put("a", 1);
        HST.put("aa", 1);
        HST.put("aaa", 1);
        HST.put("aaaa", 1);
        HST.put("ab", 1);
        HST.put("abb", 1);
        HST.put("abbb", 1);
        HST.put("ac", 1);
        HST.put("acc", 1);
        HST.put("accc", 1);
        HST.put("ad", 1);
        HST.put("add", 1);
        HST.put("addd", 1);
        HST.put("ae", 1);
        HST.put("aee", 1);
        HST.put("aeee", 1);
        HST.put("b", 1);
        HST.put("ba", 1);
        HST.put("baa", 1);
        HST.put("baaa", 1);
        HST.put("bb", 1);
        HST.put("bbb", 1);
        HST.put("bbbb", 1);
        HST.put("bc", 1);
        HST.put("bcc", 1);
        HST.put("bccc", 1);
        HST.put("bd", 1);
        HST.put("bdd", 1);
        HST.put("bddd", 1);
        HST.put("be", 1);
        HST.put("bee", 1);
        HST.put("beee", 10);
        assertEquals(32, HST.getSize());
        assertEquals(new Integer(10), HST.get("beee"));

        HST.delete("a");
        HST.delete("aa");
        HST.delete("aaa");
        HST.delete("aaaa");
        HST.delete("ab");
        HST.delete("abb");
        HST.delete("abbb");
        HST.delete("ac");
        HST.delete("acc");
        HST.delete("accc");
        HST.delete("ad");
        HST.delete("add");
        HST.delete("addd");
        HST.delete("ae");

        assertEquals(18, HST.getSize());
        assertEquals(new Integer(10), HST.get("beee"));

        HST.delete("aee");
        HST.delete("aeee");
        HST.delete("b");
        HST.delete("ba");
        HST.delete("baa");
        HST.delete("baaa");
        HST.delete("bb");
        HST.delete("bbb");

        assertEquals(10, HST.getSize());
        assertEquals(new Integer(10), HST.get("beee"));


        HST.delete("bbbb");
        HST.delete("bc");
        HST.delete("bcc");
        HST.delete("bccc");
        HST.delete("bd");
        HST.delete("bdd");
        HST.delete("bddd");

        assertEquals(3, HST.getSize());
        assertEquals(new Integer(10), HST.get("beee"));

        HST.delete("be");
        HST.delete("bee");
        HST.delete("beee");

        assertTrue(HST.isEmpty());
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

    @Test
    public void testLinkedIter(){
        LinkedList<String, Integer> linked = new LinkedList<>();
        System.out.println("no el ");
        for(String s: linked.keys()){
            System.out.println(s);
        }
        System.out.println("------");
        linked.put("a", 1);
        System.out.println("1 el ");
        for(String s: linked.keys()){
            System.out.println(s);
        }
        System.out.println("------");

        linked.put("b", 3);
        System.out.println("2 ");
        for(String s: linked.keys()){
            System.out.println(s);
        }
        System.out.println("------");

        linked.put("c", 5);
        System.out.println("3 el ");
        for(String s: linked.keys()){
            System.out.println(s);
        }
        System.out.println("------");




    }
}
