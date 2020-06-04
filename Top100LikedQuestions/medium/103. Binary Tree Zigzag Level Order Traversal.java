//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
//Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
//
//        For example:
//        Given binary tree [3,9,20,null,null,15,7],
//        3
//        / \
//        9  20
//        /  \
//        15   7
//        return its zigzag level order traversal as:
//        [
//        [3],
//        [20,9],
//        [15,7]
//        ]

//BFS:
 *     TreeNode(int val) { this.val = val; }
         *     TreeNode(int val, TreeNode left, TreeNode right) {
         *         this.val = val;
         *         this.left = left;
         *         this.right = right;
         *     }
         * }
         */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> curList = new ArrayList<>();
            int len = queue.size();
            for (int i=0; i < len; i ++) {
                TreeNode cur = queue.pop();
                if (level % 2 == 0) {
                    curList.add(cur.val);
                } else {
                    curList.add(0, cur.val);
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right !=null) {
                    queue.add(cur.right);
                }
            }
            result.add(curList);
            level ++;
        }

        return result;
    }
}

//DFS:


class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        helper(result, root, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (result.size() <= level) {
            result.add(new ArrayList<Integer>());
        }

        List<Integer> cur = result.get(level);
        if (level % 2 == 0) {
            cur.add(root.val);
        } else {
            cur.add(0, root.val);
        }

        helper(result, root.left, level +1);
        helper(result, root.right, level +1);
    }
}