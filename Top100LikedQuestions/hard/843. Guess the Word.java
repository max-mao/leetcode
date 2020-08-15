//https://leetcode.com/problems/guess-the-word/
//This problem is an interactive problem new to the LeetCode platform.
//
//        We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
//
//        You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
//
//        This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
//
//        For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
//
//        Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */

// https://www.cnblogs.com/grandyang/p/11449244.html
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        Random rand = new Random();
        for (int i = 0, count = 0; i < 10 && count < 6; i++) {
            String cur = wordlist[rand.nextInt(wordlist.length)];
            count = master.guess(cur);
            List<String> list = new ArrayList<>();
            for (String str : wordlist) {
                if (match(str, cur) == count) {
                    list.add(str);
                }
            }
            wordlist = list.toArray(new String[list.size()]);
        }

    }

    private int match (String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count ++;
            }
        }

        return count;
    }
}