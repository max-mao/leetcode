//https://leetcode.com/problems/optimal-account-balancing/
//A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
//
//Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
//
//Note:
//
//A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
//Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
//Example 1:
//
//Input:
//[[0,1,10], [2,0,5]]
//
//Output:
//2
//
//Explanation:
//Person #0 gave person #1 $10.
//Person #2 gave person #0 $5.
//
//Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

//solution: https://leetcode.com/problems/optimal-account-balancing/discuss/130895/Recursion-Logical-Thinking
class Solution {
    public int minTransfers(int[][] transactions) {
        int[] debt = buildDebt(transactions);

        return calculate(debt, 0);
    }

    private int[] buildDebt(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] trans : transactions) {
            int from = trans[0];
            int to = trans[1];
            int amount = trans[2];
            map.put(from, map.getOrDefault(from, 0) + amount);
            map.put(to, map.getOrDefault(to, 0) - amount);
        }

        int[] res = new int[map.size()];
        int i = 0;
        for (int amount : map.values()) {
            res[i++] = amount;
        }
        return res;
    }

    private int calculate(int[] debt, int curIndex) {
        while(curIndex < debt.length && debt[curIndex] == 0) {
            curIndex++;
        }
        if (curIndex == debt.length) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = curIndex + 1; i < debt.length; i++) {
            if (debt[i] * debt[curIndex] < 0) {
                debt[i] += debt[curIndex];
                res = Math.min(res, 1 + calculate(debt, curIndex+1));
                debt[i] -= debt[curIndex];
            }
        }

        return res;
    }
}