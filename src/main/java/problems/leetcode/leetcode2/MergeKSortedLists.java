package problems.leetcode.leetcode2;

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
* */

import problems.leetcode.ListNode;

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        int numberOfLists = lists.length;
        MinHeapNode[] heapArray = new MinHeapNode[numberOfLists];

        // put all heads in heapArray (assume all lists are not empty)
        for(int i = 0; i < numberOfLists; i++){
            heapArray[i] = new MinHeapNode(lists[i].val, lists[i]);
        }
        MinHeap heap = new MinHeap(heapArray);
        ListNode result = new ListNode(0); // dummy node
        ListNode pointer = result;

        // get the smallest element in minheap and replace it with next element in its list
        while(heap.getMin() != null){
            pointer.next = new ListNode(heap.getMin().value);
            pointer = pointer.next;

            ListNode next = heap.getMin().node.next;
            if(next != null) {
                heap.replaceMin(new MinHeapNode(next.val, next));
            }
            else{
                heap.replaceLastWithFirst();
            }

        }


        return result.next;

    }
}
