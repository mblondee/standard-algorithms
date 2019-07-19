package problems.leetcode.leetcode21_40;

/*
* Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
* */

/* approaches:
* n arrays of length k
* 1) concatenate + sort: O(n*k*log(n*k)), maybe naive since it removes information (sorted)
* 2) compare first elements: O(k*n*n) -> every time n compares, O(k*n) iterations
* also not all arrays have same length
*
* min heap: a complete binary tree in which each internal node is greater than or equal to the value of the children
* node index k: children 2k+1 and 2k+2
* node index k: parent (k-1)/2
*
* while(heap.getMin() != null) runs n*k times and the most expensive (not constant) computation is heapify which is
* O(height of tree) = O(log k)
* time complexity: O(n*k*(log k))
*
* also solution using a priority queue O(n log k)
* every pop and insert is (log k)
* */

import problems.leetcode.ListNode;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        int numberOfLists = lists.length;
        ListNode[] heapArray = new ListNode[numberOfLists];

        int size = 0;
        // put all heads in heapArray
        for(int i = 0; i < numberOfLists; i++){
            if(lists[i] == null){
                continue;
            }
            heapArray[size] = lists[i];
            size++;
        }
        MinHeap heap = new MinHeap(heapArray, size);
        ListNode result = new ListNode(0); // dummy node
        ListNode pointer = result;

        // get the smallest element in minheap and replace it with next element in its list
        while(heap.getMin() != null){
            pointer.next = new ListNode(heap.getMin().val);
            pointer = pointer.next;

            ListNode next = heap.getMin().next;
            if(next != null) {
                heap.replaceMin(next);
            }
            else{
                heap.replaceLastWithFirst();
            }

        }
        return result.next;
    }

    public static Comparator<ListNode> compareByVal = ((ListNode l1, ListNode l2) ->
            l1.val - l2.val);

    public static ListNode mergeKListsPQ(ListNode[] lists){
        ListNode result = new ListNode(0); // dummy node
        ListNode pointer = result;

        Queue<ListNode> pq = new PriorityQueue<>(compareByVal);
        for(ListNode node: lists){
            if(node != null){
                pq.add(node);
            }
        }

        while(! pq.isEmpty()){
            ListNode node = pq.remove();
            pointer.next = new ListNode(node.val);
            pointer = pointer.next;
            if(node.next != null) {
                pq.add(node.next);
            }
        }

        return result.next;
    }
}
