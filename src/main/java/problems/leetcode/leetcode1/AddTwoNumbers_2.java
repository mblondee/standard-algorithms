package problems.leetcode.leetcode1;

/*
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * definition ListNode given
 *
 * */


/*
* time complexity: O(max(m,n)) with m and n the resp lengths of l1 and l2
* space complexity: O(max(m,n)) the length of the new list is at most max(m,n) + 1
* */

import problems.leetcode.ListNode;

public class AddTwoNumbers_2 {


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException();
        }

        int rest;
        int sum;
        if (l1.val + l2.val > 9) {
            sum = l1.val + l2.val - 10;
            rest = 1;
        } else {
            sum = l1.val + l2.val;
            rest = 0;
        }

        ListNode result = new ListNode(sum);

        ListNode current = result;

        while (l1.next != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next;
            if (l1.val + l2.val + rest > 9) {
                sum = l1.val + l2.val + rest - 10;
                rest = 1;
            } else {
                sum = l1.val + l2.val + rest;
                rest = 0;
            }

            ListNode next = new ListNode(sum);
            current.next = next;
            current = next;
        }

        while (l1.next != null) {
            l1 = l1.next;
            if (l1.val + rest > 9) {
                sum = l1.val + rest - 10;
                rest = 1;
            } else {
                sum = l1.val + rest;
                rest = 0;
            }
            ListNode next = new ListNode(sum);
            current.next = next;
            current = next;
        }


        while (l2.next != null) {
            l2 = l2.next;
            if (l2.val + rest > 9) {
                sum = l2.val + rest - 10;
                rest = 1;
            } else {
                sum = l2.val + rest;
                rest = 0;
            }
            ListNode next = new ListNode(sum);
            current.next = next;
            current = next;
        }

        if (rest != 0) {
            ListNode next = new ListNode(rest);
            current.next = next;
        }


        return result;
    }
}
