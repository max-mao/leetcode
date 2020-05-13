//https://leetcode.com/problems/game-of-life/
//Use extra space:
class Solution {
    public void gameOfLife(int[][] board) {
        int[][] new_board = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                int count = check(board, i , j);
                change(count, board, new_board, i, j);
            }
        }

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                board[i][j] = new_board[i][j];
            }
        }
    }

    private int check(int[][] board, int x, int y) {
        int count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};

        for (int[] index : dirs) {
            int new_x = index[0] + x;
            int new_y = index[1] + y;
            if (new_x < 0 || new_x > board.length -1 || new_y < 0 || new_y > board[0].length -1) {
                continue;
            }
            if (board[new_x][new_y] == 1) {
                count ++;
            }
        }

        return count;
    }

    private void change(int count, int[][] board, int[][] new_board, int i, int j) {
        if (board[i][j] == 1) {
            if (count < 2) {
                new_board[i][j] = 0;
            } else if (count <= 3) {
                new_board[i][j] = 1;
            } else {
                new_board[i][j] = 0;
            }
        } else {
            if (count == 3) {
                new_board[i][j] = 1;
            }
        }
    }
}

//In place: https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
// [2nd bit, 1st bit] = [next state, current state]

// - 00  dead (next) <- dead (current)
// - 01  dead (next) <- live (current)
// - 10  live (next) <- dead (current)
// - 11  live (next) <- live (current)

class Solution {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                modify(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    private void modify(int[][] board, int x, int y) {
        int count = 0;
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1,1}};
        for (int i = 0; i < dirs.length; i++) {
            int new_x = dirs[i][0] + x;
            int new_y = dirs[i][1] + y;
            if (new_x >=0 && new_x < board.length && new_y >= 0 && new_y < board[0].length) {
                count += board[new_x][new_y] & 1;
            }
        }

        if (board[x][y] == 1) {
            if (count < 2) {
                board[x][y] = 1;
            } else if (count <= 3) {
                board[x][y] = 3;
            } else {
                board[x][y] = 1;
            }
        } else {
            if (count == 3) {
                board[x][y] = 2;
            }
        }

    }
}