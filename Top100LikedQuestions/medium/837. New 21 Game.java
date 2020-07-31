//https://leetcode.com/problems/new-21-game/
//Alice plays the following game, loosely based on the card game "21".
//
//        Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.
//
//        Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
//
//        Example 1:
//
//        Input: N = 10, K = 1, W = 10
//        Output: 1.00000

//solution: https://www.youtube.com/watch?v=hItY5khcCqA
class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }

        int max = K + W -1;
        double[] dp = new double[max +1];
        dp[0] = 1.0;
        double sum = 1.0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = sum / W;
            if (i < K) {
                sum += dp[i];
            }
            if (i >= W) {
                sum -= dp[i - W];
            }
        }

        double res = 0.0;
        for(int i = K; i <= N; i++) {
            res += dp[i];
        }

        return res;
    }
}

//TLE: https://leetcode.com/problems/new-21-game/discuss/132358/Java-O(K-%2B-W)-DP-solution-with-explanation
class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }

        int max = K + W -1;
        double[] dp = new double[max +1];
        dp[0] = 1.0;
        for (int i =1; i < dp.length; i++) {
            for (int j = 1; j <= W; j++) {
                if (i -j >=0 && i -j < K) {
                    dp[i] += dp[i -j] /W;
                }
            }
        }

        double sum = 0;
        for (int i = K; i <= N; i++) {
            sum += dp[i];
        }

        return sum;
    }
}