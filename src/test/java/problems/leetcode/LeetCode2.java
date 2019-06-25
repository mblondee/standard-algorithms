package problems.leetcode;

import com.sun.deploy.util.ParameterUtil;
import org.junit.Test;
import problems.leetcode.leetcode2.*;

import static org.junit.Assert.assertEquals;

public class LeetCode2 {

    @Test
    public void testMergeSortedLists(){
        ListNode lst1 = new ListNode(1);
        lst1.next = new ListNode(2);
        lst1.next.next = new ListNode(4);

        ListNode lst2 = new ListNode(1);
        lst2.next = new ListNode(3);
        lst2.next.next = new ListNode(4);

        ListNode lst = MergeSortedLists.mergeTwoLists(lst1, lst2);
        ListNode pointer = lst;
        assertEquals(1, pointer.val);
        pointer = pointer.next;
        assertEquals(1, pointer.val);
        pointer = pointer.next;
        assertEquals(2, pointer.val);
        pointer = pointer.next;
        assertEquals(3, pointer.val);
        pointer = pointer.next;
        assertEquals(4, pointer.val);
        pointer = pointer.next;
        assertEquals(4, pointer.val);

        ListNode lst3 = new ListNode(3);
        ListNode lst4 = new ListNode(1);
        lst4.next = new ListNode(2);
        ListNode lstM = MergeSortedLists.mergeTwoLists(lst3, lst4);
        assertEquals(1,lstM.val);
        assertEquals(2, lstM.next.val);
        assertEquals(3, lstM.next.next.val);

    }

    @Test
    public void testGenerateParentheses(){
        assertEquals(2, GenerateParentheses.generateParenthesis(2).size());
        assertEquals(5, GenerateParentheses.generateParenthesis(3).size());
    }

/*    @Test
    public void testHeap(){
        MinHeapNode node1 = new MinHeapNode(41,0,0);
        MinHeapNode node2 = new MinHeapNode(51,0,0);
        MinHeapNode node3 = new MinHeapNode(16,0,0);
        MinHeapNode node4 = new MinHeapNode(31,0,0);
        MinHeapNode node5 = new MinHeapNode(13,0,0);
        MinHeapNode node6 = new MinHeapNode(41,0,0);
        MinHeapNode node7 = new MinHeapNode(100,0,0);

        MinHeap heap = new MinHeap(new MinHeapNode[]{node1, node2, node3, node4, node5, node6, node7});

        int[] res = {13,31,16,41,51,41,100};
        for(int i = 0; i < res.length; i++){
            assertEquals(res[i], heap.getHeap()[i].value);
        }*/


    //}

    @Test
    public void testMergeKSort(){
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        ListNode[] input = new ListNode[]{node1, node2, node3};
        ListNode output = MergeKSortedLists.mergeKLists(input);

        int[] result = {1,1,2,3,4,4,5,6};
        ListNode pointer = output;
        for(int i = 0; i < result.length; i++){
            assertEquals(result[i], pointer.val);
            pointer = pointer.next;
        }

    }
}
