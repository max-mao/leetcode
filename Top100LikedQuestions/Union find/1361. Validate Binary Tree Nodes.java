//https://leetcode.com/problems/validate-binary-tree-nodes/
//You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
//
//If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
//
//Note that the nodes have no values and that we only use the node numbers in this problem.
//
//
//
//Example 1:
//
//
//Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
//Output: true
class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] parent = new int[n];
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int count = n;
        for (int i = 0; i < n; i++) {
            int p = i;
            if (leftChild[i] != -1) {
                if (visited.contains(leftChild[i])) {
                    return false;
                }
                visited.add(leftChild[i]);
                int left = leftChild[i];
                if (find(p, parent) == find(left, parent)) {
                    return false;
                }
                union(p, left, parent);
                count --;
            }
            if (rightChild[i] != -1) {
                if (visited.contains(rightChild[i])) {
                    return false;
                }
                visited.add(rightChild[i]);
                int right = rightChild[i];
                if (find(p, parent) == find(right, parent)) {
                    return false;
                }
                union(p, right, parent);
                count --;
            }
        }

        return count == 1;
    }

    private int find(int n, int[] parent) {
        int p = parent[n];
        if (p == n) {
            return p;
        }
        parent[n] = find(p, parent);
        return parent[n];
    }

    private void union(int a, int b, int[] parent) {
        int p_a = find(a, parent);
        int p_b = find(b, parent);
        if (p_a == p_b) {
            return;
        }
        parent[p_a] = p_b;
    }
}