//https://leetcode.com/problems/closest-binary-search-tree-value-ii/
//Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
//
//        Note:
//
//        Given target value is a floating point.
//        You may assume k is always valid, that is: k ≤ total nodes.
//        You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
//        Example:
//
//        Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
//
//        4
//        / \
//        2   5
//        / \
//        1   3
//
//        Output: [4,3]

//solution: https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/70511/AC-clean-Java-solution-using-two-stacks
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        dfs(root, res, target, k);

        return res;
    }

    private void dfs(TreeNode root, LinkedList<Integer> res, double target, int k) {
        if (root == null) {
            return;
        }

        dfs(root.left, res, target, k);

        if (res.size() == k) {
            if (Math.abs(res.getFirst() - target) > Math.abs(root.val - target)) {
                res.removeFirst();
            } else {
                return;
            }
        }

        res.add(root.val);
        dfs(root.right, res, target, k);
    }
}