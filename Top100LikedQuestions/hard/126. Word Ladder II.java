//https://leetcode.com/problems/word-ladder-ii/
//Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
//
//        Only one letter can be changed at a time
//        Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
//        Note:
//
//        Return an empty list if there is no such transformation sequence.
//        All words have the same length.
//        All words contain only lowercase alphabetic characters.
//        You may assume no duplicates in the word list.
//        You may assume beginWord and endWord are non-empty and are not the same.
//        Example 1:
//
//        Input:
//        beginWord = "hit",
//        endWord = "cog",
//        wordList = ["hot","dot","dog","lot","log","cog"]
//
//        Output:
//        [
//        ["hit","hot","dot","dog","cog"],
//        ["hit","hot","lot","log","cog"]
//        ]

//https://leetcode.com/problems/word-ladder-ii/discuss/40475/My-concise-JAVA-solution-based-on-BFS-and-DFS
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String str : wordList) {
            set.add(str);
        }
        List<List<String>> res = new ArrayList<>();
        if (!set.contains(endWord)) {
            return res;
        }
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        bfs(beginWord, endWord, graph, distance, set);
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        dfs(beginWord, endWord, graph, distance, res, temp);

        return res;
    }

    private void bfs(String beginWord, String endWord, Map<String, Set<String>> graph, Map<String, Integer> distance, Set<String> set) {
        set.add(beginWord);
        for (String node : set) {
            graph.put(node, new HashSet<>());
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        distance.put(beginWord, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> nextStrings = getNextWords(set, cur);
                for (String str : nextStrings) {
                    graph.get(cur).add(str);
                    if (!distance.containsKey(str)) {
                        distance.put(str, distance.get(cur) +1);
                        if (str.equals(endWord)) {
                            foundEnd = true;
                        } else {
                            queue.add(str);
                        }
                    }
                }
            }
            if (foundEnd) break;
        }
    }

    private List<String> getNextWords(Set<String> set, String str) {
        List<String> res = new ArrayList<>();
        String candidate = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < str.length(); i++) {
            String prefix = str.substring(0, i);
            String suffix = str.substring(i+1);
            for (int j = 0; j < candidate.length(); j++) {
                char cur = candidate.charAt(j);
                String new_string = prefix + cur + suffix;
                if (set.contains(new_string) ) {
                    res.add(new_string);
                }
            }
        }
        return res;
    }

    private void dfs(String beginWord, String endWord, Map<String, Set<String>> graph, Map<String, Integer> distance, List<List<String>> res, List<String> temp) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<String>(temp));
        } else {
            Set<String> nextStrings = graph.get(beginWord);
            for (String nextString : nextStrings) {
                if (distance.get(nextString) == distance.get(beginWord) +1) {
                    temp.add(nextString);
                    dfs(nextString, endWord, graph, distance, res, temp);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }

}