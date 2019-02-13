package search;

/*
* A symbol table of generic key-value pairs using a separate chaining hash table
* items that hash to the same index are chained together in linked lists
* */

public class SeparateChainingHashSymbolTable<Key, Value> {


    private int size; //number of key-value pairs
    private int hashTableSize; //length of hash table
    private LinkedList<Key, Value>[] searchTable; //hash table: array of linked lists


    /*
    * initialize hash table with given size
    * */
    public SeparateChainingHashSymbolTable(int hashTableSize){
        this.hashTableSize = hashTableSize;
        searchTable = new LinkedList[hashTableSize];
        // we will put empty linked lists in searchTable if necessary (in put method)
    }

    /*
    * hash value between 0 and hashTableSize - 1
    * 0x7fffffff is 0111 1111 1111 1111 1111 1111 1111 111: all 1 except the sign bit
    * key.hashCode() & 0x7fffffff will result in a positive integer
    * */
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % hashTableSize;
    }

    /*
    * return number of key-value pairs
    * */
    public int getSize(){
        return size;
    }

    /*
    * is hash table empty?
    * */

    public boolean isEmpty(){
        return size == 0;
    }

    /*
    * insert {@code key} {@code value} pair into hash table
    * */
    public void put(Key key, Value value){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(value == null){return;}

        //TODO resize if necessary!

        // get hash for key: pair has to be added in linked list at searchTable[index]
        int index = hash(key);

        // if searchTable[index] is null -> a new linked
        // list has to be made at this index
        if(searchTable[index] == null){
            searchTable[index] = new LinkedList<>();
        }

        if(!searchTable[index].contains(key)){
            size++;
        }
        searchTable[index].put(key, value);
    }
}
