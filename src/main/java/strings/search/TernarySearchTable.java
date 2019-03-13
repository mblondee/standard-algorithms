package strings.search;

/*
 * a symbol table of key-values pairs where the keys are strings
 * using ternary search trie
 * */

public class TernarySearchTable<Value> {
    private Node root; // root
    private int numberOfKeys;

    private class Node<T>{
        private T value;
        private char c;
        private Node left;
        private Node mid;
        private Node right;

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
     * insert a new key-value pair where key != ""
     * */
    public void put(String key, Value value){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        if(key.length() == 0){
            throw new IllegalArgumentException("key must have length >=1");
        }
        if(value == null){
            throw new IllegalArgumentException("value may not be null");
        }
        // TODO: check if st already contains key
        if(! contains(key)){
            numberOfKeys++;
        }
        root = put(root, key,value, 0);


    }

    private Node<Value> put(Node<Value> node, String key, Value value, int depth){
        char c = key.charAt(depth);

        if(node == null){
            // make new node
            node = new Node<>();
            node.c = c;
        }

        if(c< node.c){
            // go left
            node.left = put(node.left, key, value, depth);
        }
        else if(c > node.c){
            //go right
            node.right = put(node.right, key, value, depth);
        }
        // c == node.c -> check if we are at the end of key
        else if(depth < key.length()-1){
            // if not go mid + deeper
            node.mid = put(node.mid, key, value, depth+1);
        }
        else{
            // if so, add value
            node.value = value;
        }


        return node;
    }


    /*
     * return value from {@code key}
     * */
    public Value get(String key){
        if(key == null){
            throw new IllegalArgumentException("key may not be null");
        }
        if(key.length() == 0){
            throw new IllegalArgumentException("key must have length >=1");
        }
        Node<Value> node = get(root, key, 0);
        if(node == null){
            return null;
        }

        return node.value;
    }

    private Node<Value> get(Node<Value> node, String key, int depth){
        if(node == null){
            return null;
        }
        char c = key.charAt(depth);

        if(c< node.c){
            // go left
            return get(node.left, key, depth);
        }
        else if(c > node.c){
            //go right
            return get(node.right, key, depth);
        }
        // c == node.c -> check if we are at the end of key
        else if(depth < key.length()-1){
            // if not go mid + deeper
            return get(node.mid, key, depth+1);
        }
        else{
            // if so, return node
            return node;
        }

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
}
