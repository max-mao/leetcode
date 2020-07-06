//https://leetcode.com/problems/word-search-ii/
//Given a 2D board and a list of words from the dictionary, find all words in the board.
//
//        Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//
//
//
//        Example:
//
//        Input:
//        board = [
//        ['o','a','a','n'],
//        ['e','t','a','e'],
//        ['i','h','k','r'],
//        ['i','f','l','v']
//        ]
//        words = ["oath","pea","eat","rain"]
//
//        Output: ["eat","oath"]

// DFS:
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> set = new HashSet<>();
        if (board == null
                || board.length == 0
                || board[0].length == 0
                || words == null
                || words.length == 0) {
            return new ArrayList<String>();
        }

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                for (int k =0; k < words.length; k++) {
                    if (board[i][j] == words[k].charAt(0)) {
                        boolean[][] visited = new boolean[board.length][board[0].length];
                        if (dfs(board, i, j, words[k], 0, visited)) {
                            set.add(words[k]);
                        }
                    }
                }
            }
        }


        List<String> result = new ArrayList<>();
        for (String s : set) {
            result.add(s);
        }
        return result;
    }

    private boolean dfs(char[][] board, int i, int j, String str, int index, boolean[][] visited) {
        if (index == str.length()) {
            return true;
        }
        if (i < 0
                || i > board.length -1 || j < 0
                || j > board[0].length -1
                || board[i][j] != str.charAt(index)
                ||visited[i][j]) {
            return false;
        }
        boolean res = false;
        visited[i][j] = true;
        res = dfs(board, i + 1, j, str, index +1, visited)
                || dfs(board, i - 1, j, str, index +1, visited)
                || dfs(board, i, j + 1, str, index +1, visited)
                || dfs(board, i, j - 1, str, index +1, visited);
        visited[i][j] = false;

        return res;
    }


}

//Trie + DFS: https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                dfs(board, i, j, result, root);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, List<String> result, TrieNode root) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        char c = board[i][j];
        if (c == '*' || root.next[c - 'a'] == null) {
            return;
        }
        TrieNode cur = root.next[c - 'a'];
        if (cur.word != null) {
            result.add(cur.word);
            cur.word = null;
        }

        board[i][j] = '*';
        dfs(board, i + 1, j, result, cur);
        dfs(board, i - 1, j, result, cur);
        dfs(board, i, j + 1, result, cur);
        dfs(board, i, j - 1, result, cur);
        board[i][j] = c;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String str : words) {
            TrieNode node = root;
            for (int i = 0; i < str.length(); i++) {
                int cur = str.charAt(i) - 'a';
                if (node.next[cur] == null) {
                    node.next[cur] = new TrieNode();
                }
                node = node.next[cur];
            }
            node.word = str;
        }

        return root;
    }

    class TrieNode {
        TrieNode[] next;
        String word;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.word = null;
        }
    }
}