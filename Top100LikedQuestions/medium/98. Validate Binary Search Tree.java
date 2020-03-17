//https://leetcode.com/problems/validate-binary-search-tree/
//Given a binary tree, determine if it is a valid binary search tree (BST).
//
//        Assume a BST is defined as follows:
//
//        The left subtree of a node contains only nodes with keys less than the node's key.
//        The right subtree of a node contains only nodes with keys greater than the node's key.
//        Both the left and right subtrees must also be binary search trees.
//
//
//        Example 1:
//
//        2
//        / \
//        1   3
//
//        Input: [2,1,3]
//        Output: true

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class ReturnType {
    boolean isBST;
    TreeNode max;
    TreeNode min;
    public ReturnType(boolean isBST) {
        this.isBST = isBST;
        max = null;
        min = null;
    }
}

class Solution {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        ReturnType result = helper( root);
        return result.isBST;
    }

    private ReturnType helper(TreeNode root) {
        if (root == null) {
            return new ReturnType(true);
        }

        ReturnType left = helper(root.left);
        ReturnType right = helper(root.right);

        if (!left.isBST || !right.isBST) {
            return new ReturnType(false);
        }

        if (left.max != null) {
            if (left.max.val >= root.val) {
                return new ReturnType(false);
            }
        }

        if (right.min != null) {
            if (right.min.val <= root.val) {
                return new ReturnType(false);
            }
        }

        ReturnType result = new ReturnType(true);
        result.max = right.max == null ? root : right.max;
        result.min = left.min == null ? root : left.min;

        return result;
    }
}