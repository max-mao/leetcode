//https://leetcode.com/problems/diameter-of-binary-tree/
//Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
//
//Example:
//Given a binary tree
//          1
//         / \
//        2   3
//       / \
//      4   5
//Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

//https://leetcode.com/problems/diameter-of-binary-tree/discuss/101132/Java-Solution-MaxDepth
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = dfs(root);
        return res[1];
    }

    private int[] dfs(TreeNode root) {
        //res[0] the height from root, res[1] the diameter
        int[] res = new int[2];
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        res[0] = Math.max(left[0], right[0]) + 1;
        res[1] = Math.max(Math.max(left[1], right[1]), left[0] + right[0]);

        return res;
    }
}
