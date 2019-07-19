package problems.leetcode.leetcode81_100;

/*Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.*/

import problems.leetcode.ListNode;

public class RemoveDuplicatesSortedListII_82 {

    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return head;
        }
        //dummy head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //pointers
        ListNode previous = dummy; // previous node that is a node to be kept
        ListNode current = dummy.next; // checking if current has to be kept
        ListNode next = dummy.next; // pointer used for checking if there a duplicates of current

        while(next != null){
            while(next != null && current.val == next.val){
                next = next.next;
            }
            // if next has moved more than once there are duplicates and current-next (next not included) are nodes to be discarded
            if(current.next!=next){
                previous.next = next;
                current = next;
            }
            else{
                previous = current;
                current = next;
            }
        }


        return dummy.next;

    }
}
