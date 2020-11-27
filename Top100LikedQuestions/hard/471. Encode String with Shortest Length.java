//https://leetcode.com/problems/encode-string-with-shortest-length/
//Given a non-empty string, encode the string such that its encoded length is the shortest.
//
//The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
//
//Note:
//
//k will be a positive integer.
//If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them.
//
//
//Example 1:
//
//Input: s = "aaa"
//Output: "aaa"
//Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
//Example 2:
//
//Input: s = "aaaaa"
//Output: "5[a]"
//Explanation: "5[a]" is shorter than "aaaaa" by 1 character.

//solution: https://www.youtube.com/watch?v=fe2wvdUEmtQ
class Solution {
    public String encode(String s) {
        int len = s.length();
        String[][] dp = new String[len][len];

        for (int l = 0; l < len; l++) {
            for (int i = 0; i < len -l; i++) {
                int j = i + l;
                String cur = s.substring(i, j+1);
                if (cur.length() <= 4) {
                    dp[i][j] = cur;
                } else {
                    dp[i][j] = cur;
                    for (int k = i ; k < j; k++) {
                        if (dp[i][k].length() + dp[k+1][j].length() < dp[i][j].length()) {
                            dp[i][j] = dp[i][k] + dp[k+1][j];
                        }
                    }

                    for (int k = 0 ; k < cur.length(); k++) {
                        String ss = cur.substring(0, k+1);
                        if (ss != null
                                && cur.length() % ss.length() == 0
                                && cur.replaceAll(ss, "").length() == 0) {
                            String candidate = cur.length() / ss.length() +"[" + dp[i][i+k] + "]";
                            if (candidate.length() < dp[i][j].length()) {
                                dp[i][j] = candidate;
                            }
                        }
                    }
                }
            }
        }

        return dp[0][len-1];
    }
}