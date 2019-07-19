package problems.leetcode.leetcode81_100;

/*Given a sorted linked list, delete all duplicates such that each element appear only once.*/

import problems.leetcode.ListNode;

public class RemoveDuplicatesSortedList_83 {

    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            // list has at most one node
            return head;
        }

        ListNode pointer = head; // the node we keep
        ListNode next = head.next; // question: do we have to included next?

        while(next != null){
            if(next.val == pointer.val){
                //next has to be skipped
                pointer.next = next.next; // might be null
                next = next.next;
            }
            else{
                pointer = pointer.next;
                next = next.next;
            }

        }
        return head;



    }
}
