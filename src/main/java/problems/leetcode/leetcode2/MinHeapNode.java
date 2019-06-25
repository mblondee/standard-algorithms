package problems.leetcode.leetcode2;

/*
* utility class for marge k sorted lists
* store: value, original ListNode
* */

import problems.leetcode.ListNode;

public class MinHeapNode {

    int value;
    ListNode node;


    public MinHeapNode(int value, ListNode node){
        this.value = value;
        this.node = node;
    }
}
