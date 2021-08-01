//https://leetcode.com/problems/path-sum-iii/

//You are given a binary tree in which each node contains an integer value.
//
//        Find the number of paths that sum to a given value.
//
//        The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
//
//        The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
//
//        Example:
//
//        root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//        10
//        /  \
//        5   -3
//        / \    \
//        3   2   11
//        / \   \
//        3  -2   1
//
//        Return 3. The paths that sum to 8 are:
//
//        1.  5 -> 3
//        2.  5 -> 2 -> 1
//        3. -3 -> 11

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 递归
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    //return the sum where the start node is root
    private int pathSumFrom(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int cur_val = sum - root.val;

        return (cur_val == 0 ? 1 : 0 ) + pathSumFrom(root.left, cur_val) + pathSumFrom(root.right, cur_val);
    }
}

// prefix sum: 很像 Subarray Sum Equals K： https://leetcode.com/problems/subarray-sum-equals-k/
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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        return helper(root, 0, sum, preSum);
    }

    private int helper(TreeNode root, int curSum, int sum, Map<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        int result = 0;
        int new_sum = curSum + root.val;
        if (preSum.containsKey(new_sum - sum)) {
            result += preSum.get(new_sum - sum);
        }
        preSum.put(new_sum, preSum.getOrDefault(new_sum, 0) + 1);
        result += helper(root.left, new_sum, sum, preSum);
        result += helper(root.right, new_sum, sum, preSum);
        preSum.put(new_sum, preSum.get(new_sum) - 1);

        return result;
    }
}
