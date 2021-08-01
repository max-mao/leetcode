//https://leetcode.com/problems/unique-binary-search-trees-ii/
//Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
//
//
//
//Example 1:
//
//
//Input: n = 3
//Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

//DFS:
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
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        return helper(1, n);
    }

    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        if (start == end) {
            TreeNode root = new TreeNode(start);
            res.add(root);
            return res;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftNodes = helper(start, i -1);
            List<TreeNode> rightNodes = helper(i+1, end);
            for (TreeNode lnode : leftNodes) {
                for (TreeNode rnode : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    res.add(root);
                }
            }
        }

        return res;
    }
}

//dp: https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/31493/Java-Solution-with-DP
class Solution {
    public List<TreeNode> generateTrees(int n) {
        //dp[i] store all the BST for i
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<TreeNode>();
        dp[0].add(null);

        for (int i = 1; i <= n; i++) {
            dp[i] = new ArrayList<TreeNode>();
            for (int j = 1; j <= i; j++) {
                ArrayList<TreeNode> leftNodes = dp[j-1];
                ArrayList<TreeNode> rightNodes = dp[i-j];
                for (TreeNode lnode : leftNodes) {
                    for (TreeNode rnode : rightNodes) {
                        TreeNode root = new TreeNode(j);
                        root.left = lnode;
                        root.right = clone(rnode, j);
                        dp[i].add(root);
                    }
                }
            }
        }

        return dp[n];

    }

    private TreeNode clone(TreeNode node, int offset) {
        if (node == null) {
            return null;
        }
        TreeNode new_node = new TreeNode(node.val + offset);
        new_node.left = clone(node.left, offset);
        new_node.right = clone(node.right, offset);

        return new_node;
    }
}