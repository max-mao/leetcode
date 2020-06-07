
//https://leetcode.com/problems/increasing-triplet-subsequence/
//Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
//
//        Formally the function should:
//
//        Return true if there exists i, j, k
//        such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
//        Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
// solution: https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79071/Clean-Java-Solution-with-Clear-Explanation

class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n <= first) {
                first = n;
            } else if (n <= second) {
                second = n;
            } else {
                return true;
            }
        }

        return false;
    }
}