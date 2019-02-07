package sorting.priorityqueue;

/*
 * priority queue implementation using a heap
 * using the natural order or a comparator
 * in a binary heap, the items are stored in an array satisfying the heap condition
 * heap condition: item in each node is smaller than or equal to the children's items
 * we do not use the index 0 in the array representing the heap
 * hence: children of item in position i are found at 2i and 2i+1
 * parent of item in position i is found at i/2
 *
 * n items -> insert() <= 1 + log_2 N compares
 *         -> delMin() <= 2 log_2 N compares
 * */

//TODO: check other versions comparator and comparable in same class

import sorting.sort.Sort;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Item> implements Iterable<Item> {
    private Item[] pq; //array (heap) of items
    private int numberOfItems; //number of items in pq
    private Comparator<Item> comparatorItem; // comparator used to order items

    //initialize empty pq using natural order
    public MinPQ(){
        pq = (Item[]) new Object[2]; // initially array of size 2
        numberOfItems = 0; // initially no items in pq
    }

    //initialize empty pq using a comparator
    public MinPQ(Comparator<Item> comparator){
        pq = (Item[]) new Object[2]; // initially array of size 2
        numberOfItems = 0; // initially no items in pq
        comparatorItem = comparator;
    }


    public boolean isEmpty(){ return numberOfItems == 0;}

    public int size(){return numberOfItems;}

    // resize array to an array with capacity
    private void resize(int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        //used indices in pq are 1 to numberOfItems
        for (int i = 1; i <= numberOfItems; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    // add item to pq (at the end of the heap and restore heap condition using swim operation)
    // if there is no room left on pq, resize array (doubling it)
    // since we do not use index 0 we need to resize when pq.length is equal to numberOfItems + 1
    public void insert(Item itemToAdd){
        if(numberOfItems + 1 == pq.length){
            resize(2*pq.length);
        }
        pq[++numberOfItems] = itemToAdd; // increment numberOfItems and add item to pq (we do not use 0!)
        swim(numberOfItems);
    }

    // delete min item from pq, this element is in index 1, swap with last element, remove it from heap and
    // restore heap condition using sink operation
    // resize array if necessary
    public Item delMin(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        // max item is at root (index 1)
        Item min = pq[1];
        // swap with last item (index numberOfItems) and decrement numberOfItems
        Sort.swap(pq, 1, numberOfItems);
        // avoid loitering
        pq[numberOfItems] = null;
        numberOfItems--;
        // restore heap condition
        sink(1);
        // resize (we need one more element than numberOfItems)
        if(numberOfItems > 0 && numberOfItems + 1 == pq.length/4 ) {
            resize(pq.length/2);
        }
        return min;

    }

    /*
     * helper function
     * if an item is added in position k in the heap it should be moved up in order to restore the heap condition
     * */
    private void swim(int k){
        // we only use indices >1
        // heap condition is violated if item in parent is strictly greater than item in k
        while(k>1 && isStrictLarger(k/2, k)){
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
            int minChild = 2*k;
            // check if there is a second child
            // check which child is min item
            if(minChild < numberOfItems && isStrictLarger(minChild, minChild + 1 )){
                minChild ++;
            }
            // check if parent (k) is larger than minChild, if so replace and repeat process, else break
            if(isStrictLarger(k, minChild)){
                Sort.swap(pq, k, minChild);
                k = minChild;
            }
            else{
                break;
            }
        }
    }

    /*
     * helper function
     * is pq[i] strict larger than pq[j]?
     * */

    private boolean isStrictLarger(int i, int j){
        if(comparatorItem == null){
            // use natural order
            return Sort.isStrictLarger((Comparable) pq[i], (Comparable) pq[j]);
        }
        else{
            // use comparator
            return Sort.isStrictLarger(pq[i], pq[j], comparatorItem);
        }
    }

    // returns an iterator that iterates through the items in ascending order
    public Iterator<Item> iterator(){
        return new HeapIterator();
    }

    // an iterator
    private class HeapIterator implements Iterator<Item>{
        private MinPQ<Item> copy; // copy of pq

        public HeapIterator() {
            if (comparatorItem == null){
                // use natural order
                copy = new MinPQ<>();
            }
            else{
                // use comparatorItem
                copy = new MinPQ<>(comparatorItem);
            }
            // copy elements of pq into copy
            for (int i = 1; i <= numberOfItems; i++){
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext(){
            return !copy.isEmpty();
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }
}