package problems.leetcode.leetcode4;


/*Given a linked list, rotate the list to the right by k places, where k is non-negative.*/

// find last node, make circular list and compute length
// move pointer last node length - (k%length) -> this will be new last node of list and head will be next one

import problems.leetcode.ListNode;

public class RotateList_61 {
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return head;
        }

        ListNode last = head;
        int length = 1;
        // find last element and compute length of list
        while(last.next != null){
            last = last.next;
            length++;
        }
        // circular list
        last.next = head;


        // break should come at length - k
        // or if k > length length - (k%length)
        // move last length - (k%length)
        int i = length - (k% length);
        while(i > 0){
            last = last.next;
            i--;
        }
        head = last.next;
        last.next = null;


        return head;



    }
}
