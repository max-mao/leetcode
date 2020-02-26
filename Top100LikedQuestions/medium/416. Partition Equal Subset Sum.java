//https://leetcode.com/problems/partition-equal-subset-sum/

//Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
//
//        Note:
//
//        Each of the array element will not exceed 100.
//        The array size will not exceed 200.
//
//
//        Example 1:
//
//        Input: [1, 5, 11, 5]
//
//        Output: true
//
//        Explanation: The array can be partitioned as [1, 5, 5] and [11].

// DP
class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int i=0; i< nums.length; i++) {
            sum += nums[i];
        }
        if (sum %2 == 1) {
            return false;
        }
        int half_sum = sum / 2;
        boolean[][] map = new boolean[nums.length+1][half_sum +1];
        //表示前i个数能不能组成0
        for (int i =0; i < map.length; i++) {
            map[i][0] = true;
        }

        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                if (j - nums[i-1] >= 0) {
                    map[i][j] = map[i-1][j] || map[i-1][j - nums[i-1]];
                } else {
                    map[i][j] = map[i-1][j];
                }
            }
        }

        return map[nums.length][half_sum];
    }

// 递归
    class Solution {
        public boolean canPartition(int[] nums) {
            if (nums == null || nums.length == 0) {
                return false;
            }

            int sum = 0;
            for (int i=0; i< nums.length; i++) {
                sum += nums[i];
            }
            if (sum %2 == 1) {
                return false;
            }
            int half_sum = sum / 2;
            return canFindHalfSum(nums, half_sum, 0, 0);
        }

        private boolean canFindHalfSum(int[] nums, int half_sum, int cur_sum, int index) {
            if(index == nums.length) {
                return false;
            }

            if (cur_sum + nums[index] == half_sum) {
                return true;
            }
            return canFindHalfSum(nums, half_sum, cur_sum + nums[index], index +1) ||
                    canFindHalfSum(nums, half_sum, cur_sum, index +1);

        }
    }

}