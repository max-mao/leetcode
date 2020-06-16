//https://leetcode.com/problems/surrounded-regions/
//Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
//
//        A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//        Example:
//
//        X X X X
//        X O O X
//        X X O X
//        X O X X

class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        for (int i = 0; i < board[0].length; i ++) {
            if (board[0][i] == 'O') {
                flip(0, i, board);
            }
            if (board[board.length -1][i] == 'O') {
                flip(board.length -1, i, board);
            }
        }

        for (int i = 0; i < board.length; i ++) {
            if (board[i][0] == 'O') {
                flip(i, 0, board);
            }
            if (board[i][board[0].length - 1] == 'O') {
                flip(i, board[0].length - 1, board);
            }
        }

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void flip(int x, int y, char[][] board) {
        if (x < 0 || x > board.length -1 || y < 0 || y > board[0].length -1) {
            return;
        }

        if (board[x][y] != 'O') {
            return;
        }

        board[x][y] = '1';
        flip(x +1, y, board);
        flip(x -1, y, board);
        flip(x, y + 1, board);
        flip(x, y - 1, board);
    }
}