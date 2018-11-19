package collections.queue;

/*implements
 * queue of generic items
 * uses resizable array:
 * enqueue end of array
 * dequeue randomly
 * iterator random order
 * */

import help.libraries.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arrayQueue; // array of items
    private int numberOfItems; // number of items in queue
    private int last; // next available index at the end of array

    //initialize empty queue
    public RandomizedQueue(){
        arrayQueue = (Item[]) new Object[2]; // array of size 2
        numberOfItems = 0; // zero items in queue
        last = 0;
    }

    // returns true if queue is empty, else false
    public boolean isEmpty(){
        return numberOfItems == 0;
    }

    // returns number of elements in queue
    public int sizeOfQueue(){
        return numberOfItems;
    }

    // resize array to an array with capacity
    public void resize(int capacity){
        //System.out.println("resizing to capacity " + capacity);
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < numberOfItems; i++) {
            temp[i] = arrayQueue[i];
        }
        arrayQueue = temp;
        // adjust last
        last = numberOfItems;
    }

    // enqueues item at end of array
    // doubling size of array if necessary
    public void enqueue(Item itemToAdd){
        if(numberOfItems == arrayQueue.length){
            resize(numberOfItems*2);
        }
        arrayQueue[last++] = itemToAdd;
        numberOfItems++;
    }

    // removes item at random index
    // if this index is not last index, copies last item into this index
    // removes last
    // if necessary array is resized
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(numberOfItems); //picking random int in [0, numberOfItems]
        Item itemToRemove = arrayQueue[randomIndex];
        if (randomIndex != last-1){
            arrayQueue[randomIndex] = arrayQueue[last-1];
        }
        arrayQueue[last-1] = null; //avoid loitering
        numberOfItems--;
        last--;
        if(numberOfItems > 0 && numberOfItems == arrayQueue.length/4 ) {
            resize(arrayQueue.length/2);
        }
        return itemToRemove;
    }

    public int getArrayQueue(){
        return arrayQueue.length;
    }

    // returns an iterator that iterates randomly through the items
    public Iterator<Item> iterator(){
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{
        private final Item[] copy;
        private int currentSize = numberOfItems;

        //copy items of original array
        private RandomIterator(){
            copy = (Item[]) new Object[currentSize];
            for(int i = 0; i<currentSize; i++){
                copy[i] = arrayQueue[i];
            }
        }

        public boolean hasNext(){
            return currentSize > 0 ;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            //randomly select index in copy
            int randomIndex = StdRandom.uniform(currentSize);
            Item itemToReturn = copy[randomIndex];
            // if randomIndex is not last index
            // copy last item into randomIndex
            if(randomIndex != currentSize - 1){
                copy[randomIndex] = copy[currentSize - 1];
            }
            // remove last index
            copy[currentSize - 1] = null; //avoid loitering
            currentSize--;
            return itemToReturn;
        }
    }


}
