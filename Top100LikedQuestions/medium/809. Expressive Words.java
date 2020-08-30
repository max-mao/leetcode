//https://leetcode.com/problems/expressive-words/
//Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
//
//        For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
//
//        For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
//
//        Given a list of query words, return the number of words that are stretchy.
//
//
//
//        Example:
//        Input:
//        S = "heeellooo"
//        words = ["hello", "hi", "helo"]
//        Output: 1
//        Explanation:
//        We can extend "e" and "o" in the word "hello" to get "heeellooo".
//        We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.

class Solution {
    public int expressiveWords(String S, String[] words) {
        int result = 0;
        for (String word : words) {
            if (match(word, S)) {
                result ++;
            }
        }

        return result;
    }

    private boolean match(String w, String s) {
        int i = 0;
        int j = 0;

        while (i < w.length() && j < s.length()) {
            if (w.charAt(i) == s.charAt(j)) {
                int wLen = getLen(i, w);
                int sLen = getLen(j, s);
                if (sLen < 3 && wLen != sLen) {
                    return false;
                }
                if (wLen > sLen) {
                    return false;
                }
                i = i + wLen;
                j = j + sLen;
            } else {
                return false;
            }
        }

        if (i == w.length() && j == s.length()) {
            return true;
        }
        return false;
    }

    private int getLen(int index, String s) {
        char c = s.charAt(index);
        int count = 1;
        for (int i = index+1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}