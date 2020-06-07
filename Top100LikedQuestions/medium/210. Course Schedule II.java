////https://leetcode.com/problems/course-schedule-ii/
//There are a total of n courses you have to take, labeled from 0 to n-1.
//
//        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//        Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//        There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int[] edge : prerequisites) {
            indegree[edge[0]] ++;
        }

        buildGraph(prerequisites, graph);

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i =0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
            Set<Integer> children = graph.get(cur);
            if (children == null) {
                continue;
            }
            for (int child : children) {
                indegree[child] --;
                if (indegree[child] == 0) {
                    queue.add(child);
                }
            }
        }

        if (result.size() < numCourses) {
            return new int[0];
        }
        return  result.stream().mapToInt(i->i).toArray();
    }

    private void buildGraph(int[][] prerequisites, Map<Integer, Set<Integer>> graph) {
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            if (graph.containsKey(from)) {
                graph.get(from).add(to);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(to);
                graph.put(from, set);
            }
        }
    }
}