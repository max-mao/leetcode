//https://leetcode.com/problems/number-of-matching-subsequences/
//Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
//
//        Example :
//        Input:
//        S = "abcde"
//        words = ["a", "bb", "acd", "ace"]
//        Output: 3
//        Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".

// binary search: https://www.youtube.com/watch?v=l8_vcmjQA4g&t=877s&ab_channel=HuaHua
class Solution {
    private List<List<Integer>> pos;
    private boolean isMatch(String word) {
        int l = -1;
        for (char c : word.toCharArray()) {
            List<Integer> p = pos.get(c);
            int index = Collections.binarySearch(p, l + 1);
            if (index < 0) index = -index - 1;
            if (index >= p.size()) return false;
            l = p.get(index);
        }
        return true;
    }

    public int numMatchingSubseq(String S, String[] words) {
        pos = new ArrayList<>();
        for (int i = 0; i < 128; ++i)
            pos.add(new ArrayList<Integer>());
        char[] s = S.toCharArray();
        for (int i = 0; i < s.length; ++i)
            pos.get(s[i]).add(i);
        int ans = 0;
        for (String word : words)
            if (isMatch(word)) ++ans;
        return ans;
    }
}

// Brute force + map
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int result = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (isSubsequence(entry.getKey(), S)) {
                result += entry.getValue();
            }
        }

        return result;
    }

    private boolean isSubsequence(String S, String L) {
        int s_start = 0;
        int l_start = 0;

        while (s_start < S.length()) {
            if (l_start == L.length()) {
                return false;
            }
            if (S.charAt(s_start) == L.charAt(l_start)) {
                s_start ++;
            }
            l_start ++;

            if (s_start == S.length()) {
                return true;
            }
        }
        return false;
    }
}