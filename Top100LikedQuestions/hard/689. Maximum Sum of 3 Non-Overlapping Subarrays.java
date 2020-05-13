//https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/

//In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
//
//        Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
//
//        Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.
//
//        Example:
//
//        Input: [1,2,1,2,6,7,5,1], 2
//        Output: [0, 3, 5]
//        Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
//        We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.


//dp, 视频讲解： https://www.youtube.com/watch?v=dGS4O0110qA&t=69s
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        //对于nums这个数组，有nums.length-k +1 个 长度为k的subarray
        //这个prefixSum存 这个subarray的sum
        int len = nums.length;
        int n = nums.length - k+1;
        int[] prefixSum = new int[n];
        int sum = 0;
        for (int i =0; i < len; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i -k];
            }
            if (i >= k - 1) {
                prefixSum[i -k+1] = sum;
            }
        }

        //leftIndex array 记录第i个数以前subarray sum最大的index
        int[] leftIndex = new int[n];
        int maxIndex = 0;
        for (int i=0; i < leftIndex.length; i++) {
            if (prefixSum[i] > prefixSum[maxIndex]) {
                maxIndex = i;
            }
            leftIndex[i] = maxIndex;
        }

        //rightIndex array 记录第i个数以后subarray sum最大的index
        int[] rightIndex = new int[n];
        maxIndex = n -1;
        for (int i=n-1; i >= 0; i--) {
            if (prefixSum[i] >= prefixSum[maxIndex]) {
                maxIndex = i;
            }
            rightIndex[i] = maxIndex;
        }

        int[] result = new int[3];
        Arrays.fill(result, -1);
        for (int i = k; i <len - 2*k + 1; i++) {
            if (result[0] == -1 ||
                    prefixSum[leftIndex[i-k]] + prefixSum[i] + prefixSum[rightIndex[i+k]]
                            > prefixSum[result[0]] + prefixSum[result[1]] + prefixSum[result[2]])
            {
                result[0] = leftIndex[i-k];
                result[1] = i;
                result[2] = rightIndex[i+k];
            }
        }

        return result;
    }
}