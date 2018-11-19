package collections.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*implements
 * collection of generic items
 * items can be added and not removed
 * uses linked lists
 * */

public class LinkedBag<Item> implements Iterable<Item> {

    private Node<Item> first; // first node in the bag
    private int numberOfElements; // number of elements in the bag

    //inner class
    //linked lists
    private class Node<T>{
        private T item;
        private Node<T> next;
    }

    //initialize empty bag
    public LinkedBag(){
        first = null;
        numberOfElements = 0;
    }

    //returns true is bag is empty else false
    public Boolean isEmpty(){
        return numberOfElements == 0;
    }

    //returns number of items in bag
    public int size(){
        return numberOfElements;
    }

    //add items to bag
    public void add(Item itemToAdd){
        Node<Item> prevFirst = first;
        first = new Node<>(); // make new node
        first.item = itemToAdd; // itemToAdd is current node
        first.next = prevFirst; // points to prevFirst
        numberOfElements++;
    }

    //returns an iterator that iterates over the items
    public Iterator<Item> iterator(){
        return new ListIterator<>(first);
    }

    // an iterator
    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public ListIterator(Node<Item> first){
            current = first;
        }

        public boolean hasNext(){
            return current != null;
        }

        // does not support remove
        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
