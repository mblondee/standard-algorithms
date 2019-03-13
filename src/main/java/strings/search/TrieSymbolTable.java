package strings.search;

/*
* a symbol table of key-values pairs where the keys are strings
* using a multiway trie
*
* return all keys with a given prefix
* return all keys that match a pattern (with wildcards)
* return longest prefix in set of keys of a given string
* */

import collections.queue.LinkedQueue;


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

    /*
    * return all keys as an Iterable
    * */
    public Iterable<String> keys(){
        return keysWithPrefix("");
    }

    /*
    * return all keys that start with {@code prefix}
    * */
    public Iterable<String> keysWithPrefix(String prefix){
        LinkedQueue<String>  queue = new LinkedQueue<>();
        // find node that contains prefix
        Node<Value> node = get(root, prefix, 0);
        // collect all keys that start from node
        collect(node, new StringBuilder(prefix), queue);
        return queue;
    }

    private void collect(Node node, StringBuilder string, LinkedQueue<String> queue){
        if(node == null){
            return;
        }

        if(node.value != null){
            // if a key has been found add to queue
            queue.enqueue(string.toString());
        }

        // if a key has not been found
        for(char c = 0; c<RADIX; c++){
            // append c
            string.append(c);
            // collect from c -> if there is no key it will eventually end in a null node
            collect(node.next[c], string, queue);
            // if string has been added to queue c can be removed (last char in string)
            string.deleteCharAt(string.length()-1);
        }
    }


    /*
    * return all keys that match {@code pattern} where . is a wildcard
    * will look for strings with length of wildcard
    * */
    public Iterable<String> keysThatMatch(String pattern){
        LinkedQueue<String>  queue = new LinkedQueue<>();
        collect(root, new StringBuilder(), pattern, queue);
        return queue;
    }

    private void collect(Node node, StringBuilder string, String pattern, LinkedQueue<String> queue){
        if(node == null){
            return;
        }
        int d = string.length();
        // suppose pattern has same length as string -> enqueue string if current node has a value (string is key)
        if(pattern.length() == d && node.value != null){
            queue.enqueue(string.toString());
        }
        // suppose pattern has same length as string but current node has no value (string is not a key)
        if(pattern.length() == d){
            // done we cannot find a match
            return;
        }
        // else we still need to check next characters
        char c = pattern.charAt(d);
        if(c == '.'){
            // all char are possible -> check them all
            for(char ch = 0; ch < RADIX; ch++){
                string.append(ch);
                collect(node.next[ch], string, pattern, queue);
                string.deleteCharAt(string.length()-1);
            }
        }
        else{
            // only c is possible as next char
            string.append(c);
            collect(node.next[c], string, pattern, queue);
            string.deleteCharAt(string.length()-1);
        }
    }

    /*
    * returns the string that is the longest prefix of {@code string}
    * */
    public String longestPrefixOf(String string){
        if(string == null){
            throw new IllegalArgumentException("input cannot be null");
        }
        return longestPrefixOf(root, new StringBuilder(), string, 0, "");
    }

    private String longestPrefixOf(Node node, StringBuilder stringFitting, String string, int currentChar, String currentMatch){
        // no more options return last match
        if(node == null){
            return currentMatch;
        }
        // string itself is a key
        if(stringFitting.length() == string.length() && node.value != null){
            return stringFitting.toString();
        }
        // we have reached the end of string but it is not a key
        if(stringFitting.length() == string.length()){
            return currentMatch;
        }
        // there are still characters to investigate
        // next char that has to be matched
        char c = string.charAt(currentChar);
        if(node.next[c] != null){
            stringFitting.append(c);
            if(node.next[c].value != null){
                currentMatch = stringFitting.toString();
            }
            return longestPrefixOf(node.next[c], stringFitting, string, currentChar+1 , currentMatch);
        }
        return currentMatch;
    }

}
