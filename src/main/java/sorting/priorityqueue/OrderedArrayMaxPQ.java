package sorting.priorityqueue;

/*
 * priority queue implementation using an ordered array
 * using the natural order
 * */


import sorting.sort.Sort;

import java.util.NoSuchElementException;

// restrict Item, has to implement Comparable<Item>
public class OrderedArrayMaxPQ<Item extends Comparable<Item>> implements PriorityQueue<Item> {
    private Item[] pq; //array of items
    private int numberOfItems; //number of items in pq

    //initialize empty pq array
    public OrderedArrayMaxPQ(){
        pq = (Item[]) new Comparable[2]; // initially array of size 2
        numberOfItems = 0; // initially no items in pq
    }

    @Override
    public boolean isEmpty(){ return numberOfItems == 0;}

    @Override
    public int size(){return numberOfItems;}

    // resize array to an array with capacity
    private void resize(int capacity){
        Item[] temp = (Item[]) new Comparable[capacity];
        for (int i = 0; i < numberOfItems; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    // add item to pq in correct spot, i.e. array in ascending order
    // if there is no room left on pq, resize array (doubling it)
    @Override
    public void insert(Item itemToAdd){
        if(numberOfItems == pq.length){
            resize(2*pq.length);
        }
        //find index where itemToAdd should be added
        int indexToCheck = numberOfItems -1;
        while(indexToCheck >= 0 && Sort.isStrictLess(itemToAdd,pq[indexToCheck])){
            pq[indexToCheck + 1] = pq[indexToCheck];
            indexToCheck--;
        }

        pq[indexToCheck + 1] = itemToAdd; // add item to pq
        numberOfItems++; // increment number of items
    }

    // delete max item from pq
    // resize array if necessary
    @Override
    public Item delMax(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item max = pq[numberOfItems - 1];
        pq[numberOfItems - 1] = null; //avoid loitering
        numberOfItems --;
        if(numberOfItems > 0 && numberOfItems == pq.length/4 ) {
            resize(pq.length/2);
        }
        return max;

    }
}
