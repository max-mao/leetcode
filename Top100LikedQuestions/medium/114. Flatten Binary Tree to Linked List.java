//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

//Given a binary tree, flatten it to a linked list in-place.
//
//        For example, given the following tree:
//
//        1
//        / \
//        2   5
//        / \   \
//        3   4   6
//        The flattened tree should look like:
//
//        1
//        \
//        2
//        \
//        3
//        \
//        4
//        \
//        5
//        \
//        6

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode right_temp = root.right;
        TreeNode left_temp = root.left;

        flatten(root.left);
        flatten(root.right);

        root.right = left_temp;
        //记得把left设置为null
        root.left = null;

        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = right_temp;
    }
}