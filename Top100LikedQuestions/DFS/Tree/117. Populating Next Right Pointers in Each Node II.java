//https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
//
//Given a binary tree
//
//        struct Node {
//        int val;
//        Node *left;
//        Node *right;
//        Node *next;
//        }
//        Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
//        Initially, all next pointers are set to NULL.
//

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
//solution: https://www.youtube.com/watch?v=vy2mnT3TEXQ&t=519s
class Solution {
    public Node connect(Node root) {
        Node parent = root;
        Node child = null;
        Node childHead = null;

        while (parent != null) {
            while (parent != null) {
                if (parent.left != null) {
                    if (childHead == null) {
                        childHead = parent.left;
                    } else {
                        child.next = parent.left;
                    }
                    child = parent.left;
                }

                if (parent.right != null) {
                    if (childHead == null) {
                        childHead = parent.right;
                    } else {
                        child.next = parent.right;
                    }
                    child = parent.right;
                }
                parent = parent.next;
            }
            parent = childHead;
            child = null;
            childHead = null;
        }

        return root;
    }
}