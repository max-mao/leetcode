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

// O(N) : https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87739/Java-Strict-O(N)-Two-Pointer-Solution
class Solution {
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] count = new int[26];
        int i, j, unique, noLessThanK;
        int max = 0;

        for (int h = 1; h <= 26; h++) {
            Arrays.fill(count, 0);
            i = 0;
            j = 0;
            unique = 0;
            noLessThanK = 0;
            while(j < s.length()) {
                if (unique <= h) {
                    int cur = str[j] - 'a';
                    if (count[cur] == 0) {
                        unique ++;
                    }
                    count[cur] ++;
                    if (count[cur] == k) {
                        noLessThanK ++;
                    }
                    j ++;
                } else {
                    int cur = str[i] - 'a';
                    if (count[cur] == k) {
                        noLessThanK --;
                    }
                    count[cur] --;
                    if (count[cur] == 0) {
                        unique --;
                    }
                    i++;
                }
                if (h == unique && h == noLessThanK) {
                    max = Math.max(j - i, max);
                }
            }
        }

        return max;

    }
}