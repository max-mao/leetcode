//https://leetcode.com/problems/subsets/

//Given a set of distinct integers, nums, return all possible subsets (the power set).
//
//        Note: The solution set must not contain duplicate subsets.
//
//        Example:
//
//        Input: nums = [1,2,3]
//        Output:
//        [
//        [3],
//        [1],
//        [2],
//        [1,2,3],
//        [1,3],
//        [2,3],
//        [1,2],
//        []
//        ]

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        dfs(nums, result, new ArrayList<Integer>(), 0);
        return result;
    }

    private void dfs(int[] nums,
                     List<List<Integer>> result,
                     ArrayList<Integer> list,
                     int start) {

        result.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i ++) {
            list.add(nums[i]);
            dfs(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }

    }
}