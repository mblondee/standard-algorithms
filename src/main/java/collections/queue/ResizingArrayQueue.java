package collections.queue;

/*implements
 * first-in-first-out (fifo) queue of generic items
 * uses resizable array:
 * enqueue end of array
 * dequeue beginning of array
 * */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private Item[] arrayQueue; // array of items
    private int numberOfItems; // number of items in queue
    private int first; // index of first element
    private int last; // next available index at end

    //initialize empty queue
    public ResizingArrayQueue(){
        arrayQueue = (Item[]) new Object[2]; // array of size 2
        numberOfItems = 0; // zero items in queue
        first = 0;
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
    // first and last can be any index in array
    // temp[i] = arrayQueue[first + i ] works as long as first + i is not out of bounds
    // if out of bounds we need to continue at start of array (circular effect)
    // modulo size of arrayQueue makes sure we get the correct index
    public void resize(int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < numberOfItems; i++) {
            temp[i] = arrayQueue[(first + i) % arrayQueue.length];
        }
        arrayQueue = temp;
        // adjust first and last
        first = 0;
        last = numberOfItems;
    }

    // enqueues item at end of array
    // doubling size of array if necessary
    // if after adding the queue, last has gone out of bounds we put last = 0 (circular effect)
    public void enqueue(Item itemToAdd){
        if(numberOfItems == arrayQueue.length){
            resize(numberOfItems*2);
        }
        arrayQueue[last++] = itemToAdd;
        // check if this makes queue full
        // if so put last = 0
        if (last == arrayQueue.length) {
            last = 0; // circular effect
        }
        numberOfItems++;

    }


    // dequeues item at beginning of array
    // if after dequeueing first has gone out of bounds we put first = 0 (circular effect)
    // if necessary array is resized
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Item itemToDequeue = arrayQueue[first];
        arrayQueue[first] = null; // avoid loitering
        numberOfItems--;
        first ++;
        if(first == arrayQueue.length){
            first = 0;
        }
        if(numberOfItems > 0 && numberOfItems == arrayQueue.length/4 ) {
            resize(arrayQueue.length/2);
        }
        return itemToDequeue;

    }

    // returns item at beginning of array without removing it
    public Item peek(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return arrayQueue[first];
    }

    public int getArrayQueue(){
        return arrayQueue.length;
    }

    // returns an iterator that iterates through the items in fifo order
    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item>{
        private int current = 0;

        public boolean hasNext(){
            return current < numberOfItems;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            // look for index first + current and adjust if out of bounds
            Item item = arrayQueue[(current + first) % arrayQueue.length];
            current ++;
            return item;
        }
    }
}
