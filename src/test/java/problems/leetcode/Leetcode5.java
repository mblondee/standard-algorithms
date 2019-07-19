package problems.leetcode;

/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

        (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

        You are given a target value to search. If found in the array return true, otherwise return false.*/

import org.junit.Test;
import problems.leetcode.leetcode81_100.RemoveDuplicatesSortedListII_82;
import problems.leetcode.leetcode81_100.RemoveDuplicatesSortedList_83;
import problems.leetcode.leetcode81_100.SearchRotatedSortedArray_81;

import static org.junit.Assert.*;

public class Leetcode5 {

    @Test
    public void testSearchRotatedSortedArray(){
        assertTrue(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, 0));
        assertTrue(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, 1));
        assertTrue(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, 2));
        assertTrue(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, 5));
        assertTrue(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, 6));
        assertFalse(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, 3));
        assertFalse(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, -3));
        assertFalse(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, 4));
        assertFalse(SearchRotatedSortedArray_81.search(new int[]{2,5,6,0,0,1,2}, 7));

        assertFalse(SearchRotatedSortedArray_81.search(new int[]{2}, 3));
        assertTrue(SearchRotatedSortedArray_81.search(new int[]{2}, 2));

        assertTrue(SearchRotatedSortedArray_81.search(new int[]{1,1,3,1}, 3));
        assertTrue(SearchRotatedSortedArray_81.search(new int[]{3,1}, 1));
    }

    @Test
    public void testRemoveDuplicatesSortedList(){
        ListNode ex1 = new ListNode(1);
        ex1.next = new ListNode(1);
        ex1.next.next = new ListNode(1);
        ex1.next.next.next = new ListNode(2);
        ex1.next.next.next.next = new ListNode(2);
        ex1.next.next.next.next.next = new ListNode(2);
        ex1.next.next.next.next.next.next = new ListNode(3);

        ListNode res1 = RemoveDuplicatesSortedList_83.deleteDuplicates(ex1);
        assertEquals(1, res1.val);
        assertEquals(2, res1.next.val);
        assertEquals(3, res1.next.next.val);
        assertEquals(null, res1.next.next.next);
    }

    @Test
    public void testRemoveDuplicatesSortedListII(){
        ListNode ex1 = new ListNode(1);
        ex1.next = new ListNode(1);
        ex1.next.next = new ListNode(1);
        ex1.next.next.next = new ListNode(2);
        ex1.next.next.next.next = new ListNode(2);
        ex1.next.next.next.next.next = new ListNode(2);
        ex1.next.next.next.next.next.next = new ListNode(3);

        ListNode res1 = RemoveDuplicatesSortedListII_82.deleteDuplicates(ex1);
        assertEquals(3, res1.val);
        assertEquals(null, res1.next);


        ListNode ex2 = new ListNode(1);
        ex2.next = new ListNode(2);
        ex2.next.next = new ListNode(3);
        ListNode res2 = RemoveDuplicatesSortedListII_82.deleteDuplicates(ex2);
        assertEquals(1, res2.val);
        assertEquals(2, res2.next.val);
        assertEquals(3, res2.next.next.val);
        assertEquals(null, res2.next.next.next);
    }
}
