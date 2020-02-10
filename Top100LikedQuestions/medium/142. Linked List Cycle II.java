//https://leetcode.com/problems/linked-list-cycle-ii/
//Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
//
//        To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
//
//        Note: Do not modify the linked list.
//
//
//
//        Example 1:
//
//        Input: head = [3,2,0,-4], pos = 1
//        Output: tail connects to node index 1
//        Explanation: There is a cycle in the linked list, where tail connects to the second node.

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head.next.next;
        ListNode slow = head.next;

        //part 1: find the intersection
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }


        // no intersection
        if (fast == null || fast.next == null) {
            return null;
        }


        // find the entry
        ListNode new_start = head;

        while (new_start != slow) {
            new_start = new_start.next;
            slow = slow.next;
        }

        return new_start;
    }
}