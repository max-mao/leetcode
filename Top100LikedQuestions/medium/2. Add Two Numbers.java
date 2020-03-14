//https://leetcode.com/problems/add-two-numbers/
//
//You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
//        You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//        Example:
//
//        Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//        Output: 7 -> 0 -> 8
//        Explanation: 342 + 465 = 807.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)  {
            return l2;
        }
        if (l2 == null)  {
            return l1;
        }

        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while(l1 != null && l2 != null) {
            int value = (l1.val + l2.val +carry) % 10;
            carry = (l1.val + l2.val +carry) / 10;
            cur.next = new ListNode(value);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int value = (l1.val +carry) % 10;
            carry = (l1.val +carry) / 10;
            cur.next = new ListNode(value);
            cur = cur.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int value = (l2.val +carry) % 10;
            carry = (l2.val +carry) / 10;
            cur.next = new ListNode(value);
            cur = cur.next;
            l2 = l2.next;
        }

        //注意最后要检查一下carry 为不为0，如果不为0，要将carry也加入结果
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }

        return dummy.next;
    }
}