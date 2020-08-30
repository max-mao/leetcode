//https://leetcode.com/problems/minimum-window-subsequence/
//Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
//
//        If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
//
//        Example 1:
//
//        Input:
//        S = "abcdebdde", T = "bde"
//        Output: "bcde"
//        Explanation:
//        "bcde" is the answer because it occurs before "bdde" which has the same length.
//        "deb" is not a smaller window because the elements of T in the window must occur in order.

//solution: https://www.youtube.com/watch?v=EFRuN79l92E

class Solution {
    public String minWindow(String S, String T) {
        //dp[i][j] store the length of Minimum Window Subsequence of S[0: i] to T[0:j] which end with index i
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = Integer.MAX_VALUE/2;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (S.charAt(i-1) == T.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = dp[i-1][j] + 1;
                }
            }
        }

        int len = Integer.MAX_VALUE /2;
        int end = S.length() +1;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i][T.length()] < len) {
                len = dp[i][T.length()];
                end = i;
            }
        }

        if (len == Integer.MAX_VALUE / 2) {
            return "";
        }
        return S.substring(end - len,end);
    }
}