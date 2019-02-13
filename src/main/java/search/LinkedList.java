package search;

public class LinkedList<Key, Value> {

    private int size;
    private Node firstNode; // first node in linked list

    private class Node{
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }



    /*
     * return number of linked list
     * */
    public int getSize(){
        return size;
    }

    /*
     * is linked list empty?
     * */

    public boolean isEmpty(){
        return size == 0;
    }

    /*
    * return value for {@code key}
    * */
    public Value get(Key key){
        for(Node node = firstNode; node != null; node = node.next){
            if(key.equals(node.key)){
                return node.value;
            }
        }
        return null;
    }

    /*
    * does linked list contain {@code key}?
    * */
    public boolean contains(Key key){
        return get(key) != null;
    }

    /*
    * insert key-value pair into linked list
    * */
    public void put(Key key, Value value){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(value == null){
            return;
        }
        // is key already in the linked list?
        for(Node node = firstNode; node != null; node = node.next){
            if(key.equals(node.key)){
                node.value = value;
                return;
            }
        }
        // else add at front of the list
        firstNode = new Node(key, value, firstNode);
        size ++;

    }

    /*
    * delete key-value pair corresponding to {@code key}
    * */
    public void delete(Key key){
        firstNode = delete(firstNode, key);
    }

    private Node delete(Node node, Key key){
        if(node == null){return null;}
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(key.equals(node.key)){
            size --;
            return node.next;
        }
        node.next = delete(node.next, key);
        return node;
    }







}
