//https://leetcode.com/problems/minimum-knight-moves/
//In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
//
//        A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
//
//
//
//        Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
//
//
//
//        Example 1:
//
//        Input: x = 2, y = 1
//        Output: 1
//        Explanation: [0, 0] → [2, 1]

//solution: https://leetcode.com/problems/minimum-knight-moves/discuss/401580/Clean-Java-BFS-solution
class Solution {
    public int minKnightMoves(int x, int y) {
        int[][] dirs = new int[][] {{2, 1}, {2, -1}, {1, 2}, {1, -2},
                {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}};
        x = Math.abs(x);
        y = Math.abs(y);
        int count = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new int[]{0, 0});
        visited.add("0,0");

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.pop();
                if (cur[0] == x && cur[1] == y) {
                    return count;
                }

                for (int j = 0; j < 8; j++) {
                    int[] dir = dirs[j];
                    int new_x = cur[0] + dir[0];
                    int new_y = cur[1] + dir[1];
                    int[] next = new int[]{new_x, new_y};
                    //new_x >= -1 && new_y >= -1 is very tricy, to handle (1, 1)
                    if (!visited.contains(new_x + "," + new_y) && new_x >= -1 && new_y >= -1) {
                        queue.add(next);
                        visited.add(new_x + "," + new_y);
                    }

                }
            }
            count++;
        }

        return -1;
    }
}