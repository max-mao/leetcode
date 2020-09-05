//https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
//Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
//
//        Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
//
//        0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
//        0 <= j < j + M - 1 < i < i + L - 1 < A.length.
//
//
//        Example 1:
//
//        Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
//        Output: 20
//        Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.

class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] prefixSum = new int[A.length +1];
        prefixSum[0] = 0;
        for (int i = 1; i <= A.length; i++) {
            prefixSum[i] = prefixSum[i-1] + A[i-1];
        }

        int result = Integer.MIN_VALUE;
        result = Math.max(result, caculate(prefixSum, L, M));
        result = Math.max(result, caculate(prefixSum, M, L));

        return result;
    }

    private int caculate(int[] prefixSum, int left, int right) {
        int result = Integer.MIN_VALUE;

        int leftSum = Integer.MIN_VALUE;
        for (int i = left + right; i < prefixSum.length; i++) {
            leftSum = Math.max(leftSum, prefixSum[i- right] - prefixSum[i - left - right]);
            result = Math.max(result, leftSum + prefixSum[i] - prefixSum[i - right]);
        }

        return result;
    }
}