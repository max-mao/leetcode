//https://leetcode.com/problems/unique-binary-search-trees/

//Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

class Solution {
    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        }
        //result[i] 表示从 i个数的unique BST
        int[] result = new int[n+1];
        result[0] = 1;
        result[1] = 1;

        for (int i = 2; i <= n; i ++) {
            //表示从 1 到 i分别作为root
            for (int j = 1; j <= i; j++) {
                //result[j-1] 表示左子树，result[i-j] 表示右子树
                result[i] += result[j-1] * result[i-j];
            }
        }

        return result[n];
    }
}
