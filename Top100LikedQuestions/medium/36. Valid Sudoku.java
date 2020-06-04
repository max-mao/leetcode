//https://leetcode.com/problems/valid-sudoku/
//Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
//
//        Each row must contain the digits 1-9 without repetition.
//        Each column must contain the digits 1-9 without repetition.
//        Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }

        Set<String> set = new HashSet<>();
        for (int i =0; i < 9; i++) {
            for (int j = 0; j < 9; j ++) {
                char number = board[i][j];
                if (number == '.') continue;

                if (set.contains(board[i][j] + "in row" + i)
                        || set.contains(board[i][j] + " in col" + j)
                        || set.contains(board[i][j] + "in box" + i/3 + j/3)) {
                    return false;
                } else {
                    set.add(board[i][j] + "in row" + i);
                    set.add(board[i][j] + " in col" + j);
                    set.add(board[i][j] + "in box" + i/3 + j/3);
                }
            }
        }

        return true;
    }
}