//https://leetcode.com/problems/super-egg-drop/
//You are given K eggs, and you have access to a building with N floors from 1 to N.
//
//        Each egg is identical in function, and if an egg breaks, you cannot drop it again.
//
//        You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.
//
//        Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
//
//        Your goal is to know with certainty what the value of F is.
//
//        What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
//
//
//
//        Example 1:
//
//        Input: K = 1, N = 2
//        Output: 2
//        Explanation:
//        Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
//        Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
//        If it didn't break, then we know with certainty F = 2.
//        Hence, we needed 2 moves in the worst case to know what F is with certainty.

//solution: https://leetcode.com/problems/super-egg-drop/discuss/159055/Java-DP-solution-from-O(KN2)-to-O(KNlogN)
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K+1][N+1];
        return helper(K, N, dp);
    }

    private int helper(int K, int N, int[][] dp) {
        if (K <= 1) {
            return N;
        }
        if (N <= 1) {
            return 1;
        }

        if (dp[K][N] != 0) {
            return dp[K][N];
        }

        int low = 1, high = N, min = N;
        while (low < high) {
            int mid = low + (high - low)/2;
            int left = helper(K-1, mid - 1, dp);
            int right = helper(K, N - mid, dp);
            min = Math.min(min, Math.max(left, right) + 1);
            if (left == right) {
                break;
            } else if (left < right) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        dp[K][N] = min;
        return min;
    }
}