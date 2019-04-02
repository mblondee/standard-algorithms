package datacompression;

/*
* compress and decompress a string using the Huffman algorithm
* lossless datacompression that assigns variable length codes to input characters based on the frequencies of corresponding characters
* */

import sorting.priorityqueue.MinPQ;

import java.util.BitSet;

public class Huffman {

    // alphabet size extended ASCII
    private static int RADIX = 256;
    private NodeHuffman tree;
    private String[] encodeTable; // per char the encoding
    private String stringToCompress;

    /*
    * initialize tree
    * */
    public Huffman(String input){
        // frequency counts
        int[] frequencies = new int[RADIX];
        for(int i = 0; i < input.length(); i++){
            frequencies[input.charAt(i)] ++;
        }

        //build Huffman tree
        tree = buildTree(frequencies);


        // build encode table
        encodeTable = new String[RADIX];
        buildCode(encodeTable, tree, "");

        stringToCompress = input;



    }


    public String compress(){

        // encode input string using encode table
        String output = "";
        for(int i = 0; i < stringToCompress.length(); i++){
            output += encodeTable[stringToCompress.charAt(i)];
        }
        return output;
    }

    public byte[] compressToByte(){
        // encode input string using encode table
        String output = "";
        for(int i = 0; i < stringToCompress.length(); i++) {
            output += encodeTable[stringToCompress.charAt(i)];
        }
        BitSet bitset = new BitSet(output.length());
        for(int i = 0; i < output.length(); i++){
            bitset.set(i, output.charAt(i) == '1');
        }
        return bitset.toByteArray();

    }

    public String decompress(String input){
        NodeHuffman node = tree;
        String output = "";
        for(int i = 0; i < input.length(); i++){
            if(node == null){
                throw new IllegalArgumentException("not a correct input");
            }
            if(input.charAt(i) !='0' && input.charAt(i) != '1'){
                throw new IllegalArgumentException("not a correct input");
            }
            if(input.charAt(i) == '0'){
                node = node.getLeftNode();
            }
            else{
                node = node.getRightNode();
            }

            if(node.isLeaf()){
                output += node.getCh();
                node = tree;
            }
        }
        return output;
    }


    /*
    * build a Huffman tree given the frequencies
    * */
    private static NodeHuffman buildTree(int[] freq){

        // initialize min pq
        // put all chars with a frequency > 0 into the pq
        MinPQ<NodeHuffman> pq = new MinPQ<>();
        for(char i = 0; i < RADIX; i++){
            if(freq[i] > 0){
                pq.insert(new NodeHuffman(i, freq[i], null, null));
            }
        }

        //merge two smallest trees

        //special case:
        //if there is only one character we make a dummy node with frequency 0
        if(pq.size() == 1){
            NodeHuffman nodeLeft = new NodeHuffman('\0', 0, null, null);
            NodeHuffman nodeRight = pq.delMin();
            NodeHuffman parent = new NodeHuffman('\0', nodeLeft.getFrequency() + nodeRight.getFrequency(),
                    nodeLeft, nodeRight);
            return parent;
        }

        // if there are at least 2 nodes (2 different chars) we get the 2 with the least frequency and combine them into
        // a new node with the sum of the frequencies and at this new node to the pq
        while(pq.size() > 1){
            NodeHuffman nodeLeft = pq.delMin();
            NodeHuffman nodeRight = pq.delMin();
            NodeHuffman parent = new NodeHuffman('\0', nodeLeft.getFrequency() + nodeRight.getFrequency(),
                    nodeLeft, nodeRight);
            pq.insert(parent);
        }


        // now only one node left in pq
        return pq.delMin();
    }

    /*
    * build encoding table: going left in the tree is adding a 0
    * going right is adding a 1
    * to the encoding
    * */
    private static void buildCode(String[] table, NodeHuffman node, String s){
        if(!node.isLeaf()){
            buildCode(table, node.getLeftNode(), s+'0');
            buildCode(table, node.getRightNode(), s+'1');
        }
        // we have found a char if we are in a leaf
        else{
            table[node.getCh()] = s;
        }
    }








}
