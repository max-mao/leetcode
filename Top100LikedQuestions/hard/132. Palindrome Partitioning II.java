//https://leetcode.com/problems/palindrome-partitioning-ii/

//Given a string s, partition s such that every substring of the partition is a palindrome.
//
//        Return the minimum cuts needed for a palindrome partitioning of s.
//
//        Example:
//
//        Input: "aab"
//        Output: 1
//        Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
class Solution {
    public int minCut(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }

        for (int i = 0; i < s.length()-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                isPalindrome[i][i+1] = true;
            }
        }

        for (int i = 2;  i < s.length(); i++) {
            for (int j = 0; j < i -1; j++) {
                if (s.charAt(i) == s.charAt(j) && isPalindrome[j+1][i-1]) {
                    isPalindrome[j][i] = true;
                }
            }
        }


        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i= 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] != Integer.MAX_VALUE && isPalindrome[j][i-1]) {
                    dp[i] = Math.min(dp[i], dp[j]+1);
                }
            }
        }

        return dp[s.length()] - 1;
    }

}