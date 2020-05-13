//https://leetcode.com/problems/longest-common-prefix/
//Write a function to find the longest common prefix string amongst an array of strings.
//
//        If there is no common prefix, return an empty string "".
//
//        Example 1:
//
//        Input: ["flower","flow","flight"]
//        Output: "fl"

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int minLen = Integer.MAX_VALUE;
        String prefix = "";
        for (int i = 0; i < strs.length; i ++) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
                prefix = strs[i];
            }
        }

        int prefixEnd = prefix.length();
        for (int i = 0; i < strs.length; i ++) {
            String cur = strs[i];
            for (int j = 0; j < minLen; j++) {
                if(cur.charAt(j) != prefix.charAt(j)) {
                    prefixEnd = Math.min(j, prefixEnd);
                }
            }
        }

        return prefix.substring(0, prefixEnd);
    }
}