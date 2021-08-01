//https://leetcode.com/problems/longest-repeating-character-replacement/
//You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
//
//Return the length of the longest substring containing the same letter you can get after performing the above operations.
//
//
//
//Example 1:
//
//Input: s = "ABAB", k = 2
//Output: 4
//Explanation: Replace the two 'A's with two 'B's or vice versa.

class Solution {
    public int characterReplacement(String s, int k) {
        int res = 0;

        int[] count = new int[26];
        int left = 0;
        int right = 0;
        int max = 0;
        int max_len = 0;

        while (right < s.length()) {
            count[s.charAt(right) - 'A'] ++;
            max = Math.max(max, count[s.charAt(right) - 'A']);
            if (right - left + 1 - max > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            max_len = Math.max(max_len, right - left + 1);
            right++;
        }

        return max_len;
    }
}