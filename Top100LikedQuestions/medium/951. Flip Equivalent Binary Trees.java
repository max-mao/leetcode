//https://leetcode.com/problems/flip-equivalent-binary-trees/
//For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
//
//        A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
//
//        Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivelent or false otherwise.
//
//
//
//        Example 1:
//
//        Flipped Trees Diagram
//        Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
//        Output: true
//        Explanation: We flipped at nodes with values 1, 3, and 5.

//solution: https://www.youtube.com/watch?v=0vyWW_dH5RI&ab_channel=scottynoshotty
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }

        if (!hasSameChild(root1, root2)) {
            TreeNode temp = root1.left;
            root1.left = root1.right;
            root1.right = temp;
        }

        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    }

    private boolean hasSameChild(TreeNode root1, TreeNode root2) {
        boolean left = false;
        boolean right = false;
        if (root1.left == null && root2.left == null) {
            left = true;
        } else if (root1.left == null || root2.left == null) {
            left = false;
        } else if (root1.left.val == root2.left.val) {
            left = true;
        }

        if (root1.right == null && root2.right == null) {
            right = true;
        } else if (root1.right == null || root2.right == null) {
            right = false;
        } else if (root1.right.val == root2.right.val) {
            right = true;
        }

        return left && right;
    }
}