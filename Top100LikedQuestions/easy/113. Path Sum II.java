//https://leetcode.com/problems/path-sum-ii/

//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
//
//        Note: A leaf is a node with no children.
//
//        Example:
//
//        Given the below binary tree and sum = 22,
//
//        5
//        / \
//        4   8
//        /   / \
//        11  13  4
//        /  \    / \
//        7    2  5   1

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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        helper(root, sum, result, new ArrayList<Integer>());
        return result;
    }

    private void helper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.val == sum && root.left == null && root.right == null) {
            list.add(root.val);
            result.add(new ArrayList<>(list));
            list.remove(list.size() -1);
            return;
        }

        if (root.left != null) {
            list.add(root.val);
            helper(root.left, sum - root.val, result, list);
            list.remove(list.size() -1);
        }

        if (root.right != null) {
            list.add(root.val);
            helper(root.right, sum - root.val, result, list);
            list.remove(list.size() -1);
        }
    }
}