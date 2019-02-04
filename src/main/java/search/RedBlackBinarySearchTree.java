package search;

/*
 * Symbol table (associative array abstraction) implementation using a left leaning red-black binary search tree
 *
 * red-black binary search tree:
 * 1) red links lean left
 * 2) no node has two red links connected to it
 * 3) every path from the root to a null link has the same number of black links
 *
 *
 * insert, search: 2*log_2 N (after N inserts height of tree is at most 2*log_2 N)
 * */

import java.util.NoSuchElementException;

//TODO: implement delete

public class RedBlackBinarySearchTree <Key extends Comparable<Key>, Value> implements Search<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root; // root of the tree

    private class Node{
        private Key key;
        private Value value;
        private Node leftNode; //left subtree
        private Node rightNode; //right subtree
        private int size; //total number of nodes
        private boolean colorParentLink; //color of parent link

        public Node(Key key, Value value, int size, boolean color){
            this.key = key;
            this.value = value;
            this.size = size;
            colorParentLink = color;
        }
    }

    /***************************************
     * helper functions for nodes
     ************************************************** */

    private boolean isRed(Node node){
        if (node == null){
            return false;
        }
        return node.colorParentLink == RED;
    }

    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.size;
    }

    /***************************************
     * helper functions for red-black binary search trees
     ************************************************** */

    /*
    * make a right leaning red link lean to the left
    * */
    private Node rotateLeft(Node node){
        //right node must become new root of subtree
        Node newRoot = node.rightNode;
        // rightNode of node becomes leftNode of newRoot
        node.rightNode = newRoot.leftNode;
        // node becomes leftNode of new root
        newRoot.leftNode = node;
        // update colors
        // color of newRoot: color of original node
        newRoot.colorParentLink = node.colorParentLink;
        // color of original node becomes red
        node.colorParentLink = RED;
        //update sizes
        newRoot.size = node.size;
        node.size = 1 + size(node.leftNode) + size(node.rightNode);
        return newRoot;
    }

    /*
     * make a left leaning red link lean to the right
     * */
    private Node rotateRight(Node node){
        //left node must become new root of subtree
        Node newRoot = node.leftNode;
        // leftNode of node becomes rightNode of newRoot
        node.leftNode = newRoot.rightNode;
        // node becomes rightNode of new root
        newRoot.rightNode = node;
        // update colors
        // color of newRoot: color of original node
        newRoot.colorParentLink = node.colorParentLink;
        // color of original node becomes red
        node.colorParentLink = RED;
        //update sizes
        newRoot.size = node.size;
        node.size = 1 + size(node.leftNode) + size(node.rightNode);
        return newRoot;
    }

    /*
    * flip colors of a node and its two children
    * */
    private void flipColors(Node node){
        node.colorParentLink = !node.colorParentLink;
        node.leftNode.colorParentLink = !node.leftNode.colorParentLink;
        node.rightNode.colorParentLink = !node.rightNode.colorParentLink;
    }

    /*
     * restore balance (e.g. after deleting)
     * */
    private Node restoreBalance(Node node){
        // make subtree at node a red-black binary search tree
        //if the right link is red and the left is not, rotate left
        if(isRed(node.rightNode) && !isRed(node.leftNode)){
            node = rotateLeft(node);
        }
        //if left link and its left link are both red, rotate right
        if(isRed(node.leftNode) && isRed(node.leftNode.leftNode)){
            node = rotateRight(node);
        }
        // if left and right links are both red, flip colors
        if(isRed(node.leftNode) && isRed(node.rightNode)){
            flipColors(node);
        }
        //update size
        node.size = 1 + size(node.leftNode) + size(node.rightNode);
        return node;
    }

    /***************************************
     * basic operations red-black binary search trees
     ************************************************** */

    /*
    * return number of key-value pairs
    * */
    @Override
    public int getSize() {
        return size(root);
    }

    /*
     * is table empty?
     * */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /***************************************
     * search operations red-black binary search trees
     ************************************************** */

    /*
     * return value for {@code key}
     * */
    @Override
    public Value get(Key key) {
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

    /***************************************
     * insert operations red-black binary search trees
     ************************************************** */

    /*
     * insert key-value pair with {@code key} and {@code value} in tree
     * */
    @Override
    public void put(Key key, Value value) {
        if(key == null){throw new IllegalArgumentException("key may not be null");}
        if(value == null){return;}
        // replace root with tree in which key-value is inserted
        root = put(root, key, value);
        // color root is always black
        root.colorParentLink = BLACK;
    }

    /*
     * insert key-value pair with {@code key} in subtree with root {@code node}
     * and return new subtree
     * */
    private Node put(Node node, Key key, Value value){
        //if subtree is empty create new node
        if(node == null){return new Node(key, value, 1, RED);}
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

        // make subtree at node a red-black binary search tree
        //if the right link is red and the left is not, rotate left
        if(isRed(node.rightNode) && !isRed(node.leftNode)){
            node = rotateLeft(node);
        }
        //if left link and its left link are both red, rotate right
        if(isRed(node.leftNode) && isRed(node.leftNode.leftNode)){
            node = rotateRight(node);
        }
        // if left and right links are both red, flip colors
        if(isRed(node.leftNode) && isRed(node.rightNode)){
            flipColors(node);
        }
        //update size
        node.size = 1 + size(node.leftNode) + size(node.rightNode);
        return node;
    }

    /***************************************
     * delete operations red-black binary search trees
     ************************************************** */

    @Override
    public void delete(Key key) {

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
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }
}
