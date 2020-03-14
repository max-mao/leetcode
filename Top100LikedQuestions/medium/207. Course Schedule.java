//207. Course Schedule

//There are a total of n courses you have to take, labeled from 0 to n-1.
//
//        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//        Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
//        Example 1:
//
//        Input: 2, [[1,0]]
//        Output: true
//        Explanation: There are a total of 2 courses to take.
//        To take course 1 you should have finished course 0. So it is possible.

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 ||
                prerequisites[0].length == 0) {
            return true;
        }

        int[] indegree = new int[numCourses];
        for (int i =0; i < prerequisites.length;i ++) {
            indegree[prerequisites[i][0]] ++;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        Map<Integer, Set<Integer>> graphNode = getGraphNode(prerequisites);
        Set<Integer> resultSet = new HashSet<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            resultSet.add(cur);
            Set<Integer> nextNode = graphNode.get(cur);
            if (nextNode == null) {
                continue;
            }
            for(int i : nextNode) {
                indegree[i] --;
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }
        }

        return resultSet.size() == numCourses;
    }

    private Map<Integer, Set<Integer>> getGraphNode(int[][] prerequisites) {
        Map<Integer, Set<Integer>> result = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i ++) {
            if (result.containsKey(prerequisites[i][1])) {
                result.get(prerequisites[i][1]).add(prerequisites[i][0]);
            } else {
                Set<Integer> nextNodes = new HashSet<>();
                nextNodes.add(prerequisites[i][0]);
                result.put(prerequisites[i][1], nextNodes);
            }
        }

        return result;
    }
}