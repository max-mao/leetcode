//https://leetcode.com/problems/robot-room-cleaner/
//Given a robot cleaner in a room modeled as a grid.
//
//        Each cell in the grid can be empty or blocked.
//
//        The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
//
//        When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
//
//        Design an algorithm to clean the entire room using only the 4 given APIs shown below.
//
//interface Robot {
//    // returns true if next cell is open and robot moves into the cell.
//    // returns false if next cell is obstacle and robot stays on the current cell.
//    boolean move();
//
//    // Robot will stay on the same cell after calling turnLeft/turnRight.
//    // Each turn will be 90 degrees.
//    void turnLeft();
//    void turnRight();
//
//    // Clean the current cell.
//    void clean();
//}

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */


//solution: https://www.youtube.com/watch?v=y4izHfShEfU

class Solution {
    int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    Set<String> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {
        helper (0, 0, 0, robot);
    }

    private void helper(int i, int j, int dir, Robot robot) {
        robot.clean();
        visited.add(i + "_" + j);

        for (int k = 0; k < 4; k++) {
            int new_dir = (dir + k) % 4;
            int new_i = dirs[new_dir][0] + i;
            int new_j = dirs[new_dir][1] + j;
            if (!visited.contains(new_i + "_" + new_j) && robot.move()) {
                helper(new_i, new_j, new_dir, robot);
                backtrack(robot);
            }
            robot.turnRight();
        }
    }

    private void backtrack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}