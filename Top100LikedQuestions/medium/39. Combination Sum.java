//https://leetcode.com/problems/combination-sum/

//Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
//
//        The same repeated number may be chosen from candidates unlimited number of times.
//
//        Note:
//
//        All numbers (including target) will be positive integers.
//        The solution set must not contain duplicate combinations.
//        Example 1:
//
//        Input: candidates = [2,3,6,7], target = 7,
//        A solution set is:
//        [
//        [7],
//        [2,2,3]
//        ]

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        dfs(result, candidates, target, 0, new ArrayList<Integer>());
        return result;
    }

    private void dfs(List<List<Integer>> result,
                     int[] candidates,
                     int target,
                     int index,
                     List<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i= index; i < candidates.length; i ++) {
            list.add(candidates[i]);
            dfs(result, candidates, target - candidates[i], i, list);
            list.remove(list.size()-1);
        }
    }
}