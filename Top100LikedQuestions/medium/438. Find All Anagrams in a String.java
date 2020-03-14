//https://leetcode.com/problems/find-all-anagrams-in-a-string/
//Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
//
//        Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
//
//        The order of output does not matter.
//
//        Example 1:
//
//        Input:
//        s: "cbaebabacd" p: "abc"
//
//        Output:
//        [0, 6]
//
//        Explanation:
//        The substring with start index = 0 is "cba", which is an anagram of "abc".
//        The substring with start index = 6 is "bac", which is an anagram of "abc".

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }
        int[] sMap = new int[26];
        int[] pMap = new int[26];

        //将p中char的map 建好
        for (int i =0; i < p.length(); i++) {
            pMap[p.charAt(i) - 'a'] ++;
        }

        //建s第0 index 开始的map
        for (int i =0; i < p.length(); i++) {
            sMap[s.charAt(i) - 'a'] ++;
        }

        //将window 朝后面挪动
        for (int i = 0; i <= s.length() - p.length(); i++) {
            int end = i + p.length();
            if (isSame(sMap, pMap)) {
                result.add(i);
            }
            if (end < s.length()) {
                sMap[s.charAt(i) - 'a'] --;
                sMap[s.charAt(end) - 'a'] ++;
            }
        }

        return result;
    }

    private boolean isSame(int[] sMap, int[] pMap) {
        for (int i = 0; i < sMap.length; i++) {
            if (sMap[i] != pMap[i]) {
                return false;
            }
        }

        return true;
    }
}