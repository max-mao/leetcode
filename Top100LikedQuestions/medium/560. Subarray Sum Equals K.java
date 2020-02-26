//https://leetcode.com/problems/subarray-sum-equals-k/
//Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
//
//        Example 1:
//        Input:nums = [1,1,1], k = 2
//        Output: 2

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;

        int[] prefix_sum = new int[nums.length + 1];
        prefix_sum[0] = 0;
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += nums[i-1];
            prefix_sum[i] = sum;
        }

        for (int start = 0; start < nums.length; start++) {
            for (int end = start +1; end <= nums.length; end++) {
                if (prefix_sum[end] - prefix_sum[start] == k) {
                    count ++;
                }
            }
        }

        return count;
    }
}