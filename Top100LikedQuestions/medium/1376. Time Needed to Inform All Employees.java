//https://leetcode.com/problems/time-needed-to-inform-all-employees/
//A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the one with headID.
//
//Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.
//
//The head of the company wants to inform all the employees of the company of an urgent piece of news. He will inform his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.
//
//The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i] minutes, all his direct subordinates can start spreading the news).
//
//Return the number of minutes needed to inform all the employees about the urgent news.
//
//
//
//Example 1:
//
//Input: n = 1, headID = 0, manager = [-1], informTime = [0]
//Output: 0
//Explanation: The head of the company is the only employee in the company.

// solution: https://www.youtube.com/watch?v=7K9XW8KpHP0&ab_channel=codeExplainer
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for(int i = 0; i < manager.length; i++) {
            if (manager[i] != -1) {
                if (!tree.containsKey(manager[i])) {
                    tree.put(manager[i], new ArrayList<Integer>());
                }
                tree.get(manager[i]).add(i);
            }
        }

        return dfs(tree, informTime, headID);
    }

    private int dfs(Map<Integer, List<Integer>> tree, int[] informTime, int curId) {
        if (!tree.containsKey(curId)) {
            return 0;
        }
        int maxTime = 0;
        List<Integer> employees = tree.get(curId);

        for (int e : employees) {
            maxTime = Math.max(maxTime, dfs(tree, informTime, e));
        }

        return informTime[curId] + maxTime;
    }
}
