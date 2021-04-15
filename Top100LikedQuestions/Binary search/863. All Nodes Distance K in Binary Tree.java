//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
//We are given a binary tree (with root node root), a target node, and an integer value K.
//
//Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
//
//
//
//Example 1:
//
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//
//Output: [7,4,1]
//
//Explanation:
//The nodes that are a distance 2 from the target node (with value 5)
//have values 7, 4, and 1.

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Set<TreeNode> visited = new HashSet<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(target);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i< size; i++) {
                TreeNode cur = queue.poll();
                if (K == 0 && cur != null) {
                    result.add(cur.val);
                }
                visited.add(cur);
                List<TreeNode> next = graph.get(cur);
                for (TreeNode n : next) {
                    if (!visited.contains(n)) {
                        queue.add(n);
                    }
                }
            }
            K--;
        }

        return result;
    }

    private void buildGraph(TreeNode node, TreeNode parent, Map<TreeNode, List<TreeNode>> graph) {
        if (node == null) {
            return;
        }

        if (!graph.containsKey(node)) {
            graph.put(node, new ArrayList<TreeNode>());
        }
        graph.get(node).add(parent);
        if (!graph.containsKey(parent)) {
            graph.put(parent, new ArrayList<TreeNode>());
        }
        graph.get(parent).add(node);
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }
}