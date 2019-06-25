package problems.binarytree;

import org.junit.Test;

import static org.junit.Assert.*;


public class BinaryTreeTest {

    @Test
    public void test1(){
        BinaryTree bTree = new BinaryTree();
        assertTrue(bTree.isEmpty());
        bTree.insert(7);
        assertFalse(bTree.isEmpty());
        bTree.insert(2);
        bTree.insert(1);
        bTree.insert(10);
        bTree.insert(5);
        bTree.insert(11);
        assertTrue(bTree.getSize() == 6);
        bTree.insert(5);
        assertTrue(bTree.getSize() == 6);

        int[] nodes = new int[]{7,2,1,10,5,11};
        for(int node : nodes){
            assertTrue(bTree.contains(node));
        }

        int[] otherNodes = new int[]{3,6,9,12};
        for(int node : otherNodes){
            assertFalse(bTree.contains(node));
        }


    }

    @Test
    public void test2(){
        BinaryTree bTree = new BinaryTree();
        bTree.insert(7);
        bTree.insert(2);
        bTree.insert(1);
        bTree.insert(10);
        bTree.insert(5);
        bTree.insert(13);
        bTree.insert(5);

        int[] nodes = new int[]{7,2,1,10,5,13};
        for(int node : nodes){
            assertEquals((Integer) node, bTree.leq(node));
        }


        assertTrue(bTree.leq(0) == null);
        assertTrue(bTree.leq(-10) == null);

        assertEquals((Integer) 2, bTree.leq(3));
        assertEquals((Integer) 2, bTree.leq(4));
        assertEquals((Integer) 5, bTree.leq(6));
        assertEquals((Integer) 7, bTree.leq(8));
        assertEquals((Integer) 7, bTree.leq(9));
        assertEquals((Integer) 10, bTree.leq(11));
        assertEquals((Integer) 10, bTree.leq(12));
        assertEquals((Integer) 13, bTree.leq(14));


        bTree.printLevelByLevel();


    }

    @Test
    public void test3(){
        BinaryTree bTree = new BinaryTree();
        BinaryTree bTree2 = new BinaryTree();

        int[] treeNodes = new int[]{8,5,9};
        for(int i : treeNodes){
            bTree.insert(i);
        }
        assertFalse(bTree.hasDeadEnd());
        bTree.insert(2);
        assertFalse(bTree.hasDeadEnd());
        bTree.insert(7);
        assertFalse(bTree.hasDeadEnd());
        bTree.insert(1);
        assertTrue(bTree.hasDeadEnd());



        bTree2.insert(8);
        assertFalse(bTree2.hasDeadEnd());
        bTree2.insert(7);
        assertFalse(bTree2.hasDeadEnd());
        bTree2.insert(2);
        assertFalse(bTree2.hasDeadEnd());
        bTree2.insert(10);
        assertFalse(bTree2.hasDeadEnd());
        bTree2.insert(13);
        assertFalse(bTree2.hasDeadEnd());
        bTree2.insert(9);
        assertTrue(bTree2.hasDeadEnd());


    }

    @Test
    public void preorder(){
        int[] preorder = new int[]{10,5,1,7,40,50};
        BinaryTree bTree = new BinaryTree(preorder);
        assertTrue(bTree.getSize() == 6);
    }

    @Test
    public void invert(){
        BinaryTree bTree = new BinaryTree();
        bTree.insert(7);
        bTree.insert(2);
        bTree.insert(1);
        bTree.insert(10);
        bTree.insert(5);
        bTree.insert(13);
        bTree.invert();
        bTree.printLevelByLevel();

    }
}
