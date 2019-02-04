package search;

import org.junit.Test;


import static org.junit.Assert.*;


public class RedBlackBinarySearchTreeTest {
    @Test
    public void test(){
        RedBlackBinarySearchTree<String, Integer> BST = new RedBlackBinarySearchTree<>();
        assertTrue(BST.isEmpty());
        assertEquals(0, BST.getSize());
        BST.put("a", 1);
        assertEquals(1, BST.getSize());
        assertEquals(new Integer(1), BST.get("a"));
        BST.put("a", 1);
        assertEquals(1, BST.getSize());
        assertEquals(new Integer(1), BST.get("a"));
        BST.put("c", 5);
        assertEquals(new Integer(5), BST.get("c"));
        assertEquals(2, BST.getSize());
        BST.put("b", 15);
        assertEquals(new Integer(15), BST.get("b"));
        BST.put("z", 1);
        assertEquals(new Integer(1), BST.get("z"));
        BST.put("c", 1);
        assertEquals(new Integer(1), BST.get("c"));
        assertEquals(4, BST.getSize());

        assertTrue(BST.contains("a"));
        assertFalse(BST.contains("x"));
    }
}
