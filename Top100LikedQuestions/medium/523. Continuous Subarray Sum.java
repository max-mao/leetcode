//https://leetcode.com/problems/continuous-subarray-sum/
//Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
//
//
//
//        Example 1:
//
//        Input: [23, 2, 4, 6, 7],  k=6
//        Output: True
//        Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i =0; i < nums.length; i ++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.containsKey(sum)) {
                int index = map.get(sum);
                if (i - index >= 2) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }
}