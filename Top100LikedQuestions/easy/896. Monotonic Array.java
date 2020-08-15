//https://leetcode.com/problems/monotonic-array/
//An array is monotonic if it is either monotone increasing or monotone decreasing.
//
//        An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
//
//        Return true if and only if the given array A is monotonic.
//
//
//
//        Example 1:
//
//        Input: [1,2,2,3]
//        Output: true

class Solution {
    public boolean isMonotonic(int[] A) {
        int pre = A[0];
        boolean isIncreae = false;
        if (A[0] > A[A.length -1]) {
            isIncreae = false;
        } else if (A[0] < A[A.length -1]) {
            isIncreae = true;
        } else {
            for (int i = 0; i < A.length; i++) {
                if (A[i] != pre) {
                    return false;
                }
                pre = A[i];
            }
        }

        if (isIncreae) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] < pre) {
                    return false;
                }
                pre = A[i];
            }
        } else {
            for (int i = 0; i < A.length; i++) {
                if (A[i] > pre) {
                    return false;
                }
                pre = A[i];
            }
        }

        return true;
    }
}