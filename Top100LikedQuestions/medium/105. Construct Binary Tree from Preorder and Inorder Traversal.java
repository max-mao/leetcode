//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
//Given preorder and inorder traversal of a tree, construct the binary tree.
//
//        Note:
//        You may assume that duplicates do not exist in the tree.
//
//        For example, given
//
//        preorder = [3,9,20,15,7]
//        inorder = [9,3,15,20,7]
//        Return the following binary tree:
//
//        3
//        / \
//        9  20
//        /  \
//        15   7

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return helper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    private TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == preorder[preStart]) {
                TreeNode left = helper(preorder, preStart+1, preStart+i-inStart, inorder, inStart, i-1);
                TreeNode right = helper(preorder, preStart+i-inStart+1, preEnd, inorder, i+1, inEnd);
                root.left = left;
                root.right = right;
                break;
            }
        }

        return root;
    }
}