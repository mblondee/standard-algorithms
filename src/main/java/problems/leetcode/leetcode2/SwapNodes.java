package problems.leetcode.leetcode2;

/*
* Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.
*
* Given 1->2->3->4, you should return the list as 2->1->4->3
* */

import problems.leetcode.ListNode;

public class SwapNodes {
    public static ListNode swapPairs(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }

        ListNode previous = null;
        ListNode first = head;
        ListNode second = head.next;

        head = second;

        while(second.next != null){
            ListNode next = second.next;
            second.next = first;
            first.next = next;

            if(previous != null){
                previous.next = second;
            }

            if(next.next != null){
                previous = first;
                first = next;
                second = next.next;
            }
            else{
                return head;
            }
        }

        // second.next == null
        if(previous != null) {
            previous.next = second;
        }
        second.next = first;
        first.next = null;



        return head;

    }
}
