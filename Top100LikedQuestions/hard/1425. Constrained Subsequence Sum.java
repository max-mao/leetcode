//https://leetcode.com/problems/constrained-subsequence-sum/
//Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
//
//        A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.
//
//
//
//        Example 1:
//
//        Input: nums = [10,2,-10,5,20], k = 2
//        Output: 37
//        Explanation: The subsequence is [10, 2, 5, 20].

// DP + deque
// video: https://www.youtube.com/watch?v=B5fa989qz4U
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        // dp[i] means max subset sum of index i and nums[i] is in it.
        int[] dp = new int[nums.length];
        Deque<Integer> maxQ = new LinkedList<>();
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i > k && maxQ.peekFirst() == dp[i - k -1]) {
                maxQ.pollFirst();
            }
            if (maxQ.isEmpty()) {
                dp[i] = nums[i];
            } else {
                dp[i] = Math.max(0, maxQ.peekFirst()) + nums[i];
            }
            while (!maxQ.isEmpty() && maxQ.peekLast() < dp[i]) {
                maxQ.pollLast();
            }
            maxQ.add(dp[i]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}