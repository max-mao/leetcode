//https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
//Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
//
//A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.

class Solution {
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return 0;
        }
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        int res = max - min;
        int left = dfs(root.left, min, max);
        int right = dfs(root.right, min, max);

        return Math.max(Math.max(left, right), res);
    }
}