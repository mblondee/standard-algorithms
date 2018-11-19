package collections.stack;

/*implements
 * last-in-first-out (lifo) stack of generic items
 * uses linked lists:
 * push and pop beginning of linked list
 * */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Iterable<Item> {

    private Node<Item> first ; // the first node in the linked list
    private int numberOfItems; // number of elements in stack

    private class Node<T>{
        T item; // Node holds reference to an item
        Node<T> next; // Node holds reference to next node
    }

    //initialize empty stack
    public LinkedStack(){
        first = null;
        numberOfItems = 0;
    }

    // returns true when stack is empty and else false
    public boolean isEmpty(){
        return numberOfItems == 0;
    }

    // returns number of items in stack
    public int sizeOfStack(){
        return numberOfItems;
    }

    // push item to stack
    // add to the beginning of the linked list
    public void push(Item itemToAdd){
        Node<Item> oldFirstNode = first; //save reference to old first node
        first = new Node<Item>();
        first.item = itemToAdd;
        first.next = oldFirstNode;
        numberOfItems ++;
    }

    // pop (top) element from stack if not empty
    // remove first node in linked list
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item elementToPop = first.item;
        first = first.next;
        numberOfItems--;
        return elementToPop;
    }

    // returns but does not remove top of stack
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    // returns an iterator that iterates through the items in lifo order
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node<Item> current;

        public ListIterator(){
            current = first;
        }

        public boolean hasNext(){
            return current != null;

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
