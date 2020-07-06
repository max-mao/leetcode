//https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
//Given a string, find the length of the longest substring T that contains at most k distinct characters.
//
//        Example 1:
//
//        Input: s = "eceba", k = 2
//        Output: 3
//        Explanation: T is "ece" which its length is 3.


//solution:
//https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/discuss/80055/Generic-solution-in-Java-that-can-be-used-for-Unicode
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > k) {
                char left_char = s.charAt(left);
                if (map.get(left_char) > 0) {
                    map.put(left_char, map.get(left_char) - 1);
                    left ++;
                }
                if (map.get(left_char) == 0) {
                    map.remove(left_char);
                }
            }
            res = Math.max(res, i - left + 1);
        }

        return res;
    }
}