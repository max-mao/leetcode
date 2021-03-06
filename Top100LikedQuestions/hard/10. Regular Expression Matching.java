//https://leetcode.com/problems/regular-expression-matching/
//Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
//
//        '.' Matches any single character.
//        '*' Matches zero or more of the preceding element.
//        The matching should cover the entire input string (not partial).
//
//        Note:
//
//        s could be empty and contains only lowercase letters a-z.
//        p could be empty and contains only lowercase letters a-z, and characters like . or *.


//DP: //https://www.youtube.com/watch?v=l3hda49XcDE
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i ++) {
            dp[i][0] = false;
        }

        for (int i = 1; i < dp[0].length; i++) {
            //当string为“”，pattern只有为 a*b* 才能match，全部消除
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //当前i和j match上了，所以看i-1和j-1能不能match
                if (s.charAt(i-1) == p.charAt(j -1) || p.charAt(j -1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j -1) == '*') {
                    //当*消除了前面的字符
                    dp[i][j] = dp[i][j-2];

                    // *可以匹配一个或者多个前面的字母
                    if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[s.length()][p.length()];

    }
}