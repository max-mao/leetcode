//https://leetcode.com/problems/number-of-wonderful-substrings/
//A wonderful string is a string where at most one letter appears an odd number of times.
//
//For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
//Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.
//
//A substring is a contiguous sequence of characters in a string.
//
//
//
//Example 1:
//
//Input: word = "aba"
//Output: 4
//Explanation: The four wonderful substrings are underlined below:
//- "aba" -> "a"
//- "aba" -> "b"
//- "aba" -> "a"
//- "aba" -> "aba"
//solution: https://www.youtube.com/watch?v=QiiK-his0IQ
class Solution {
    public long wonderfulSubstrings(String word) {
        int[] count = new int[1024];
        count[0] = 1;
        int state = 0;
        long res = 0;

        for (int i = 0; i < word.length(); i++) {

            state = state ^ (1 << (word.charAt(i) - 'a'));
            res += count[state];
            count[state]++;
            for (int j = 0 ; j < 10; j++) {
                res += count[state ^ (1 << j)];
            }
        }

        return res;
    }
}