//https://leetcode.com/problems/find-duplicate-subtrees/
//Given the root of a binary tree, return all duplicate subtrees.
//
//        For each kind of duplicate subtrees, you only need to return the root node of any one of them.
//
//        Two trees are duplicate if they have the same structure with the same node values.
//
//
//
//        Example 1:
//
//
//        Input: root = [1,2,3,4,null,2,4,null,null,4]
//        Output: [[2,4],[4]]

//solution: https://www.youtube.com/watch?v=JLK92dbTt8k&ab_channel=HuaHua

class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        serilize(root, result, map);
        return result;
    }

    private String serilize(TreeNode root, List<TreeNode> result, Map<String, Integer> map) {
        if (root == null) {
            return "#";
        }
        String key = root.val + "," + serilize(root.left, result, map) + "," + serilize(root.right, result, map);
        map.put(key, map.getOrDefault(key, 0) + 1);
        if (map.get(key) == 2) {
            result.add(root);
        }

        return key;
    }
}