//https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
//Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
//
//        Example 1:
//
//        Input:
//        s = "aaabb", k = 3
//
//        Output:
//        3
//
//        The longest substring is "aaa", as 'a' is repeated 3 times.

class Solution {
    public int longestSubstring(String s, int k) {
        return helper(s, 0, s.length(), k);
    }

    private int helper(String s, int start, int end, int k) {
        if (end - start < k) {
            return 0;
        }

        int[] map = new int[26];
        for (int i = start; i < end; i++) {
            map[s.charAt(i) - 'a'] ++;
        }

        for (int i = 0; i < 26; i ++) {
            int times = map[i];
            if (times < k && times > 0) {
                for (int j = start; j < end; j++) {
                    if (s.charAt(j) != 'a' + i) {
                        continue;
                    }
                    int left = helper(s, start, j, k);
                    int right = helper(s, j +1, end, k);
                    return Math.max(left, right);
                }
            }
        }

        return end - start;
    }
}