//https://leetcode.com/problems/alphabet-board-path/
//On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
//
//Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
//
//
//
//We may make the following moves:
//
//'U' moves our position up one row, if the position exists on the board;
//'D' moves our position down one row, if the position exists on the board;
//'L' moves our position left one column, if the position exists on the board;
//'R' moves our position right one column, if the position exists on the board;
//'!' adds the character board[r][c] at our current position (r, c) to the answer.
//(Here, the only positions that exist on the board are positions with letters on them.)
//
//Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
//
//
//
//Example 1:
//
//Input: target = "leet"
//Output: "DDR!UURRR!!DDD!"

class Solution {
    public String alphabetBoardPath(String target) {
        if (target == null || target.length() == 0) {
            return "";
        }
        int prevI = 0;
        int prevJ = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            int curI = (target.charAt(i) - 'a') / 5;
            int curJ = (target.charAt(i) - 'a') % 5;
            if (curI == prevI && curJ == prevJ) {
                sb.append("!");
            } else {
                getPath(sb, curI, curJ, prevI, prevJ);
                sb.append("!");
                prevI = curI;
                prevJ = curJ;
            }
        }

        return sb.toString();
    }

    private void getPath(StringBuilder sb, int curI, int curJ, int prevI, int prevJ) {
        //edge case 'z', to reach z, need go left first then go down.
        //z to other char, need go up then go right;
        // L -> D, U -> R;

        while (curJ < prevJ) {
            prevJ --;
            sb.append("L");
        }
        while (curI > prevI) {
            prevI ++;
            sb.append("D");
        }
        while (curI < prevI) {
            prevI --;
            sb.append("U");
        }
        while (curJ > prevJ) {
            prevJ ++;
            sb.append("R");
        }
    }
}