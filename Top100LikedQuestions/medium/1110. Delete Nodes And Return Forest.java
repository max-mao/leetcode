//https://leetcode.com/problems/delete-nodes-and-return-forest/
//Given the root of a binary tree, each node in the tree has a distinct value.
//
//        After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
//
//        Return the roots of the trees in the remaining forest.  You may return the result in any order.
//
//
//
//        Example 1:
//
//
//
//        Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
//        Output: [[1,2,null,4],[6],[7]]

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

//solution: https://www.youtube.com/watch?v=SEW3Vofoj_k

class Solution {
    List<TreeNode> result;
    Set<Integer> to_delete_set;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        result = new ArrayList<>();
        to_delete_set = new HashSet<>();
        for (int i : to_delete) {
            to_delete_set.add(i);
        }
        root = process(root);
        if (root != null) {
            result.add(root);
        }
        return result;
    }

    private TreeNode process(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = process(root.left);
        root.right = process(root.right);
        if (to_delete_set.contains(root.val)) {
            if (root.left != null) {
                result.add(root.left);
            }
            if (root.right != null) {
                result.add(root.right);
            }
            return null;
        } else {
            return root;
        }
    }
}