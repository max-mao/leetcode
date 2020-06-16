//https://leetcode.com/problems/decode-ways/
//A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
//        'A' -> 1
//        'B' -> 2
//        ...
//        'Z' -> 26
//        Given a non-empty string containing only digits, determine the total number of ways to decode it.
//
//        Example 1:
//
//        Input: "12"
//        Output: 2
//        Explanation: It could be decoded as "AB" (1 2) or "L" (12).

//pay attention: how to handle 0:
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 :1;
        for (int i = 2; i < dp.length; i++) {
            int first = Integer.parseInt(s.substring(i-1, i));
            int second = Integer.parseInt(s.substring(i-2, i));
            if (first >= 1 && first <= 9) {
                dp[i] = dp[i-1];
            }
            if (second >=10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }

        return dp[s.length()];
    }
}