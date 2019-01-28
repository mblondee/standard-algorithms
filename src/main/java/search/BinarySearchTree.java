package search;

/*
* Symbol table (associative array abstraction) implementation using a binary search tree
*
* operations: time proportional to height of the tree
* */

//TODO: finalize delete
//TODO: find min/max key
//TODO: find floor (largest less than)/ceil (smallest larger than)
//TODO: how many keys <key?
//TODO: add iteration

public class BinarySearchTree<Key extends Comparable<Key>, Value> implements Search<Key, Value>{

    private Node root; // root of the tree

    private class Node{
        private Key key;
        private Value value;
        private Node leftNode; //left subtree
        private Node rightNode; //right subtree
        private int size; //total number of nodes

        public Node(Key key, Value value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    /*
    * returns number of key-value pairs in tree
    * */
    public int sizeOfTree(){
        return size(root);
    }

    /*
     * returns number of key-value pairs in subtree with root node
     * */
    private int size(Node node){
        if(node == null){return 0;}
        else{
            return node.size;
        }
    }

    @Override
    public int getSize() {
        return 0;
    }

    /*
    * is tree empty?
    * */
    public boolean isEmpty(){
        return sizeOfTree() == 0;
    }


    /*
    * does the tree contain a given key?
    * */

    public boolean contains(Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        return get(key) != null;
    }

    /*
    * return value for given key
    * */
    public Value get(Key key){
        return get(root, key);
    }

    /*
    * find value for given key in subtree with root node
    * */
    private Value get(Node node, Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(node == null){return null;}
        // is node the one having key? or do we need to check the subtrees?
        int comparison = node.key.compareTo(key);
        // look in left subtree
        if(comparison < 0){
            return get(node.leftNode,key);
        }
        //look in right subtree
        else if(comparison > 0){
            return get(node.rightNode, key);
        }
        //node is the one having the key (comparison = 0, node.key = key)
        else{
            return node.value;
        }
    }


    /*
    * insert key-value pair in tree
    * */
    public void put(Key key, Value value){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(value == null){return;}
        // replace root with tree in which key-value is inserted
        root = put(root, key, value);
    }


    /*
    * insert key-value pair in subtree with root node
    * and return new subtree
    * */
    private Node put(Node node, Key key, Value value){
        //if subtree is empty create new node
        if(node == null){return new Node(key, value, 1);}
        // check in which subtree of node we must look
        int comparison = node.key.compareTo(key);
        // go to left subtree
        if(comparison < 0){
            put(node.leftNode, key, value);
        }
        else if (comparison > 0){
            put(node.rightNode, key, value);
        }
        // this node has key -> update value
        else{
            node.value = value;
        }
        //update size of node
        node.size = size(node.leftNode) + size(node.rightNode) + 1;
        return node;
    }

    /*
    * delete node with key
    * */
    public void delete(Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        // replace root with tree in which key is deleted
        root = delete(root, key);
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key Ceiling(Key key) {
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    /*
     * delete key-value pair in subtree with root node
     * and return new subtree
     * */
    private Node delete(Node node, Key key){
        //if subtree is empty return empty subtree
        if(node == null){return null;}
        // check in which subtree of node we must look for key
        int comparison = node.key.compareTo(key);
        // go to left subtree
        if(comparison < 0){
            delete(node.leftNode, key);
        }
        else if (comparison > 0){
            delete(node.rightNode, key);
        }
        // this node has key -> remove
        else{
            // there is no left subtree -> left with right subtree
            if(node.leftNode == null){
                return node.rightNode;
            }
            // there is no right subtree -> left with left subtree
            if(node.rightNode == null){
                return node.leftNode;
            }
            // copy of node
            Node copyNode = node;
            //TODO: think

        }
        //update size of node
        node.size = size(node.leftNode) + size(node.rightNode) + 1;
        return node;
    }

}
