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
            return null;
        }
        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();
        buildGraph(words, graph, indegree);
        return bfs(graph, indegree);
    }

    private String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
        int totalNode = graph.size();
        StringBuilder sb = new StringBuilder();

        Queue<Character> queue = new LinkedList<>();
        for (char c: graph.keySet()) {
            if (indegree[c - 'a'] == 0) {
                queue.add(c);
            }
        }

        while (!queue.isEmpty()) {
            char cur = queue.poll();
            sb.append(cur);
            Set<Character> neighbors = graph.get(cur);
            for (char neighbor: neighbors) {
                indegree[neighbor - 'a'] --;
                if (indegree[neighbor - 'a'] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (sb.length() == totalNode) {
            return sb.toString();
        } else {
            return "";
        }
    }

    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] indegree) {
        for (int i = 0; i < words.length; i ++) {
            for (char c : words[i].toCharArray()) {
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<Character>());
                }
            }
        }

        for (int i = 0; i < words.length -1; i ++) {
            String first = words[i];
            String second = words[i+1];
            int len = Math.min(first.length(), second.length());
            for (int j = 0; j < len; j ++) {
                if (first.charAt(j) != second.charAt(j)) {

                    char out = first.charAt(j);
                    char in = second.charAt(j);
                    if (!graph.get(out).contains(in)) {
                        graph.get(out).add(in);
                        indegree[in - 'a'] ++;
                    }
                    break;
                }

                if(j == len - 1 && first.length() > second.length()){
                    graph.clear();
                    return;
                }
            }
        }
    }
}