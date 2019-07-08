package problems.leetcode.leetcode2;

/*utility class for merge k sorted lists
min heap: complete binary tree, minimum is at root
children <= parent
* */

import problems.leetcode.ListNode;

public class MinHeap {

    private ListNode[] heapArray;
    private int heapSize; // current number of elements in min heap

    // build min heap from array of nodes
    public MinHeap(ListNode[] input, int heapSize){
        this.heapSize = heapSize;
        heapArray = input;
        // last half are all trees of height one: satisfy the heap condition
        // first one that is not on the last row: parent of last element
        int first = (heapSize - 2)/2;
        while(first >= 0){
            heapify(first);
            first--;
        }
    }

    private static int getIndexLeftChild(int position){
        return 2*position + 1;
    }

    private static int getIndexRightChild(int position){
        return 2*position + 2;
    }

    // return root (min element)
    public ListNode getMin(){
        if(heapSize <= 0){
            return null;
        }
        return heapArray[0];
    }

    // replace root with newNode
    public void replaceMin(ListNode newNode){
        heapArray[0] = newNode;
        heapify(0);
    }

    // if subtrees in subtree rooted at index satisfy the heap condition (children <= parent)
    // restore heapcondition in subtree rooted at index
    private void heapify(int index){
        // first find smallest between index, leftChild and rightChild
        int leftChild = getIndexLeftChild(index);
        int rightChild = getIndexRightChild(index);
        int smallest = index;
        // compare index to leftChild (if leftChild exists!)
        if( leftChild < heapSize && heapArray[leftChild].val < heapArray[index].val){
            smallest = leftChild;
        }
        // compare smallest to rightChild (if rightChild exists!)
        if(rightChild < heapSize && heapArray[rightChild].val < heapArray[smallest].val){
            smallest = rightChild;
        }
        if(smallest != index){
            // replace index with smallest and do heapify on subtree
            ListNode temp = heapArray[index];
            heapArray[index] = heapArray[smallest];
            heapArray[smallest] = temp;
            heapify(smallest);
        }
    }

    public ListNode[] getHeap(){
        return heapArray;
    }

    // remove first and replace it with last
    public void replaceLastWithFirst(){
        heapArray[0] = heapArray[heapSize -1];
        heapArray[heapSize-1] = null;
        heapSize--;
        if(heapSize == 0){
            return;
        }
        heapify(0);
    }

}
