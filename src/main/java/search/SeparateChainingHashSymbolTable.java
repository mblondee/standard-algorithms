package search;

/*
* A symbol table of generic key-value pairs using a separate chaining hash table
* items that hash to the same index are chained together in linked lists
* */

public class SeparateChainingHashSymbolTable<Key, Value> {

    private static int DEFAULTSIZE = 4;

    private int size; //number of key-value pairs
    private int hashTableSize; //length of hash table
    private LinkedList<Key, Value>[] searchTable; //hash table: array of linked lists


    /*
    * initialize hash table with given size
    * */
    public SeparateChainingHashSymbolTable(int hashTableSize){
        this.hashTableSize = hashTableSize;
        searchTable = new LinkedList[hashTableSize];
        for(int i = 0; i < hashTableSize; i++){
            searchTable[i] = new LinkedList<>();
        }
    }

    /*
     * initialize hash table without specifying size
     * */
    public SeparateChainingHashSymbolTable(){
        this(DEFAULTSIZE);
    }



    /*
    * hash value between 0 and upperbound - 1
    * 0x7fffffff is 0111 1111 1111 1111 1111 1111 1111 111: all 1 except the sign bit
    * key.hashCode() & 0x7fffffff will result in a positive integer
    * */
    private int hash(Key key, int upperbound){
        return (key.hashCode() & 0x7fffffff) % upperbound;
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
    * resize hashtable
    * */
    private void resize(int capacity){

        LinkedList<Key, Value>[] temp = new LinkedList[capacity];
        for(int i = 0; i < capacity; i++){
            temp[i] = new LinkedList<>();
        }
        for(int i = 0; i < hashTableSize; i++){
            //iterate over keys in linked list at searchTable[i]
            for(Key key: searchTable[i].keys()){
                // get hash for key: pair has to be added in linked list at temp[index]
                int index = hash(key, capacity);

                // put key-value pair in temp
                temp[index].put(key, searchTable[i].get(key));
            }
        }
        this.hashTableSize = capacity;
        this.searchTable = temp;

    }

    /*
    * insert {@code key} {@code value} pair into hash table
    * */
    public void put(Key key, Value value){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(value == null){return;}

        // if average length of linked lists is >=10 size of searchTable will be doubled
        if(size > hashTableSize*10){
            resize(2*hashTableSize);
        }

        // get hash for key: pair has to be added in linked list at searchTable[index]
        int index = hash(key, hashTableSize);


        if(!searchTable[index].contains(key)){
            size++;
        }
        searchTable[index].put(key, value);
    }


    /*
     * return value for {@code key}
     * */
    public Value get(Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        int index = hash(key, hashTableSize);
        if(searchTable[index] == null){
            return null;
        }
        return searchTable[index].get(key);
    }


    /*
     * does hashtable contain {@code key}?
     * */
    public boolean contains(Key key){
        return get(key) != null;
    }

    /*
     * delete key-value pair corresponding to {@code key}
     * */
    public void delete(Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        int index = hash(key, hashTableSize);
        if(searchTable[index].contains(key)){
            size--;
        }
        searchTable[index].delete(key);

        // if average length of linked lists is <=2 size of searchTable will be halved
        if( size > DEFAULTSIZE && size <= 2*hashTableSize){
            resize(hashTableSize/2);
        }
    }
}
