package problems.binarytree;

/*
* Binary search tree coding problems
*
* Binary search tree of integers
* 1) insert k into the tree if it is not already present
* 2) is k present in the tree?
* 3) given k, return the biggest element that is less than or equal to k or null if there is no such element
* 4) print out the nodes level by level
* 5) given a bst with only positive integers (>0), is there a dead end, i.e. it is not possible
* to insert any element after that node
* 6) construct a bst from a given preorder traversal
* 7) invert a bst (result is not a bst anymore)
* */


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

    private Node root;
    private int size;

    public BinaryTree(){}

    /*
     * solution to 6): every element in preorder is pushed and popped only once on stack: O(n)
     * */

    public BinaryTree(int[] preorder){
        if(preorder.length == 0){
            throw new IllegalArgumentException();
        }
        this.root = new Node(preorder[0]);
        this.size = preorder.length;
        construct(preorder);
    }


    private void construct(int[] preorder){
        Stack<Node>  nodeStack = new Stack<>();
        nodeStack.push(root);

        for(int i = 1; i<size; i++){
            //looking for node where to add next element in preorder
            Node nodeToAddTo = null;


            // if a right has to be added then we have already added a left one
            // (if there is one because it is preorder -> then we can pop it)

            while(! nodeStack.empty() && preorder[i] > nodeStack.peek().getValue()){
                nodeToAddTo = nodeStack.pop();
            }

            // suppose nodeToAddTo is still null
            // (has to be added as left child, maybe there is a right child, so node cannot be popped of the stack)
            if(nodeToAddTo == null){
                nodeToAddTo = nodeStack.peek();
                Node add = new Node(preorder[i]);
                nodeToAddTo.setLeft(add);
                nodeStack.push(add);
            }

            // has to be added as a right child
            else{
                Node add = new Node(preorder[i]);
                nodeToAddTo.setRight(add);
                nodeStack.push(add);

            }

        }
    }




    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }



    /*
    * solution to 1) O(h) with h the height (expected height log n)
    * */
    public void insert(int k){
        root = insert(k, root);
    }

    private Node insert(int k, Node node){
        if(node == null){
            size++;
            node =  new Node(k);
        }
        else if(node.getValue() == k){
        }
        else if(node.getValue() > k){
            node.setLeft(insert(k, node.getLeft()));
        }
        else{
            node.setRight(insert(k, node.getRight()));
        }
        return node;
    }

    /*
     * solution to 2) O(h) with h the height (expected height log n)
     * */
    public boolean contains(int k){
        return contains(k, root);
    }

    private boolean contains(int k, Node node){
        if(node == null){
            return false;
        }
        if(node.getValue() == k){
            return true;
        }
        if(node.getValue() > k){
            return contains(k, node.getLeft());
        }
        else{
            return contains(k, node.getRight());
        }
    }

    /*
     * solution to 3) O(h) with h the height (expected height log n)
     * */
    public Integer leq(int k){
        return leq(k, root);
    }


    private Integer leq(int k, Node node){
        if(node == null){
            return null;
        }
        if(node.getValue() == k){
            return k;
        }
        if(node.getValue() < k){
            // current node could be the one we are looking for
            Integer test = leq(k, node.getRight());
            if(test == null){
                return node.getValue();
            }
            else{
                return test;
            }
        }
        else{
            return leq(k, node.getLeft());
        }


    }

    /*
     * solution to 4)
     * */
    public void printLevelByLevel(){
        // this is breadth first traversal -> use a queue
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node current = queue.remove();
            System.out.println(current.getValue());
            if(current.getLeft() != null){
                queue.add(current.getLeft());
            }
            if(current.getRight() != null){
                queue.add(current.getRight());
            }
        }


    }

    /*
     * solution to 5)
     * */

    public boolean hasDeadEnd(){
        if(root == null){
            return false;
        }
        HashSet<Integer> allNodes = new HashSet<>();
        HashSet<Integer> leaves = new HashSet<>();

        checkNodes(root, allNodes, leaves);

        for(Integer i : leaves){
            if(i == 1 && allNodes.contains(i+1)){
                return true;
            }
            else if(allNodes.contains(i-1) && allNodes.contains(i+1)){
                return true;
            }
        }
        return false;
    }

    private void checkNodes(Node node, HashSet<Integer> allNodes, HashSet<Integer> leaves){
        // node is never null
        if(node.getRight() == null && node.getLeft() == null){
            leaves.add(node.getValue());
        }
        allNodes.add(node.getValue());
        if(node.getRight() != null){
            checkNodes(node.getRight(), allNodes, leaves);
        }
        if(node.getLeft() != null){
            checkNodes(node.getLeft(), allNodes, leaves);
        }
    }

    /*
     * solution to 7)
     * */
    public void invert(){
        root = invert(root);
    }

    private Node invert(Node node){
        if(node == null){
            return null;
        }
        else{
            Node left = node.getLeft();
            Node right = node.getRight();
            node.setLeft(invert(right));
            node.setRight(invert(left));
            return node;
        }
    }


}
