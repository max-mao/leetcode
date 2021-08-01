//https://leetcode.com/problems/partition-labels/
//A string s of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
//
//
//
//Example 1:
//
//Input: s = "ababcbacadefegdehijhklij"
//Output: [9,7,8]
//Explanation:
//The partition is "ababcbaca", "defegde", "hijhklij".
//This is a partition so that each letter appears in at most one part.
//A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

//solution: https://leetcode.com/problems/partition-labels/discuss/113259/Java-2-pass-O(n)-time-O(1)-space-extending-end-pointer-solution
class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int[] index = new int[26];

        for (int i = 0; i < s.length(); i++) {
            index[s.charAt(i) - 'a'] = i;
        }

        int i = 0;
        int start = 0;
        int end = 0;
        while (i < s.length()) {
            end = Math.max(end, index[s.charAt(i) - 'a']);
            if (end == i) {
                res.add(end - start + 1);
                start = end + 1;
            }
            i++;
        }

        return res;
    }
}