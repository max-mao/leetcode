//https://leetcode.com/problems/alien-dictionary/
//There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
//
//        Example 1:
//
//        Input:
//        [
//        "wrt",
//        "wrf",
//        "er",
//        "ett",
//        "rftt"
//        ]
//
//        Output: "wertf"

//solution: https://www.youtube.com/watch?v=LA0X_N-dEsg
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] indegree = new int[26];
        buildGraph(words, graph, indegree);
        return bfs(graph, indegree);
    }

    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] indegree) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<Character>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            String prev = words[i-1];
            String cur = words[i];
            //check for cases like, ["wrtkj","wrt"]; it's invalid, because this input is not in sorted lexicographical order
            if (prev.length() > cur.length() && prev.startsWith(cur)) {
                graph.clear();
                return;
            }
            int minLen = Math.min(prev.length(), cur.length());
            for (int j = 0; j < minLen; j++) {
                if (prev.charAt(j) != cur.charAt(j)) {
                    char from = prev.charAt(j);
                    char to = cur.charAt(j);
                    if (!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        indegree[to - 'a']++;
                    }
                    break;
                }
            }
        }
    }

    private String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
        Queue<Character> queue = new LinkedList<>();
        int strLen = graph.keySet().size();

        StringBuilder sb = new StringBuilder();
        for (char c : graph.keySet()) {
            if (indegree[c - 'a'] == 0) {
                sb.append(c);
                queue.add(c);
            }
        }

        while (!queue.isEmpty()) {
            Character cur = queue.poll();
            Set<Character> nextNodes = graph.get(cur);
            for (char next : nextNodes) {
                indegree[next - 'a'] --;
                if (indegree[next - 'a'] == 0) {
                    queue.add(next);
                    sb.append(next);
                }
            }
        }

        return sb.length() == strLen ? sb.toString() : "";
    }
}