package problems.leetcode.leetcode21_40;

/*
* Merge two sorted linked lists and return it as a new list.
* The new list should be made by splicing together the nodes of the first two lists.
* */

/*
* time complexity: O(n)
* */

import problems.leetcode.ListNode;

public class MergeSortedLists {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0); // result
        ListNode tail = dummyNode; // tail of result to add ListNodes

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            }
            else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if(l1 == null){
            tail.next = l2;
        }
        else{
            tail.next = l1;
        }

        return dummyNode.next;

    }
}
