package sorting.priorityqueue;


/*
* priority queue implementation using an unordered array
* using the natural order
* */


import sorting.sort.Sort;

import java.util.NoSuchElementException;

// restrict generic type Item
public class UnorderedArrayMaxPQ<Item extends Comparable<Item>> implements PriorityQueue<Item>{
    private Item[] pq; //array of items
    private int numberOfItems; //number of items in pq

    //initialize empty pq
    public UnorderedArrayMaxPQ(){
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

    // add item to pq (at the end of the array)
    // if there is no room left on pq, resize array (doubling it)
    @Override
    public void insert(Item itemToAdd){
        if(numberOfItems == pq.length){
            resize(2*pq.length);
        }
        pq[numberOfItems] = itemToAdd; // add key to pq
        numberOfItems++; // increment number of keys
    }

    // delete max item from pq
    // resize array if necessary
    @Override
    public Item delMax(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        // find index max key
        int max = 0;
        for(int i = 1; i<numberOfItems; i++){
            if(Sort.isStrictLess(pq[max] , pq[i])){
                max = i;
            }
        }
        // swap max with last
        Sort.swap(pq, max, numberOfItems-1);
        return pq[--numberOfItems];

    }


}
