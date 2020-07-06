//https://leetcode.com/problems/word-break-ii/
//Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
//
//        Note:
//
//        The same word in the dictionary may be reused multiple times in the segmentation.
//        You may assume the dictionary does not contain duplicate words.
//        Example 1:
//
//        Input:
//        s = "catsanddog"
//        wordDict = ["cat", "cats", "and", "sand", "dog"]
//        Output:
//        [
//        "cats and dog",
//        "cat sand dog"
//        ]

//DFS: https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<String, List<String>>());
    }

    private List<String> dfs(String s, List<String> wordDict, HashMap<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> result = new ArrayList<>();
        if (s.length() == 0) {
            result.add("");
            return result;
        }
        for (int i = 0; i < s.length(); i ++) {
            String cur = s.substring(0, i+1);
            if (wordDict.contains(cur)) {
                List<String> temp = dfs(s.substring(i+1), wordDict, map);
                if (temp.size() == 0) {
                    continue;
                }
                for (String str : temp) {
                    if (str.length() > 0) {
                        result.add(cur + " " + str);
                    } else {
                        result.add(cur);
                    }
                }
            }
        }

        map.put(s, result);
        return result;
    }
}