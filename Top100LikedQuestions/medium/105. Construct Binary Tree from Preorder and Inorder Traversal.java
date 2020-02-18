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
        return helper(preorder, inorder, 0, 0, inorder.length -1);
    }

    private TreeNode helper(int[] preorder,
                            int[] inorder,
                            int index,
                            int in_start,
                            int in_end) {
        if (index > preorder.length -1 || in_start > in_end) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[index]);

        for (int i = in_start; i <= in_end; i++) {
            if (inorder[i] != preorder[index]) {
                continue;
            } else {
                int left_len = i - in_start;
                TreeNode left = helper(preorder, inorder, index+1, in_start, i-1);
                TreeNode right = helper(preorder, inorder, index+left_len+1, i+1, in_end);
                root.left = left;
                root.right = right;
                break;
            }
        }

        return root;
    }
}