//https://leetcode.com/problems/add-two-numbers-ii/
//You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
//You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//Follow up:
//What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
//
//Example:
//
//Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 8 -> 0 -> 7


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }

        ListNode head = null;
        int carry = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            ListNode cur1 = s1.pop();
            ListNode cur2 = s2.pop();
            int value = cur1.val + cur2.val + carry;
            ListNode new_node = new ListNode(value%10);
            carry = value /10;
            new_node.next = head;
            head = new_node;
        }

        while (!s1.isEmpty()) {
            ListNode cur = s1.pop();
            int value = cur.val + carry;
            ListNode new_node = new ListNode(value%10);
            carry = value /10;
            new_node.next = head;
            head = new_node;
        }

        while (!s2.isEmpty()) {
            ListNode cur = s2.pop();
            int value = cur.val + carry;
            ListNode new_node = new ListNode(value%10);
            carry = value /10;
            new_node.next = head;
            head = new_node;
        }

        if (carry != 0)  {
            ListNode new_node = new ListNode(carry);
            new_node.next = head;
            head = new_node;
        }
        return head;
    }
}