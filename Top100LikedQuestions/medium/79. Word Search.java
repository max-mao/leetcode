//https://leetcode.com/problems/word-search/

//Given a 2D board and a word, find if the word exists in the grid.
//
//        The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//        Example:
//
//        board =
//        [
//        ['A','B','C','E'],
//        ['S','F','C','S'],
//        ['A','D','E','E']
//        ]
//
//        Given word = "ABCCED", return true.
//        Given word = "SEE", return true.
//        Given word = "ABCB", return false.

class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0|| board[0].length == 0) {
            return false;
        }
        if (word ==null || word.length() ==0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean result = false;

        for (int i =0; i < board.length; i++) {
            for (int j=0; j < board[0].length; j ++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, word, visited, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int i, int j,
                        String word, boolean[][] visited, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        boolean result = false;
        if (!visited[i][j]) {
            visited[i][j] = true;
            result = dfs(board, i+1, j, word, visited, index+1) ||
                    dfs(board, i-1, j, word, visited, index+1) ||
                    dfs(board, i, j+1, word, visited, index+1) ||
                    dfs(board, i, j-1, word, visited, index+1);
            visited[i][j] = false;
        }

        return result;
    }
}