//https://leetcode.com/problems/design-tic-tac-toe/
//Design a Tic-tac-toe game that is played between two players on a n x n grid.
//
//        You may assume the following rules:
//
//        A move is guaranteed to be valid and is placed on an empty block.
//        Once a winning condition is reached, no more moves is allowed.
//        A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.


// 记得要取Math.abs
class TicTacToe {
    int[] Row;
    int[] Col;
    int diagonal;
    int anti_diagonal;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        Row = new int[n];
        Col = new int[n];
        diagonal = 0;
        anti_diagonal = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int n = Row.length;
        int add = 0;
        if (player == 1) {
            add = 1;
        } else if (player == 2) {
            add = -1;
        }

        Row[row] += add;
        Col[col] += add;

        if (row == col) {
            diagonal += add;
        }

        if (row + col == n -1) {
            anti_diagonal += add;
        }

        if (Math.abs(Row[row]) == n ||
                Math.abs(Col[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(anti_diagonal) == n) {
            return player;
        }

        return 0;
    }

}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */