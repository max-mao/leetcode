//https://leetcode.com/problems/find-duplicate-subtrees/
//Given the root of a binary tree, return all duplicate subtrees.
//
//For each kind of duplicate subtrees, you only need to return the root node of any one of them.
//
//Two trees are duplicate if they have the same structure with the same node values.
//
//
//
//Example 1:
//
//
//Input: root = [1,2,3,4,null,2,4,null,null,4]
//Output: [[2,4],[4]]

class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        helper(result, map, root);


        return result;
    }

    private String helper(List<TreeNode> result, Map<String, Integer> map, TreeNode root) {
        if (root == null) {
            return "#";
        }
        String serilize = root.val + "," +  helper(result, map, root.left) + "," +helper(result, map, root.right);
        map.put(serilize, map.getOrDefault(serilize, 0) + 1);
        if (map.get(serilize) == 2) {
            result.add(root);
        }

        return serilize;
    }
}