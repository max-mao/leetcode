https://leetcode.com/problems/permutations/
//Given a collection of distinct integers, return all possible permutations.
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();

        dfs(nums, result, visited, new ArrayList<Integer>());
        return result;
    }

    private void dfs(int[] nums,
                     List<List<Integer>> result,
                     HashSet<Integer> visited,
                     ArrayList<Integer> list) {
        if (list.size() == nums.length) {
            result.add(new ArrayList(list));
            return;
        }

        for (int i = 0; i < nums.length; i ++) {
            if (visited.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            visited.add(nums[i]);
            dfs(nums, result, visited, list);
            list.remove(list.size() - 1);
            visited.remove(nums[i]);
        }
    }
}