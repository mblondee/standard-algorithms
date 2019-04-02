package datacompression;

public class NodeHuffman implements Comparable<NodeHuffman> {

    private char ch;
    private int frequency;
    private NodeHuffman leftNode;
    private NodeHuffman rightNode;

    public NodeHuffman(char ch, int frequency, NodeHuffman leftNode, NodeHuffman rightNode){
        this.ch = ch; // if not a leaf node, ch will be '\0' the default null char
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public char getCh() {
        return ch;
    }

    public int getFrequency() {
        return frequency;
    }

    public NodeHuffman getLeftNode() {
        return leftNode;
    }

    public NodeHuffman getRightNode() {
        return rightNode;
    }

    // is the node a leaf?
    public boolean isLeaf(){
        return leftNode == null && rightNode == null;
    }

    //compare based on the frequency
    @Override
    public int compareTo(NodeHuffman otherNode){
        return this.frequency - otherNode.frequency;
    }
}
