package collections.bag;


/*implements
 * collection of generic items
 * items can be added and not removed
 * uses resizable arrays
 * */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayBag<Item> implements Iterable<Item>{

    private Item[] arrayBag; //array of items
    private int numberOfItems ; //number of items in bag

    //initialize empty bag
    public ResizingArrayBag(){
        arrayBag = (Item[]) new Object[2]; // start with an array of size 2
        numberOfItems = 0; // empty bag has 0 items
    }

    //returns true is bag is empty else false
    public Boolean isEmpty(){
        return numberOfItems == 0;
    }

    //returns number of items in bag
    public int size(){
        return numberOfItems;
    }

    // resize array to an array with capacity
    public void resize(int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < numberOfItems; i++){
            temp[i] = arrayBag[i];
        }
        arrayBag = temp;
    }

    public int getArrayBag(){
        return arrayBag.length;
    }

    //add items to bag
    // if there is no room left in bag resize array (doubling it)
    public void add(Item itemToAdd){
        if (numberOfItems == arrayBag.length){
            resize(numberOfItems*2); // double size if bag is full
        }
        arrayBag[numberOfItems] = itemToAdd; // put item in array
        numberOfItems++;
    }

    // returns an iterator that iterates through the items in arbitrary order
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
            return arrayBag[current++];
        }
    }


}
