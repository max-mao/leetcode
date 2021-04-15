//https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
//Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
//
//Return the maximum possible length of s.
//
//
//
//Example 1:
//
//Input: arr = ["un","iq","ue"]
//Output: 4
//Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
//Maximum length is 4.

//solution: https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/discuss/414180/Clean-JavaJavaScript-DFS-solution
class Solution {
    int res = Integer.MIN_VALUE;
    public int maxLength(List<String> arr) {
        if (arr.size() == 0) {
            return 0;
        }

        dfs(arr, "", 0);
        return res;
    }

    private void dfs(List<String> arr, String str, int index) {
        boolean isUnique = isUnique(str);
        if (index == arr.size() || !isUnique) return;
        res = Math.max(res, str.length());

        for (int i = index; i < arr.size(); i++) {
            String cur = arr.get(i);
            String next = str + cur;
            dfs(arr, next, i);
        }
    }

    private boolean isUnique(String str) {
        Set<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }

        return true;
    }
}