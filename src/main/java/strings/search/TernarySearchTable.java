package strings.search;

/*
 * a symbol table of key-values pairs where the keys are strings
 * using ternary search trie
 *
 * return all keys with a given prefix
 * return all keys that match a pattern (with wildcards)
 * return longest prefix in set of keys of a given string
 * */

import collections.queue.LinkedQueue;

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

    /*
     * return all keys as an Iterable
     * */
    public Iterable<String> keys(){
        LinkedQueue<String> queue = new LinkedQueue<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    private void collect(Node node, StringBuilder string, LinkedQueue<String> queue ){
        if(node == null){
            return;
        }
        //left
        collect(node.left, string, queue);

        //mid
        if(node.value != null){
            // add string to collection
            queue.enqueue(string.toString() + node.c);
        }
        collect(node.mid, string.append(node.c), queue);
        string.deleteCharAt(string.length()-1);

        //right
        collect(node.right, string, queue);
    }

    public Iterable<String> keysWithPrefix(String prefix){
        if(prefix == null){
            throw new IllegalArgumentException("prefix cannot be null");
        }
        LinkedQueue<String>  queue = new LinkedQueue<>();
        // find node that contains prefix
        Node<Value> node = get(root, prefix, 0);
        // check first node
        if(node == null){
            return queue;
        }
        if(node.value != null){
            queue.enqueue(prefix);
        }
        // collect all keys that start from node
        collect(node.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    /*
     * return all keys that match {@code pattern} where . is a wildcard
     * will look for strings with length of wildcard
     * */
    public Iterable<String> keysThatMatch(String pattern){
        if(pattern == null){
            throw new IllegalArgumentException("pattern cannot be null");
        }
        LinkedQueue<String>  queue = new LinkedQueue<>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    private void collect(Node node, StringBuilder stringToFit, int index, String pattern, LinkedQueue<String> queue){
        if(node == null){
            return;
        }
        char c = pattern.charAt(index);
        if( c == '.' || c < node.c){
            collect(node.left, stringToFit, index, pattern, queue);
        }
        if(c == '.' || c == node.c){
            if(index == pattern.length() -1 && node.value != null){
                queue.enqueue(stringToFit.toString() + node.c);
            }
            if( index < pattern.length()-1){
                collect(node.mid, stringToFit.append(node.c), index+1, pattern, queue);
                stringToFit.deleteCharAt(stringToFit.length()-1);
            }
        }
        if(c == '.' || c > node.c){
            collect(node.right, stringToFit, index, pattern, queue);
        }
    }

    /*
     * returns the string that is the longest prefix of {@code string}
     * */
    public String longestPrefixOf(String string){
        if(string == null){
            throw new IllegalArgumentException("input cannot be null");
        }
        // take care of empty string
        if(string.length() == 0){
            return null;
        }
        return longestPrefixOf(root, new StringBuilder(), string, 0, "");
    }

    private String longestPrefixOf(Node node, StringBuilder stringToFit, String string, int depth, String match){
        if(node == null){
            return match;
        }
        if(depth == string.length()){
            return match;
        }

            char c = string.charAt(depth);
            if(c < node.c){
                return longestPrefixOf(node.left, stringToFit, string, depth, match);
            }
            else if(c > node.c){
                return longestPrefixOf(node.right, stringToFit, string, depth, match);
            }
            else{
                stringToFit.append(c);
                if(node.value != null){
                    match = stringToFit.toString();
                }
                return longestPrefixOf(node.mid, stringToFit, string, depth+1, match);
            }

    }
}
