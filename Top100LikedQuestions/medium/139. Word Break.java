//https://leetcode.com/problems/word-break/
//Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
//        Note:
//
//        The same word in the dictionary may be reused multiple times in the segmentation.
//        You may assume the dictionary does not contain duplicate words.
//        Example 1:
//
//        Input: s = "leetcode", wordDict = ["leet", "code"]
//        Output: true
//        Explanation: Return true because "leetcode" can be segmented as "leet code".

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return false;
        }

        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i ++) {
            for (int j = 0; j < i; j ++) {
                String cur_string = s.substring(j, i);
                if (set.contains(cur_string) && dp[j]) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }
}