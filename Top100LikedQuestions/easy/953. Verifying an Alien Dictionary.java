//https://leetcode.com/problems/verifying-an-alien-dictionary/
//In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
//
//        Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
//
//
//
//        Example 1:
//
//        Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
//        Output: true
//        Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i ++) {
            char cur = order.charAt(i);
            if (!map.containsKey(cur)) {
                map.put(cur, i);
            }
        }

        for (int i = 0; i < words.length -1; i ++) {
            String first = words[i];
            String second = words[i+1];
            int len = Math.min(first.length(), second.length());
            for (int j = 0; j < len; j ++) {
                if (first.charAt(j) != second.charAt(j)) {
                    if(map.get(first.charAt(j)) > map.get(second.charAt(j))) {
                        return false;
                    }
                    break;
                }
                if (j == len -1 && first.length() > second.length()) {
                    return false;
                }
            }
        }

        return true;
    }
}