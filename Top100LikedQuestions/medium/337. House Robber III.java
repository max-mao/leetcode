//https://leetcode.com/problems/house-robber-iii/
//The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
//
//        Determine the maximum amount of money the thief can rob tonight without alerting the police.
//
//        Example 1:
//
//        Input: [3,2,3,null,3,null,1]
//
//        3
//        / \
//        2   3
//        \   \
//        3   1
//
//        Output: 7
//        Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

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
    class ReturnType {
        int rob_cur;
        int not_rob_cur;
        public ReturnType(int rob_cur, int not_rob_cur) {
            this.rob_cur = rob_cur;
            this.not_rob_cur = not_rob_cur;
        }
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(helper(root).rob_cur, helper(root).not_rob_cur);
    }

    private ReturnType helper(TreeNode node) {
        if (node == null) {
            return new ReturnType(0, 0);
        }

        ReturnType left = helper(node.left);
        ReturnType right = helper(node.right);

        int rob_cur = left.not_rob_cur + node.val + right.not_rob_cur;
        int not_rob_cur = Math.max(left.not_rob_cur, left.rob_cur) +
                Math.max(right.not_rob_cur, right.rob_cur);

        return new ReturnType(rob_cur, not_rob_cur);
    }
}