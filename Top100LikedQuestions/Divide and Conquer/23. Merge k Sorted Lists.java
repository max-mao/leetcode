//https://leetcode.com/problems/merge-k-sorted-lists/
//You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//Merge all the linked-lists into one sorted linked-list and return it.
//
//
//
//Example 1:
//
//Input: lists = [[1,4,5],[1,3,4],[2,6]]
//Output: [1,1,2,3,4,4,5,6]
//Explanation: The linked-lists are:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//merging them into one sorted list:
//1->1->2->3->4->4->5->6

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ArrayList<ListNode> listsCopy = new ArrayList<>();
        for (ListNode node : lists) {
            listsCopy.add(node);
        }


        while (listsCopy.size() > 1) {
            ArrayList<ListNode> newLists = new ArrayList<>();
            int len = listsCopy.size();
            if (len % 2 == 0) {
                for (int i = 0; i < len; i+=2) {
                    ListNode node = mergeTwoLists(listsCopy.get(i), listsCopy.get(i+1));
                    newLists.add(node);
                }
            } else {
                for (int i = 0; i < len-1; i+=2) {
                    ListNode node = mergeTwoLists(listsCopy.get(i), listsCopy.get(i+1));
                    newLists.add(node);
                }
                newLists.add(listsCopy.get(len-1));
            }
            listsCopy = newLists;
        }
        return listsCopy.get(0);
    }

    private ListNode  mergeTwoLists(ListNode node1, ListNode node2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                tail.next = node1;
                tail = tail.next;
                node1 = node1.next;

            } else {
                tail.next = node2;
                tail = tail.next;
                node2 = node2.next;

            }
        }

        if (node1 != null) {
            tail.next = node1;
        }
        if (node2 != null) {
            tail.next = node2;
        }

        return head.next;
    }

}

//solution2:
class Solution {
    public class ListNodeComparator implements Comparator<ListNode> {
        public int compare(ListNode n1, ListNode n2) {
            return n1.val - n2.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, new ListNodeComparator());
        for (int i = 0; i < lists.length; i ++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            tail.next = cur;
            tail = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }

        return dummy.next;
    }
}

//solution 3:
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return mergeSort(lists, 0, lists.length - 1);
    }

    private ListNode mergeSort(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = (start + end)/2;
        ListNode left = mergeSort(lists, start, mid);
        ListNode right = mergeSort(lists, mid+1, end);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                tail = tail.next;
                left = left.next;
            } else {
                tail.next = right;
                tail = tail.next;
                right = right.next;
            }
        }
        if (left == null) {
            tail.next = right;
        }
        if (right == null) {
            tail.next = left;
        }
        return dummy.next;
    }
}