//https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
//Given a binary tree, return the vertical order traversal of its nodes values.
//
//        For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
//
//        Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
//
//        If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
//
//        Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
//
//
//
//        Example 1:
//
//
//
//        Input: [3,9,20,null,null,15,7]
//        Output: [[9],[3,15],[20],[7]]
//        Explanation:
//        Without loss of generality, we can assume the root node is at position (0, 0):
//        Then, the node with value 9 occurs at position (-1, -1);
//        The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
//        The node with value 20 occurs at position (1, -1);
//        The node with value 7 occurs at position (2, -2).

//solution: https://www.youtube.com/watch?v=QWbVCqIhTO4
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
    class Node {
        TreeNode root;
        int x;
        int y;
        public Node(TreeNode root, int x, int y) {
            this.root = root;
            this.x = x;
            this.y = y;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Node>> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0, 0));
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            min = Math.min(min, cur.x);
            max = Math.max(max, cur.x);
            if (!map.containsKey(cur.x)) {
                map.put(cur.x, new ArrayList<Node>());
            }
            map.get(cur.x).add(cur);
            if (cur.root.left != null) {
                queue.add(new Node(cur.root.left, cur.x - 1, cur.y + 1));
            }
            if (cur.root.right != null) {
                queue.add(new Node(cur.root.right, cur.x + 1, cur.y + 1));
            }
        }

        for (int i = min; i <= max; i++) {
            Collections.sort(map.get(i), new Comparator<Node>() {
                public int compare(Node n1, Node n2) {
                    if (n1. y != n2.y) {
                        return n1.y - n2.y;
                    } else {
                        return n1.root.val - n2.root.val;
                    }
                }
            });
            List<Integer> list = new ArrayList<>();
            for (Node node : map.get(i)) {
                list.add(node.root.val);
            }
            res.add(list);
        }

        return res;
    }
}