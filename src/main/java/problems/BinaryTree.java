package problems;

/*
 * is a binary tree a subtree of another binary tree?
 *
 * solution 1:
 * O(mn) where m and n are nodes in resp. trees
 * hence O(n^2) since areIndentical() will visit at most n nodes
 *
 * solution 2:
 * a binary tree is uniquely identified by a combination of inorder and preorder traversal
 * (can be shown by induction on number of nodes in tree)
 * O(m+n) m and n are nodes in resp. trees
 * hence O(max(n,m))
 * */


import java.util.ArrayList;
import java.util.List;

public class BinaryTree<Item> {

    private NodeBinaryTree root;

    private List<Item> preOrder;
    private List<Item> inOrder;


    public NodeBinaryTree getRoot() {
        return root;
    }

    public void setRoot(NodeBinaryTree<Item> root) {
        this.root = root;
    }


    /*SOLUTION 1*/

    /*
    * is otherTree a subtree of this tree?
    * */
    public boolean hasSubtree(BinaryTree otherTree){
        return isSubtree(root, otherTree.getRoot());
    }

    /*
    * is the tree rooted at otherNode a subtree of the tree rooted at node?
    * */

    private static  boolean isSubtree(NodeBinaryTree node, NodeBinaryTree otherNode){
        if(otherNode == null){
            return true;
        }
        else if(node == null){ // otherNode is not null and node is null
            return false;
        }
        else if(areIndentical(node, otherNode)){
            return true;
        }
        else{
            return isSubtree(node.getLeft(), otherNode) || isSubtree(node.getRight(), otherNode);
        }
    }



    /*
    * are trees with roots node and otherNode the same trees?
    * */
    private static boolean areIndentical(NodeBinaryTree node, NodeBinaryTree otherNode){
        if(node == null && otherNode == null){
            return true;
        }
        else if(node == null || otherNode == null){
            return false;
        }
        else{
            return node.getItem().equals(otherNode.getItem()) &&
                    areIndentical(node.getLeft(), otherNode.getLeft()) && areIndentical(node.getRight(), otherNode.getRight());
        }
    }




    /*SOLUTION 2*/

    /*
     * is otherTree a subtree of this tree?
     * */
    public boolean hasSubtree1(BinaryTree otherTree){
        return isSubList(getInOrder(), otherTree.getInOrder()) && isSubList(getPreOrder(), otherTree.getPreOrder());
    }


    // setting inorder list
    private static <Item> void inOrderTraverse(NodeBinaryTree<Item> node, List<Item> listOfNodes){
        if(node == null){
            return;
        }
        inOrderTraverse(node.getLeft(), listOfNodes);
        listOfNodes.add(node.getItem());
        inOrderTraverse(node.getRight(), listOfNodes);
    }

    private void setInOrder(){
        inOrder = new ArrayList<>();
        inOrderTraverse(root, inOrder);
    }

    public List<Item> getInOrder(){
        if(inOrder == null){
            setInOrder();
        }
        return inOrder;
    }

    //setting preorder list
    private static <Item> void preOrderTravers(NodeBinaryTree<Item> node, List<Item> listOfNodes){
        if(node == null){
            return;
        }
        listOfNodes.add(node.getItem());
        preOrderTravers(node.getLeft(), listOfNodes);
        preOrderTravers(node.getRight(), listOfNodes);
    }

    private void setPreOrder(){
        preOrder = new ArrayList<>();
        preOrderTravers(root, preOrder);
    }

    public List<Item> getPreOrder(){
        if(preOrder == null){
            setPreOrder();
        }
        return preOrder;
    }

    // is ordered list sublist of another ordered list?
    private static <Item> boolean isSubList(List<Item> list1, List<Item> list2){
        if(list2 == null){
            return true;
        }
        if(list1 == null){
            return false;
        }
        int index1 = 0;
        int index2 = 0;
        while(index1 < list1.size() && index2<list2.size()){
            if(list2.get(index2).equals(list1.get(index1))){
                index1++;
                index2++;
            }
            else{
                index1++;
                index2 = 0;
            }
        }

        return index2 == list2.size();
    }





    public static void main(String[] args){
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.setRoot(new NodeBinaryTree(new Integer(26)));
        binaryTree.getRoot().setLeft(new NodeBinaryTree(new Integer(10)));
        binaryTree.getRoot().setRight(new NodeBinaryTree(new Integer(3)));
        binaryTree.getRoot().getLeft().setLeft(new NodeBinaryTree(new Integer(4)));
        binaryTree.getRoot().getLeft().setRight(new NodeBinaryTree(new Integer(6)));
        binaryTree.getRoot().getLeft().getLeft().setRight(new NodeBinaryTree(new Integer(30)));

        binaryTree.getRoot().getRight().setRight(new NodeBinaryTree(new Integer(3)));

        BinaryTree<Integer> subTree = new BinaryTree<>();
        subTree.setRoot(new NodeBinaryTree(new Integer(10)));
        subTree.getRoot().setLeft(new NodeBinaryTree(new Integer(4)));
        subTree.getRoot().getLeft().setRight(new NodeBinaryTree(new Integer(30)));
        subTree.getRoot().setRight(new NodeBinaryTree(new Integer(6)));

        //System.out.println(binaryTree.hasSubtree(subTree)); //true
        //System.out.println(subTree.hasSubtree(binaryTree)); // false

        System.out.println(binaryTree.hasSubtree1(subTree)); //true
        System.out.println(subTree.hasSubtree1(binaryTree)); // false


        BinaryTree<Integer> emptyTree = new BinaryTree<>();
        //System.out.println(binaryTree.hasSubtree(emptyTree)); //true
        //System.out.println(emptyTree.hasSubtree(emptyTree)); //true
        //System.out.println(emptyTree.hasSubtree(binaryTree)); // false

        System.out.println(binaryTree.hasSubtree1(emptyTree)); //true
        System.out.println(emptyTree.hasSubtree1(emptyTree)); //true
        System.out.println(emptyTree.hasSubtree1(binaryTree)); // false








/*        for(Integer i : binaryTree.getInOrder()){
            System.out.println(i);
        }

        System.out.println("----");

        for(Integer i : binaryTree.getPreOrder()){
            System.out.println(i);
        }*/
/*        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i<11; i++){
            list1.add(i);
        }
        List<Integer> list2 = new ArrayList<>();
*//*        System.out.println(isSubList(list1, list2)); //true
        System.out.println(isSubList(list2, list1)); // false*//*

        list2.add(2);
        list2.add(3);
        System.out.println(isSubList(list1, list2)); // true

        List<Integer> list3 = new ArrayList<>();
        list3.add(3);
        list3.add(2);
        System.out.println(isSubList(list1, list3)); // false*/







    }
}
