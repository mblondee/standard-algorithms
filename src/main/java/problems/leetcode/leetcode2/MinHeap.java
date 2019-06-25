package problems.leetcode.leetcode2;

/*utility class for merge k sorted lists
min heap: complete binary tree, minimum is at root
children <= parent
* */

public class MinHeap {

    private MinHeapNode[] heapArray;
    private int heapSize; // current number of elements in min heap

    // build min heap from array of nodes
    public MinHeap(MinHeapNode[] input){
        heapSize = input.length;
        heapArray = input;
        // last half are all trees of height one: satisfy the heap condition
        // first one that is not on the last row: parent of last element
        int first = (heapSize - 2)/2;
        while(first >= 0){
            heapify(first);
            first--;
        }
    }

    private static int getIndexParent(int position){
        return (position-1)/2;
    }

    private static int getIndexLeftChild(int position){
        return 2*position + 1;
    }

    private static int getIndexRightChild(int position){
        return 2*position + 2;
    }

    // return root (min element)
    public MinHeapNode getMin(){
        if(heapSize <= 0){
            return null;
        }
        return heapArray[0];
    }

    // replace root with newNode
    public void replaceMin(MinHeapNode newNode){
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
        if( leftChild < heapSize && heapArray[leftChild].value < heapArray[index].value){
            smallest = leftChild;
        }
        // compare smallest to rightChild (if rightChild exists!)
        if(rightChild < heapSize && heapArray[rightChild].value < heapArray[smallest].value){
            smallest = rightChild;
        }
        if(smallest != index){
            // replace index with smallest and do heapify on subtree
            MinHeapNode temp = heapArray[index];
            heapArray[index] = heapArray[smallest];
            heapArray[smallest] = temp;
            heapify(smallest);
        }
    }

    public MinHeapNode[] getHeap(){
        return heapArray;
    }

    // remove first and replace it with last
    public void replaceLastWithFirst(){
        heapArray[0] = heapArray[heapSize -1];
        heapArray[heapSize-1] = null;
        heapSize--;
        heapify(0);
    }

}
