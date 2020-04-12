//https://leetcode.com/problems/minimum-window-substring/
//Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
//
//        Example:
//
//        Input: S = "ADOBECODEBANC", T = "ABC"
//        Output: "BANC"
//        Note:
//
//        If there is no such window in S that covers all characters in T, return the empty string "".
//        If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

//solution: https://www.youtube.com/watch?v=eS6PZLjoaq8
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t.length() > s.length()) {
            return "";
        }

        int[] map = new int[256];
        int left = 0, right = 0, head = 0, count = t.length();
        int len = Integer.MAX_VALUE;
        for (int i =0; i < t.length(); i++) {
            char c = t.charAt(i);
            map[c] ++;
        }

        while (right < s.length()) {
            char r = s.charAt(right);
            if (map[r] > 0) {
                count --;
            }
            map[r] --;
            right ++;

            while (count == 0) {
                if (right - left  < len) {
                    len = right - left;
                    head = left;
                }
                char l = s.charAt(left);
                if (map[l] >= 0) {
                    count ++;
                }
                map[l] ++;
                left ++;

            }
        }

        if (len == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(head, head + len);
    }
}