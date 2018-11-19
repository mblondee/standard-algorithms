package collections.deque;

/*implements
 * deque generic items
 * uses linked lists:
*  adding and removing can both be add beginning or at end
 * */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<Item> implements Iterable<Item> {
    private int numberOfItems; //number of items on queue
    private Node<Item> first; //first node
    private Node<Item> last; // last node

    private class Node<T>{
        T item;
        Node<T> next;
    }

    // initiate empty deque
    public LinkedDeque(){
        numberOfItems = 0;
        first = null;
        last = null;
    }

    //return true if deque is empty, else false
    public boolean isEmpty(){
        return numberOfItems == 0;
    }

    //returns number of items in deque
    public int size(){
        return numberOfItems;
    }



    // adds item to beginning of deque
    public void addFirst(Item item){
        if(item == null){throw new IllegalArgumentException("you cannot add null");}
        Node<Item> prevFirst = first;// save previous first
        first = new Node<Item>(); // new first node
        first.item = item;
        first.next = prevFirst;

        // if deque was empty last should be same as first
        if (isEmpty()){
            last = first;
        }

        numberOfItems ++;
    }

    //adds item to end of deque
    public void addLast(Item item){
        if(item == null){throw new IllegalArgumentException("you cannot add null");}
        Node<Item> prevLast = last; //save previous last
        last = new Node<Item>(); // new last node
        last.item = item;
        last.next = null;

        // if deque was empty first should be same as last
        if (isEmpty()){
            first = last;
        }
        else {
            prevLast.next = last;
        }
        numberOfItems++;
    }

    //removes item at beginning
    public Item removeFirst(){
        if(isEmpty()){throw new NoSuchElementException();}
        Item itemToReturn = first.item;
        first = first.next;
        numberOfItems--;
        if(isEmpty()){
            //avoid loitering
            last = null;
        }
        return itemToReturn;
    }

    //removes item at end
    public Item removeLast(){
        if(isEmpty()){throw new NoSuchElementException();}
        Item itemToReturn = last.item;
        //only one element in deque
        if(first == last){
            first = null;
            last = null;
        }
        else{
            Node<Item> currentNode = first;
            // we know there are at east two items in deque so currentNode.next is a Node
            while(currentNode.next.next != null){
                currentNode = currentNode.next;
            }
            //currentNode is now parent to last node this should become new last item
            last = currentNode;
            last.next = null;
        }
        numberOfItems--;
        return itemToReturn;
    }

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
