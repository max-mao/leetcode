//https://leetcode.com/problems/number-of-longest-increasing-subsequence/
//Given an integer array nums, return the number of longest increasing subsequences.
//
//Notice that the sequence has to be strictly increasing.
//
//
//
//Example 1:
//
//Input: nums = [1,3,5,4,7]
//Output: 2
//Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

class Solution {
    public int findNumberOfLIS(int[] nums) {
        //dp[i] len of LIS which end at i
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        //count[i] number of LIS which end at i
        int[] counts = new int[nums.length];
        Arrays.fill(counts, 1);

        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        counts[i] = counts[j];
                    } else if(dp[j] + 1 == dp[i]) {
                        counts[i] += counts[j];
                    }
                }
            }

            max = Math.max(max, dp[i]);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == max) {
                res += counts[i];
            }
        }

        return res;
    }
}

