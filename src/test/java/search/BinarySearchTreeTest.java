package search;

import org.junit.Test;


import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void test(){
        BinarySearchTree<String, Integer> BST = new BinarySearchTree<>();
        assertTrue(BST.isEmpty());
        assertEquals(0, BST.getSize());
        BST.put("a", 1);
        assertEquals(new Integer(1), BST.get("a"));
        BST.put("c", 5);
        assertEquals(new Integer(5), BST.get("c"));
        BST.put("b", 15);
        assertEquals(new Integer(15), BST.get("b"));
        BST.put("z", 1);
        assertEquals(new Integer(1), BST.get("z"));
        BST.put("c", 1);
        assertEquals(new Integer(1), BST.get("c"));
        assertEquals(4, BST.getSize());
        BST.delete("s");
        assertEquals(4, BST.getSize());
        BST.delete("z");
        assertEquals(3, BST.getSize());
        BST.delete("b");
        assertEquals(2, BST.getSize());
        BST.delete("c");
        assertEquals(1, BST.getSize());
        BST.delete("a");
        assertEquals(0, BST.getSize());
    }

    @Test
    public void deleteMinMaxTest(){
        BinarySearchTree<String, Integer> BST = new BinarySearchTree<>();
        BST.put("a", 1);
        BST.put("c", 5);
        BST.put("b", 15);
        BST.put("z", 1);
        BST.put("c", 1);
        BST.deleteMin();
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
        BST.deleteMax();
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
        BST.deleteMax();
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
        BST.deleteMax();
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
    }

    @Test
    public void testIterate(){
        BinarySearchTree<String, Integer> BST = new BinarySearchTree<>();
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
        BST.put("a", 1);
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
        BST.put("c", 5);
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
        BST.put("b", 5);
        BST.put("c", 7);
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
        BST.delete("b");
        for(String s : BST.keys()){
            System.out.println(s);
        }
        System.out.println("-----");
    }

    @Test
    public void testRank(){
        BinarySearchTree<Integer, String> BST = new BinarySearchTree<>();
        BST.put(1, "a");
        assertEquals(new Integer(1), (Integer) BST.rank(2) );
        assertEquals(new Integer(0), (Integer) BST.rank(1) );
        BST.put(5, "a");
        assertEquals(new Integer(1), (Integer) BST.rank(2) );
        assertEquals(new Integer(1), (Integer) BST.rank(5) );
        assertEquals(new Integer(2), (Integer) BST.rank(6) );
        BST.put(5, "b");
        assertEquals(new Integer(1), (Integer) BST.rank(2) );
        assertEquals(new Integer(1), (Integer) BST.rank(5) );
        assertEquals(new Integer(2), (Integer) BST.rank(6) );
        assertEquals(new Integer(2), (Integer) BST.sizeRange(0,6));
        assertEquals(new Integer(2), (Integer) BST.sizeRange(1,5));
        assertEquals(new Integer(0), (Integer) BST.sizeRange(3,4));
    }
}
