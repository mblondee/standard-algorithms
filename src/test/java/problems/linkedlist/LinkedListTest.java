package problems.linkedlist;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void test1(){
        LinkedList lkst = new LinkedList();
        int[] toAdd = new int[]{1,5,7,2,9,10,11,14};
        for(int i : toAdd){
            lkst.addToEnd(i);
        }
        System.out.println(lkst);
    }

    @Test
    public void test2(){
        LinkedList lkst = new LinkedList();
        int[] toAdd = new int[]{1,5,7,2,9,10,11,14};
        for(int i : toAdd){
            lkst.addToFront(i);
        }
        System.out.println(lkst);
    }

    @Test
    public void testEquals(){
        LinkedList lkst = new LinkedList();
        int[] toAdd = new int[]{1,5,7,2,9,10,11,14};
        for(int i : toAdd){
            lkst.addToFront(i);
        }
        LinkedList lkst2 = new LinkedList();
        for(int i : toAdd){
            lkst2.addToFront(i);
        }

        LinkedList lkst3 = new LinkedList();
        int[] toAdd3 = new int[]{1,5,7,2,9,10,11};
        for(int i : toAdd3){
            lkst3.addToFront(i);
        }


        assertTrue(lkst.equals(lkst));
        assertTrue(lkst.equals(lkst2));
        assertTrue(lkst2.equals(lkst));
        assertFalse(lkst.equals(lkst3));
        assertFalse(lkst3.equals(lkst2));
        lkst3.addToFront(14);
        assertTrue(lkst3.equals(lkst2));
        assertTrue(lkst.equals(lkst3));
    }

    @Test
    public void testReverse(){
        LinkedList lkst = new LinkedList();
        int[] toAdd = new int[]{1,5,7,2,9,10,11,14};
        for(int i : toAdd){
            lkst.addToFront(i);
        }

        LinkedList lkst1 = new LinkedList();
        for(int i : toAdd){
            lkst1.addToEnd(i);
        }
        LinkedList lkst1copy = new LinkedList();
        for(int i : toAdd){
            lkst1copy.addToEnd(i);
        }

        lkst.reverse();
        assertEquals(lkst1, lkst);

        lkst1.reverse();
        lkst1.reverse();
        assertEquals(lkst1copy, lkst1);

        LinkedList emptyList = new LinkedList();
        LinkedList emptyListCopy = new LinkedList();
        emptyList.reverse();
        assertEquals(emptyListCopy, emptyList);

        LinkedList oneElList = new LinkedList();
        oneElList.addToFront(1);
        LinkedList oneElListCopy = new LinkedList();
        oneElListCopy.addToFront(1);

        oneElList.reverse();
        assertEquals(oneElListCopy, oneElList);



    }

    @Test
    public void testInterLeave(){
        LinkedList lkst = new LinkedList();
        int[] toAdd = new int[]{1,5,7,2};
        for(int i : toAdd){
            lkst.addToFront(i);
        }
        LinkedList lkst2 = new LinkedList();
        int[] toAdd2 = new int[]{10,50,70,20};
        for(int i : toAdd2){
            lkst2.addToFront(i);
        }

        LinkedList lkst3 = new LinkedList();
        int[] toAdd3 = new int[]{10,1,50,5,70,7,20,2};
        for(int i : toAdd3){
            lkst3.addToFront(i);
        }

        lkst.interleave(lkst2);
        assertTrue(lkst.equals(lkst3));

        LinkedList lst = new LinkedList();
        LinkedList lst1 = new LinkedList();
        LinkedList lst2 = new LinkedList();
        lst.interleave(lst1);
        assertEquals(lst2, lst);
    }

    @Test
    public void toLastTest(){
        LinkedList lkst3 = new LinkedList();
        int[] toAdd3 = new int[]{10,1,50,5,70,7,20,2};
        for(int i : toAdd3){
            lkst3.addToEnd(i);
        }

        for(int i = 1; i <= toAdd3.length; i++){
            assertEquals(toAdd3[toAdd3.length-i], lkst3.toLast(i-1));
        }



    }

    @Test
    public void mergeSortTest(){

        LinkedList lkst6 = new LinkedList();
        int[] toAdd6 = new int[]{10,1,50,5, 70,7,20,2};
        for(int i : toAdd6){
            lkst6.addToEnd(i);
        }

        LinkedList lkst6sort = new LinkedList();
        int[] toAdd6sort = new int[]{1,2,5,7,10,20,50,70};
        for(int i : toAdd6sort){
            lkst6sort.addToEnd(i);
        }

        LinkedList empty = new LinkedList();
        LinkedList emptyCopy = new LinkedList();

        lkst6.mergeSort();
        assertEquals(lkst6sort, lkst6);

        empty.mergeSort();
        assertEquals(emptyCopy, empty);

        empty.addToFront(1);
        emptyCopy.addToFront(1);

        empty.mergeSort();
        assertEquals(emptyCopy, empty);

    }

    @Test
    public void quickSortTest() {
        LinkedList lkst6 = new LinkedList();
        int[] toAdd6 = new int[]{10,1,50, 5, 70,7,20,2};
        for (int i : toAdd6) {
            lkst6.addToEnd(i);
        }

        LinkedList lkst6sort = new LinkedList();
        int[] toAdd6sort = new int[]{1, 2, 5, 7, 10, 20, 50, 70};
        for (int i : toAdd6sort) {
            lkst6sort.addToEnd(i);
        }


        LinkedList lkst5 = new LinkedList();
        int[] toAdd5 = new int[]{10,1};
        for (int i : toAdd5) {
            lkst5.addToEnd(i);
        }

        LinkedList lkst5a = new LinkedList();
        int[] toAdd5a = new int[]{1,10};
        for (int i : toAdd5a) {
            lkst5a.addToEnd(i);
        }

        LinkedList lkst5sort = new LinkedList();
        int[] toAdd5sort = new int[]{1, 10};
        for (int i : toAdd5sort) {
            lkst5sort.addToEnd(i);
        }

        LinkedList empty = new LinkedList();
        LinkedList emptysort = new LinkedList();

        lkst6.quicksort();
        assertEquals(lkst6sort,  lkst6);

        lkst5.quicksort();
        assertEquals(lkst5sort,  lkst5);


        lkst5a.quicksort();
        assertEquals(lkst5sort,  lkst5a);

        empty.quicksort();
        assertEquals(emptysort,  empty);
    }
}
