package collections.stack;

/*implements
* last-in-first-out (lifo) stack of generic items
* resizes array when necessary
* if too small (no sites left) -> double arraysize
* if too big (1/4) -> half arraysize
* */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] arrayStack; //array of items
    private int numberOfItems; //number of items in stack

    //initialize empty stack
    public ResizingArrayStack(){ ;
        arrayStack = (Item[]) new Object[2]; // initially array of size 2
        numberOfItems = 0; // initially no items in stack
    }

    public boolean isEmpty(){
        return numberOfItems == 0;
    }

    public int sizeOfStack(){
        return numberOfItems;
    }

    // resize array to an array with capacity
    public void resize(int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < numberOfItems; i++) {
            temp[i] = arrayStack[i];
        }
        arrayStack = temp;
    }

    // push item to stack
    // if there is no room left on stack resize array (doubling it)
    public void push(Item item){
        if(numberOfItems == arrayStack.length){
            resize(2*arrayStack.length);
        }
        arrayStack[numberOfItems] = item; // add item to stack
        numberOfItems++; // increment number of items
    }

    // pop element from stack if not empty
    // resize array if necessary
    public Item pop(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item itemToPop = arrayStack[numberOfItems - 1]; // item to pop
        arrayStack[numberOfItems - 1] = null; //avoid loitering
        numberOfItems--;
        if(numberOfItems > 0 && numberOfItems == arrayStack.length/4 ) {
            resize(arrayStack.length/2);
        }
        return itemToPop;
    }

    // returns but does not remove top of stack
    public Item peek(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return arrayStack[numberOfItems - 1];
    }

    public int getArrayStack(){
        return arrayStack.length;
    }

    // returns an iterator that iterates through the items in lifo order
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    // an iterator
    private class ReverseArrayIterator implements Iterator<Item>{
        private int currentIndex;

        public ReverseArrayIterator(){
            currentIndex = numberOfItems - 1; //current item is last element of array
        }

        public boolean hasNext(){
            return currentIndex >=0;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return arrayStack[currentIndex--];
        }
    }

}
