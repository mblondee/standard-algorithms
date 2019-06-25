package problems;

// data structure for nodes in a binary tree

public class NodeBinaryTree<Item> {

    private Item item;
    private NodeBinaryTree left;
    private NodeBinaryTree right;

    public NodeBinaryTree(Item item){
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public NodeBinaryTree getLeft() {
        return left;
    }

    public NodeBinaryTree getRight() {
        return right;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setLeft(NodeBinaryTree left) {
        this.left = left;
    }

    public void setRight(NodeBinaryTree right) {
        this.right = right;
    }
}
