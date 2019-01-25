package search;

/*
 * Symbol table (associative array abstraction) implementation with binary search in an ordered array
 *
 * Key type implements comparable interface
 * keys and values may not be null
 *
 * put and delete: linear time in the worst case
 * contains, get: logarithmic time
 * getSize, isEmpty: constant time
 * */

public class BinarySearchSymbolTable<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] values;
    private int size = 0; // number of key-value pairs

    private static int CAPACITY = 2; //initial capacity of arrays

    public BinarySearchSymbolTable() {
        keys = (Key[]) new Comparable[CAPACITY];
        values = (Value[]) new Object[CAPACITY];
    }

    /*resize underlying arrays to arrays with capacity*/
    private void resize(int capacity) {
        Key[] tempKeys = (Key[]) new Comparable[capacity];
        Value[] tempValues = (Value[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = values[i];
        }
        keys = tempKeys;
        values = tempValues;
    }

    /*
    * returns number of key-value pairs
    * */
    public int getSize(){
        return size;
    }

    /*
    * is symbol table empty?
    * */
    public boolean isEmpty(){
        return size == 0;
    }

    /*
    * does the symbol table contain a given key?
    * */
    public boolean contains(Key key){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        return (get(key) != null);
    }

    public void print(){
        System.out.println("keys: ");
        for(Key key: keys){
            System.out.println(key + " ");
        }
        System.out.println();
        System.out.println("values: ");
        for(Value val: values){
            System.out.println(val + " ");
        }
        System.out.println();
    }

    /*
    * returns value associated with a given key
    * */
    public Value get(Key key){
        //System.out.println("calling get with key " + key);
        //System.out.println("current printing: ");
        //print();
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        if(isEmpty()){
            return null;
        }
        // find index of key in keys
        int rank = getRank(key);
        //System.out.println("rank " + rank);
        //System.out.println("size " + rank);
        // if rank = 0 check if correct key
        if(rank < size && keys[rank].compareTo(key) == 0){
            return values[rank];
        }
        return null;


    }

    /*
    * returns rank of key in keys: number of keys strictly less than key
    * using binary search
    * if key is in keys, the index will be returned
    * if key is not in keys, 0 or size will be returned
    * */
    private int getRank(Key key){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }

        int low = 0;
        int high = size - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            // key in index mid?
            int comparation = key.compareTo(keys[mid]);
            // key < keys[mid]
            if(comparation < 0){
                high = mid -1;
            }
            // key > key[mid]
            else if (comparation > 0){
                low = mid + 1;
            }
            // key = key[mid]
            else{
                return mid;
            }

        }
        return low;
    }


    /*
    * insert a key-value pair into the symbol table
    * */

    public void put(Key key, Value value){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        if(value == null){
            throw new IllegalArgumentException("value may not be null");
        }

        int rank = getRank(key);

        //check if key is already in keys
        if(rank < size && keys[rank].compareTo(key) == 0){
            // overwrite value associated with key
            values[rank] = value;
            return;
        }

        //if it is a new pair
        //resize if necessary
        if(size == keys.length){
            resize(2*keys.length);
        }
        //move elements in index > rank (from right to left)
        for(int i = size; i > rank; i++){
            keys[i] = keys[i-1];
            values[i] = values[i-1];
        }
        // put new pair
        keys[rank] = key;
        values[rank] = value;
        //increment size
        size++;
    }

    /*
    * delete a key and its value
     */
    public void delete(Key key){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        //if empty nothing to delete
        if(isEmpty()){
            return;
        }
        //find rank
        int rank = getRank(key);
        //key is not in keys
        if(rank == size || keys[rank].compareTo(key) != 0){
            return;
        }
        //key is in keys
        //move all keys right from key to the left
        for(int i = rank; i<size -1 ; i++){
            keys[i] = keys[i+1];
            values[i] = values[i+1];
         }
         //decrement size
        size--;
        //avoid loitering
        keys[size] = null;
        values[size] = null;

        //resize if necessary
        if(size > 0 && size == keys.length/4){
            resize(keys.length/2);
        }
    }

}
