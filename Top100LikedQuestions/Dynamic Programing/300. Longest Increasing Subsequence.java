//https://leetcode.com/problems/longest-increasing-subsequence/
//Given an unsorted array of integers, find the length of longest increasing subsequence.
//
//        Example:
//
//        Input: [10,9,2,5,3,7,101,18]
//        Output: 4
//        Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //dp[i]数组代表 第i 个数之前包括自己，LIS 长度多少
        int[] dp = new int[nums.length];
        dp[0] = 1;

        int result = 1;
        for (int i = 1; i < nums.length; i ++) {
            int maxLen = 0;
            // 当当前第i个数 比第j个数大时候，是dp[i] = dp【j】 + 1
            for (int j = 0; j < i; j ++) {
                if (nums[j] < nums[i]) {
                    maxLen = Math.max(maxLen, dp[j]);
                }
            }
            dp[i] = maxLen + 1;
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}

//solution2: NlogN
//https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {
            int start = 0;
            int end = len;

            while (start < end) {
                int mid = start +(end- start)/2;
                if (dp[mid] < num) {
                    start = mid +1;
                } else {
                    end = mid;
                }
            }
            dp[start] = num;
            if (start == len) {
                len ++;
            }

        }

        return len;
    }
}