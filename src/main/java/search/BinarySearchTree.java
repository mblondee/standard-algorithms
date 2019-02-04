package search;

/*
* Symbol table (associative array abstraction) implementation using a binary search tree
*
* operations worst case: time proportional to height of the tree
* */


import sorting.sort.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    @Override
    public int getSize(){
        return size(root);
    }

    /*
     * returns number of key-value pairs in subtree with root {@code node}
     * */
    private int size(Node node){
        if(node == null){return 0;}
        else{
            return node.size;
        }
    }


    /*
    * is tree empty?
    * */
    @Override
    public boolean isEmpty(){
        return getSize() == 0;
    }


    /*
    * does the tree contain {@code key}?
    * */
    @Override
    public boolean contains(Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        return get(key) != null;
    }

    /*
    * return value for {@code key}
    * */
    @Override
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
        // node.key < key: look in right subtree
        if(comparison < 0){
            return get(node.rightNode,key);
        }
        // node.key > key: look in left subtree
        else if(comparison > 0){
            return get(node.leftNode, key);
        }
        //node is the one having the key (comparison = 0, node.key = key)
        else{
            return node.value;
        }
    }

    /*
    * how many keys are strictly less than {@code key}
    * */
    public int rank(Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        return rank(key, root);
    }

    /*
     * how many keys are strictly less than {@code key} in subtree rooted at {@code node}
     * */
    private int rank(Key key, Node node){
        if(node == null){return 0;}
        int comparison = node.key.compareTo(key);
        // node.key > key: we only need to count in left subtree
        if(comparison > 0){
            return rank(key, node.leftNode);
        }
        // node.key < key: size of left subtree + node + count in right subtree
        else if(comparison < 0){
            return size(node.leftNode) + 1 + rank(key, node.rightNode);
        }
        // node.key = key: size of left subtree
        else{
            return size(node.leftNode);
        }
    }

    public int sizeRange(Key lowKey, Key highKey){
        if(lowKey == null){throw new IllegalArgumentException("first key may not be null");}
        if(highKey == null){throw new IllegalArgumentException("second key may not be null");}
        if(Sort.isStrictLess(highKey, lowKey)){return 0;}
        if(contains(highKey)){
            return rank(highKey) - rank(lowKey) + 1;
        }
        else{
            return rank(highKey) - rank(lowKey);
        }
    }



    /*
    * insert key-value pair with {@code key} and {@code value} in tree
    * */
    @Override
    public void put(Key key, Value value){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(value == null){return;}
        // replace root with tree in which key-value is inserted
        root = put(root, key, value);
    }


    /*
    * insert key-value pair with {@code key} in subtree with root {@code node}
    * and return new subtree
    * */
    private Node put(Node node, Key key, Value value){
        //if subtree is empty create new node
        if(node == null){return new Node(key, value, 1);}
        // check in which subtree of node we must look
        int comparison = node.key.compareTo(key);
        // node.key > key: key has to be added in left subtree
        if(comparison > 0){
            node.leftNode = put(node.leftNode, key, value);
        }
        // node.key < key: key has to be added in right subtree
        else if (comparison < 0){
            node.rightNode = put(node.rightNode, key, value);
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
     * delete node with smallest key
     * */
    @Override
    public void deleteMin() {
        if(isEmpty()){
            throw new NoSuchElementException("table may not be empty");
        }
        // replace root with tree in which smallest key is deleted
        root = deleteMin(root);
    }

    /*
     * delete node with smallest key in subtree with root {@code node} and return subtree
     * we go left until finding a Node with a null left node (this is the minimum key node)
     * */
    private Node deleteMin(Node node){
        if(node.leftNode == null){
            return node.rightNode;
        }
        node.leftNode = deleteMin(node.leftNode);
        node.size = 1 + size(node.leftNode) + size(node.rightNode);
        return node;
    }

    /*
     * delete node with largest key
     * */
    @Override
    public void deleteMax() {
        if(isEmpty()){
            throw new NoSuchElementException("table may not be empty");
        }
        // replace root with tree in which largest key is deleted
        root = deleteMax(root);
    }

    /*
     * delete node with largest key in subtree with root {@code node} and return subtree
     * we go right until finding a Node with a null right node (this is the maximum key node)
     * */
    private Node deleteMax(Node node){
        if(node.rightNode == null){
            return node.leftNode;
        }
        node.rightNode = deleteMax(node.rightNode);
        node.size = 1 + size(node.leftNode) + size(node.rightNode);
        return node;
    }


    /*
     * delete node with {@code key}
     * */
    @Override
    public void delete(Key key){
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        // replace root with tree in which key is deleted
        root = delete(root, key);
    }

    /*
     * delete key-value pair with {@code key} in subtree with root {@code node}
     * and return new subtree
     * uses Hibbard deletion
     * */
    private Node delete(Node node, Key key){
        //if subtree is empty return empty subtree
        if(node == null){return null;}
        // check in which subtree of node we must look for key
        int comparison = node.key.compareTo(key);
        // node.key < key: go to right subtree
        if(comparison < 0){
            node.rightNode = delete(node.rightNode, key);
        }
        else if (comparison > 0){
            node.leftNode = delete(node.leftNode, key);
        }
        // this node has key -> remove node
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
            // replace node with min node in right subtree
            node = min(copyNode.rightNode);
            // set right subtree to right subtree in which min node has been deleted
            node.rightNode = deleteMin(copyNode.rightNode);
            // set left subtree to initial left subtree
            node.leftNode = copyNode.leftNode;

        }
        //update size of node
        node.size = size(node.leftNode) + size(node.rightNode) + 1;
        return node;
    }

    /*
    * return smallest key
    * */
    private Key min(){
        if(isEmpty()){
            throw new NoSuchElementException("table may not be empty");
        }
        // return key of min node in subtree with root
        return min(root).key;
    }

    /*
     * return node with smallest key in subtree with {@code node}
     * */
    private Node min(Node node){
        if(node.leftNode == null){return node;}
        return min(node.leftNode);
    }

    /*
     * return largest key
     * */
    private Key max(){
        if(isEmpty()){
            throw new NoSuchElementException("table may not be empty");
        }
        // return key of max node in subtree with root
        return max(root).key;
    }

    /*
     * return node with largest key in subtree with {@code node}
     * */
    private Node max(Node node){
        if(node.rightNode == null){return node;}
        return max(node.rightNode);
    }


    /*
    * return largest key less than or equal to {@code key}
    * */
    @Override
    public Key floor(Key key) {
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(isEmpty()){
            throw new NoSuchElementException("table may not be empty");
        }
        Node floorNode = floor(root, key);
        if(floorNode == null){
            return null;
        }
        return floorNode.key;
    }

    /*
    * in the subtree with root {@code node)
    * return node that contains the largest key less than or equal to {@code key}
    * */
    private Node floor(Node node, Key key){
        if(node == null){
            return null;
        }
        int comparison = key.compareTo(node.key);
        // this is node we are looking for
        if(comparison == 0){
            return node;
        }
        // we have to look in left subtree
        // key < node.key
        if(comparison < 0){
            return floor(node.leftNode, key);
        }
        // key > node.key
        // maybe current node is the we one we are looking for
        // -> check if we can find a larger key still smaller than key
        Node right = floor(node.rightNode, key);
        if(right == null){return node;}
        return right;




    }

    /*
     * return smallest key greater than or equal to {@code key}
     * */
    @Override
    public Key ceiling(Key key) {
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(isEmpty()){
            throw new NoSuchElementException("table may not be empty");
        }
        Node ceilingNode = ceiling(root, key);
        if(ceilingNode == null){
            return null;
        }
        return ceilingNode.key;
    }

    /*
     * in the subtree with root {@code node)
     * return node that contains the smallest key greater than or equal to {@code key}
     * */
    private Node ceiling(Node node, Key key){
        if(node == null){
            return null;
        }
        int comparison = key.compareTo(node.key);
        // this is node we are looking for
        if(comparison == 0){
            return node;
        }
        // we have to look in right subtree
        // key > node.key
        if(comparison > 0){
            return floor(node.rightNode, key);
        }
        // key < node.key
        // maybe current node is the we one we are looking for
        // -> check if we can find a smaller key still larger than key
        Node left = floor(node.leftNode, key);
        if(left == null){return node;}
        return left;
    }


    @Override
    public Iterable<Key> keys() {
        List<Key> listOfKeys = new ArrayList<>();
        // if empty min(), max() will throw exception
        if(isEmpty()){
            return listOfKeys;
        }
        keys(listOfKeys, root, min(), max());

        return listOfKeys;
    }

    private void keys(List<Key> list, Node node, Key low, Key high){
        if (node == null){return;}
        int compareToLow = low.compareTo(node.key);
        int compareToHigh = high.compareTo(node.key);

        // as long as low < node.key we go deeper to the left
        // until we find a node such that low <= node.key and node.key <= high
        if (compareToLow <0){
            keys(list, node.leftNode, low, high);
        }
        // low <= node.key and node.key <= high
        // add key
        if(compareToLow <= 0 && compareToHigh >=0){
            list.add(node.key);
        }
        // as long as high > node.key we go deeper to the right
        if(compareToHigh > 0){
            keys(list, node.rightNode, low, high);
        }
    }


}
