//https://leetcode.com/problems/longest-substring-without-repeating-characters/

//Given a string, find the length of the longest substring without repeating characters.
//
//        Example 1:
//
//        Input: "abcabcbb"
//        Output: 3
//        Explanation: The answer is "abc", with the length of 3.

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        int result = 1;
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right ++;
            } else {
                while (set.contains(s.charAt(right))) {
                    set.remove(s.charAt(left));
                    left ++;
                }
            }
            result = Math.max(result, right - left);
        }

        return result;
    }
}