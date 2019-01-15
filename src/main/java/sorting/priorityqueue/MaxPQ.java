package sorting.priorityqueue;

/*
 * priority queue implementation using a heap
 * using the natural order
 * in a binary heap, the items are stored in an array satisfying the heap condition
 * heap condition: item in each node is larger than or equal to the children's items
 * we do not use the index 0 in the array representing the heap
 * hence: children of item in position i are found at 2i and 2i+1
 * parent of item in position i is found at i/2
 * */

//TODO: comparator version (maybe in same class?)
//TODO: implement iterable
//TODO: check other versions wrt previous todo's

import sorting.sort.Sort;

import java.util.NoSuchElementException;

public class MaxPQ<Item extends Comparable<Item>> implements PriorityQueue<Item> {
    private Item[] pq; //array (heao) of items
    private int numberOfItems; //number of items in pq

    //initialize empty pq
    public MaxPQ(){
        pq = (Item[]) new Comparable[2]; // initially array of size 2
        numberOfItems = 0; // initially no items in pq
    }

    @Override
    public boolean isEmpty(){ return numberOfItems == 0;}

    @Override
    public int size(){return numberOfItems;}

    // resize array to an array with capacity
    public void resize(int capacity){
        Item[] temp = (Item[]) new Comparable[capacity];
        for (int i = 0; i < numberOfItems; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    // add item to pq (at the end of the heap and restore heap condition using swim operation)
    // if there is no room left on pq, resize array (doubling it)
    // since we do not use index 0 we need to resize when pq.length is equal to numberOfItems + 1
    @Override
    public void insert(Item itemToAdd){
        if(numberOfItems + 1 == pq.length){
            resize(2*pq.length);
        }
        pq[++numberOfItems] = itemToAdd; // increment numberOfItems and add item to pq (we do not use 0!)
        swim(numberOfItems);
    }

    // delete max item from pq, this element is in index 1, swap with last element, remove it from heap and
    // restore heap condition using sink operation
    // resize array if necessary
    @Override
    public Item delMax(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        // max item is at root (index 1)
        Item max = pq[1];
        // swap with last item (index numberOfItems) and decrement numberOfItems
        Sort.swap(pq, 1, numberOfItems--);
        // avoid loitering
        pq[numberOfItems+1] = null;
        // restore heap condition
        sink(1);
        return max;

    }

    /*
    * helper function
    * if an item is added in position k in the heap it should be moved up in order to restore the heap condition
    * */
    private void swim(int k){
        // we only use indices >1
        // heap condition is violated if item in parent is strict less than item in k
        while(k>1 && Sort.isStrictLess(pq[k/2], pq[k])){
            // swap with parent
            Sort.swap(pq,k,k/2);
            k = k/2; // repeat with parent (k/2)
        }
    }

    /*helper function
    * if an item is added in position k in the heap it should be moved down in order to restore the heap condition
    * */
    private void sink(int k){
        // there are only numberOfItems indices used
        // there are only children (at least one) for index k if 2*k <= numberOfItems
        while(2*k <= numberOfItems){
            int maxChild = 2*k;
            // check if there is a second child
            // check which child is max item
            if(maxChild < numberOfItems && Sort.isStrictLess(pq[maxChild], pq[maxChild + 1])){
                maxChild ++;
            }
            // check if parent (k) is smaller than maxChild, if so replace and repeat process, else break
            if(Sort.isStrictLess(pq[k], pq[maxChild])){
                Sort.swap(pq, k, maxChild);
                k = maxChild;
            }
            else{
                break;
            }
        }
    }
}
