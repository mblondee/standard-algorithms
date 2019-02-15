package search;

/*
 * A symbol table of generic key-value pairs using linear probing
 * if there is an item that hashes to the same index that is already in the table it is put in the next available index
 * */

public class LinearProbingHashSymbolTable<Key, Value> {
    private static int DEFAULTSIZE = 4;

    private int size; //number of key-value pairs
    private int hashTableSize; //length of hash table
    private Key[] keys; // array of keys
    private Value[] values; // array of values

    /*
     * initialize hash table with given size
     * */
    public LinearProbingHashSymbolTable(int hashTableSize){
        this.hashTableSize = hashTableSize;
        keys = (Key[]) new Object[hashTableSize];
        values = (Value[]) new Object[hashTableSize];
    }

    /*
     * initialize hash table without specifying size
     * */
    public LinearProbingHashSymbolTable(){
        this(DEFAULTSIZE);
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
     * hash value between 0 and upperbound - 1
     * 0x7fffffff is 0111 1111 1111 1111 1111 1111 1111 111: all 1 except the sign bit
     * key.hashCode() & 0x7fffffff will result in a positive integer
     * */
    private int hash(Key key, int upperbound){
        return (key.hashCode() & 0x7fffffff) % upperbound;
    }



    /*
     * resize hashtable
     * */
    private void resize(int capacity){
        Key[] tempKeys = (Key[]) new Object[capacity];
        Value[] tempValues = (Value[]) new Object[capacity];

        // go through hashtable and insert in temporary arrays
        for(int i = 0; i< hashTableSize; i++){
            if(keys[i] != null){
                int index;
                // is index in tempKeys still available?, otherwise continue
                // at the end of the array i->0 hence i = (i+1) % hashTableSize
                for(index = hash(keys[i], capacity); tempKeys[index] != null; index = (index+1) % capacity){
                    // if key in keys is the same: update value
                    if(keys[i].equals(tempKeys[index])){
                        tempValues[index] = values[i];
                        return;
                    }
                }
                tempKeys[index] = keys[i];
                tempValues[index] = values[i];
            }
        }


        this.hashTableSize = capacity;
        this.keys = tempKeys;
        this.values = tempValues;
    }

    /*
     * insert {@code key} {@code value} pair into hash table
     * */
    public void put(Key key, Value value){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(value == null){return;}

        //double table if half full
        if(size >= hashTableSize/2){
            resize(2*hashTableSize);
        }

        int index;
        // is index still available?, otherwise continue
        // at the end of the array i->0 hence i = (i+1) % hashTableSize
        for(index = hash(key, hashTableSize); keys[index] != null; index = (index+1) % hashTableSize){
            // if key in keys is the same: update value
            if(key.equals(keys[index])){
                values[index] = value;
                return;
            }
        }
        // put key en value in index
        keys[index] = key;
        values[index] = value;
        size++;

    }

    /*
     * return value for {@code key}
     * */
    public Value get(Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        int index;
        for(index = hash(key, hashTableSize); keys[index] != null; index = (index + 1) % hashTableSize){
            if(key.equals(keys[index])){
                return values[index];
            }
        }
        // keys[hash(key, hashTableSize)] == null -> key is not in table
        return null;

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
        //table does not contain key
        if(! contains(key)){return;}

        // find index of key
        int index = hash(key, hashTableSize);
        while(!key.equals(keys[index])){
            index = (index+1)%hashTableSize;
        }

        // delete key and value
        keys[index] = null;
        values[index] = null;

        // rehash keys from index+1 until null in keys (following keys will have been put in place)
        index = (index + 1)%hashTableSize;
        while(keys[index] != null){
            Key keyToInsert = keys[index];
            Value valueToInsert = values[index];
            //delete from table
            keys[index] = null;
            values[index] = null;
            size --;
            //reinsert
            put(keyToInsert, valueToInsert);
            index = (index + 1)%hashTableSize;
        }
        size --;


        //if table is only 1/8 full it is halved
        if(size >= DEFAULTSIZE && size <= hashTableSize/8){
            System.out.println("resize delete " + key );
            resize(hashTableSize/2);
        }

    }



}
