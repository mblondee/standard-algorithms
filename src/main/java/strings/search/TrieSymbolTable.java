package strings.search;

/*
* a symbol table of key-values pairs where the keys are strings
* using a multiway trie
* */

public class TrieSymbolTable<Value> {

    private static int RADIX = 256; // extended ASCII
    private Node root; // root of trie
    private int numberOfKeys;

    private class Node<T>{
        private T value;
        private Node<T>[] next = new Node[RADIX];
    }

    /*
    * is symbol table empty?
    * */
    public boolean isEmpty(){
        return numberOfKeys == 0;
    }

    /*
    * return size of symbol table
    * */
    public int size(){
        return numberOfKeys;
    }

    /*
    * insert a new key-value pair
    * */
    public void put(String key, Value value){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        if(value == null){
            throw new IllegalArgumentException("value may not be null");
        }
        root = put(root, key, value, 0);
    }

    private Node put(Node node, String key, Value value, int depth){
        if(node == null){
            node = new Node();
        }
        if(depth == key.length()){
            if(node.value == null){
                numberOfKeys++;
            }
            node.value = value;
            return node;
        }
        int c = key.charAt(depth);
        node.next[c] = put(node.next[c], key, value, depth+1);
        return node;
    }

    /*
    * return value from {@code key}
    * */
    public Value get(String key){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        Node node = get(root, key, 0);
        if(node == null){
            return null;
        }
        return (Value) node.value;
    }

    private Node get(Node node, String key, int digit){
        if(node == null){
            return null;
        }
        if(digit == key.length()){
            return node;
        }
        char c = key.charAt(digit);
        return get(node.next[c], key, digit+1);
    }

    /*
    * does the symbol contain {@code key}?
    * */
    public boolean contains(String key){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        return get(key) != null;
    }

    /*
    * delete {@code key} if present
    * */
    public void delete(String key){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        root = delete(root, key, 0);
    }

    private Node delete(Node node, String key, int depth){

        if(node == null){
            return null;
        }
        // we have found key
        if(depth == key.length()){
            // key is in table if value is not null
            if(node.value != null){
                numberOfKeys --;
            }
            node.value = null;
        }
        // continue looking for key
        else{
            int c = key.charAt(depth);
            node.next[c] = delete(node.next[c], key, depth+1);
        }

        // remove subtrie if it is empty
        if(node.value != null){
            // do nothing if there is a value
            return node;
        }
        for(int c = 0; c<RADIX; c++){
            //do nothing if there is still a value in next
            if(node.next[c] != null){
                return node;
            }
        }
        // if it is empty return null (remove it)
        return null;
    }

}
