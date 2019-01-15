package sorting.priorityqueue;

/*
 * priority queue implementation using an ordered array
 * using a comparator
 * */

import sorting.sort.Sort;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class OrderedArrayMaxPQComparator<Item> implements PriorityQueue<Item> {
    private Item[] pq; //array of items
    private int numberOfItems; //number of items in pq
    private Comparator<Item> comparatorItem; // comparator used to order items

    //initialize empty pq
    public OrderedArrayMaxPQComparator(Comparator<Item> comparator){
        pq = (Item[]) new Object[2]; // initially pq array of size 2
        numberOfItems = 0; // initially no items in pq
        comparatorItem = comparator;
    }

    @Override
    public boolean isEmpty(){ return numberOfItems == 0;}

    @Override
    public int size(){return numberOfItems;}

    // resize array to an array with capacity
    public void resize(int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < numberOfItems; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    // add item to pq in correct index i.e. such that array is ordered
    // if there is no room left on pq, resize array (doubling it)
    @Override
    public void insert(Item itemToAdd){
        if(numberOfItems == pq.length){
            resize(2*pq.length);
        }
        //find index where itemToAdd should be added
        int indexToCheck = numberOfItems -1;
        while(indexToCheck >= 0 && Sort.isStrictLess(itemToAdd,pq[indexToCheck],comparatorItem)){
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
