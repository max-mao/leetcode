//https://leetcode.com/problems/nested-list-weight-sum/
//Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
//
//        Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
//        Example 1:
//
//        Input: [[1,1],2,[1,1]]
//        Output: 10
//        Explanation: Four 1's at depth 2, one 2 at depth 1.

class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int res = 0;

        for (int i = 0; i < nestedList.size(); i++) {
            NestedInteger cur = nestedList.get(i);
            res += helper(cur, 1);
        }

        return res;
    }

    private int helper(NestedInteger ni, int level) {
        if (ni.isInteger()) {
            return ni.getInteger() * level;
        } else {
            List<NestedInteger> next_list = ni.getList();
            int res = 0;
            for (int i = 0; i < next_list.size(); i++) {
                res += helper(next_list.get(i), level + 1);
            }

            return res;
        }
    }
}