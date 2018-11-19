package collections.queue;

/*implements
 * first-in-first-out (fifo) stack of generic items
 * uses linked lists:
 * enqueue end of linked list
 * dequeue beginning of linked list
 * */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {

    private int numberOfItems; //number of items on queue
    private Node<Item> first; //first node
    private Node<Item> last; // last node

    private class Node<T>{
        T item;
        Node<T> next;
    }

    //initialize empty queue
    public LinkedQueue(){
        first = null;
        last = null;
        numberOfItems = 0;
    }

    // returns true if queue is empty, else false
    public boolean isEmpty(){
        return numberOfItems == 0;
    }

    // returns number of elements in queue
    public int sizeOfQueue(){
        return numberOfItems;
    }

    // enqueues item at end of linked list
    public void enqueue(Item itemToAdd){
        Node<Item> prevLast = last; // save current last
        last = new Node<>(); // last is new node
        last.item = itemToAdd;
        last.next = null;
        if (isEmpty()){
            // if queue is empty then first and last now point to same node
            first = last;
        }
        else{
            prevLast.next = last;

        }
        numberOfItems++;
    }

    // dequeues item at beginning of linked list
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Item itemToReturn = first.item;
        first = first.next;
        numberOfItems--;
        if(isEmpty()){
            //avoid loitering
            last = null;
        }
        return itemToReturn;
    }

    // returns item at beginning of linked list without removing it
    public Item peek(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return first.item;
    }

    //iterates from beginning to end of linked list
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node<Item> current = first;

        public boolean hasNext(){
            return current!=null;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }
}
