package problems.leetcode.leetcode1;

/*
* Given a linked list, remove the n-th node from the end of list and return its head.
*
* Note:

Given n will always be valid.
* */

/*impossible to remove first element because of the call by value!*/

import problems.leetcode.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class RemoveNthNode_19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return null;
        }

        if(head.next == null){
            // suppose input n is correct
            head = null;
            return head;
        }

        ListNode current = head;
        ListNode findLast = head;

        while(n > 0){
            findLast = findLast.next;
            n--;
        }

        if(findLast == null){
            // we have to remove the head
            head = head.next;
            return head;
        }

        while(findLast.next != null){
            current = current.next;
            findLast = findLast.next;
        }
        current.next = current.next.next;


        return head;
    }
}
