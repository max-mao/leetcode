//https://leetcode.com/problems/odd-even-linked-list/
//Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
//
//        You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
//
//        Example 1:
//
//        Input: 1->2->3->4->5->NULL
//        Output: 1->3->5->2->4->NULL

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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddEnd = head;
        ListNode evenHead = head.next;
        ListNode evenEnd = evenHead;

        while (evenEnd != null && evenEnd.next != null) {
            oddEnd.next = evenEnd.next;
            oddEnd = oddEnd.next;
            evenEnd.next = oddEnd.next;
            evenEnd = evenEnd.next;
        }

        oddEnd.next = evenHead;
        return head;
    }
}