//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
//You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
//Recursion:


class Solution {
    public Node connect(Node root) {
        if (root == null) return null;

        if(root.left != null) {
            root.left.next = root.right;
        }
        if(root.next !=null && root.right != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;
    }
}

// Iterative:
class Solution {
    public Node connect(Node root) {
        Node leftMost = root;

        while (leftMost != null) {
            Node cur = leftMost;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.next != null && cur.right != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            leftMost = leftMost.left;
        }

        return root;
    }
}



