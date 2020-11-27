//https://leetcode.com/problems/boundary-of-binary-tree/
//A binary tree boundary is the set of nodes (no duplicates) denoting the union of the left boundary, leaves, and right boundary.
//
//The left boundary is the set of nodes on the path from the root to the left-most node. The right boundary is the set of nodes on the path from the root to the right-most node.
//
//The left-most node is the leaf node you reach when you always travel to the left subtree if it exists and the right subtree if it doesn't. The right-most node is defined in the same way except with left and right exchanged. Note that the root may be the left-most and/or right-most node.
//
//Given the root of a binary tree, return the values of its boundary in a counter-clockwise direction starting from the root.
//
//
//
//Example 1:
//
//
//Input: root = [1,null,2,3,4]
//Output: [1,3,4,2]
//Explanation:
//The left boundary is the nodes [1,2,3].
//The right boundary is the nodes [1,2,4].
//The leaves are nodes [3,4].
//Unioning the sets together gives [1,2,3,4], which is [1,3,4,2] in counter-clockwise order.

//solution: https://leetcode.com/problems/boundary-of-binary-tree/discuss/101280/Java(12ms)-left-boundary-left-leaves-right-leaves-right-boundary
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
    List<Integer> res = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return res;

        res.add(root.val);
        getLeftList(root.left);
        getBottomList(root.left);
        getBottomList(root.right);
        getRightList(root.right);

        return res;
    }

    private void getLeftList(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        res.add(root.val);
        if (root.left != null) {
            getLeftList(root.left);
        } else {
            getLeftList(root.right);
        }
    }

    private void getRightList(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        if (root.right != null) {
            getRightList(root.right);
        } else {
            getRightList(root.left);
        }
        res.add(root.val);
    }

    private void getBottomList(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        getBottomList(root.left);
        getBottomList(root.right);

    }
}
