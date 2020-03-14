//https://leetcode.com/problems/sort-list/
//Sort a linked list in O(n log n) time using constant space complexity.
//
//        Example 1:
//
//        Input: 4->2->1->3
//        Output: 1->2->3->4

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode cur = head;
        while (fast != null && fast.next != null) {
            cur = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        cur.next = null;
        ListNode right = sortList(slow);
        ListNode left = sortList(head);

        return mergeList(right, left);
    }

    private ListNode mergeList(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                cur.next = n1;
                n1 = n1.next;
            } else {
                cur.next = n2;
                n2 = n2.next;
            }
            cur = cur.next;
        }

        if (n1 == null) {
            cur.next = n2;
        }

        if (n2 == null) {
            cur.next = n1;
        }

        return dummy.next;
    }
}