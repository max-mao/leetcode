//https://leetcode.com/problems/jump-game-iv/
//Given an array of integers arr, you are initially positioned at the first index of the array.
//
//In one step you can jump from index i to index:
//
//i + 1 where: i + 1 < arr.length.
//i - 1 where: i - 1 >= 0.
//j where: arr[i] == arr[j] and i != j.
//Return the minimum number of steps to reach the last index of the array.
//
//Notice that you can not jump outside of the array at any time.
//
//
//
//Example 1:
//
//Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
//Output: 3
//Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
class Solution {
    public int minJumps(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> map = buildGraph(arr);
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == arr.length - 1) {
                    return step;
                }
                List<Integer> nextNodes = map.get(arr[cur]);
                if (cur-1 > 0) {
                    nextNodes.add(cur-1);
                }
                if (cur +1< arr.length) {
                    nextNodes.add(cur+1);
                }
                for (int next : nextNodes) {
                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
                }
                // Since we have already taken all indexes into account, we don't need to traverse them again.
                // Consider example: [1,1,1,1,1,1,.....(5000 terms), 11] -> Answer =2;
                nextNodes.clear();
            }
            step++;
        }

        return -1;
    }

    private Map<Integer, List<Integer>> buildGraph(int[] arr) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            result.computeIfAbsent(arr[i], k-> new LinkedList<Integer>()).add(i);
        }

        return result;
    }
}