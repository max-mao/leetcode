//https://leetcode.com/problems/task-scheduler/

//Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
//
//        However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
//
//        You need to return the least number of intervals the CPU will take to finish all the given tasks.
//
//
//
//        Example:
//
//        Input: tasks = ["A","A","A","B","B","B"], n = 2
//        Output: 8
//        Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null) {
            return 0;
        }

        // 一共26个字母，所以是26 长度的map
        int[] map = new int[26];
        // 统计每个task 次数，index是key
        for (int i=0; i < tasks.length; i++) {
            map[tasks[i] - 'A'] ++;
        }
        //对 task 次数排序
        Arrays.sort(map);
        //拿到最多的task，减一是loop次数
        int max_loop = map[25] - 1;

        //最多有这么多idle
        int total_idle = max_loop * n;
        // 将剩下task插入idle
        for (int i = 24; i >=0; i--) {
            total_idle -= Math.min(map[i], max_loop);
        }

        //如果idle为0，返回长度，如果不为0，返回长度加idle
        return total_idle > 0 ? total_idle + tasks.length : tasks.length;
    }
}