package problems.leetcode.leetcode21_40;

/*
* Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list.
* If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
*
* Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
* */

import problems.leetcode.ListNode;

public class ReverseKNodes {

    public static ListNode reverseKGroup(ListNode head, int k) {

        // first check if list has at least k nodes
        int counter = 0;
        ListNode pointer = head;
        while(pointer != null && counter < k){
            counter++;
            pointer = pointer.next;
        }

        // not enough nodes -> we return list as it is
        if(counter < k){
            return head;
        }

        // else counter == k
        ListNode current = head;
        ListNode previous = null;
        ListNode next;
        while(current != null && counter >= 1){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            counter--;
        }

        // end of sublist is in head
        if(current != null) {
            head.next = reverseKGroup(current, k);
        }

        // new head is now in previous!
        return previous;

    }
}
