//https://leetcode.com/problems/binary-tree-right-side-view/
//Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//
//        Example:
//
//        Input: [1,2,3,null,5,null,4]
//        Output: [1, 3, 4]
//        Explanation:
//
//        1            <---
//        /   \
//        2     3         <---
//        \     \
//        5     4       <---

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int nums = queue.size();
            for(int i = 0; i < nums; i ++) {
                TreeNode cur = queue.poll();
                //level order traverse, add the right most into res
                if (i == nums - 1) {
                    res.add(cur.val);
                }
                if (cur.left != null) {
                    queue.add(cur.left );
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }

        return res;
    }