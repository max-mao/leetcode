//https://leetcode.com/problems/jump-game-ii/
<<<<<<< HEAD

//        Given an array of non-negative integers, you are initially positioned at the first index of the array.
=======
//Given an array of non-negative integers, you are initially positioned at the first index of the array.
>>>>>>> 6f3b03482fd958bca0ba2d33e65a4724f60042c7
//
//        Each element in the array represents your maximum jump length at that position.
//
//        Your goal is to reach the last index in the minimum number of jumps.
//
//        Example:
//
//        Input: [2,3,1,1,4]
//        Output: 2
//        Explanation: The minimum number of jumps to reach the last index is 2.
//        Jump 1 step from index 0 to 1, then 3 steps to the last index.
<<<<<<< HEAD
//        Note:
//
//        You can assume that you can always reach the last index.
//
//        Accepted


// Greedy: O(N)
class Solution {
    public int jump(int[] nums) {
        int step = 0;
        int curEnd = 0;
        int maxJump = 0;
        for (int i = 0; i < nums.length -1; i ++) {
            maxJump = Math.max(maxJump, i + nums[i]);
            if (i == curEnd) {
                step ++;
                curEnd = maxJump;
                // exit the for loop earlier, still work without it.
                if (curEnd >= nums.length -1) {
                    break;
=======

//DP time: O(N^2)

class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] + j >= i) {
                    dp[i] = Math.min(dp[i], dp[j]+1);
>>>>>>> 6f3b03482fd958bca0ba2d33e65a4724f60042c7
                }
            }
        }

<<<<<<< HEAD
        return step;
    }
}

//DP O(n^2)
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[i] != -1 && j + nums[j]  >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                } else if (j + nums[j]  >= i) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        return dp[dp.length -1];
    }
}
=======
        return dp[dp.length -1];
    }
}

// Greedy time: O（n）
// https://leetcode.com/problems/jump-game-ii/discuss/18014/Concise-O(n)-one-loop-JAVA-solution-based-on-Greedy
class Solution {
    public int jump(int[] nums) {
        int start = 0, end = 0, farthest = 0;
        int step = 0;
        //i < nums.length -1 是因为 在最后index上不用跳了
        for (int i = 0; i < nums.length -1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                step ++;
                end = farthest;
            }

            if (end >= nums.length) {
                break;
            }
        }

        return step;
    }
}
>>>>>>> 6f3b03482fd958bca0ba2d33e65a4724f60042c7
