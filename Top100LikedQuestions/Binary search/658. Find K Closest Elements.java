//https://leetcode.com/problems/find-k-closest-elements/
//Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
//
//An integer a is closer to x than an integer b if:
//
//|a - x| < |b - x|, or
//|a - x| == |b - x| and a < b
//
//
//Example 1:
//
//Input: arr = [1,2,3,4,5], k = 4, x = 3
//Output: [1,2,3,4]

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();
        if (arr == null) {
            return res;
        }

        int index = findClosest(arr, x);
        int start = index - k + 1 >= 0 ? index - k + 1 : 0;
        int end = index + k - 1 <= arr.length-1 ? index + k - 1 : arr.length-1;

        for (int i = start; i <= end; i++) {
            if (res.size() == k) {
                if (Math.abs(arr[i] - x) < Math.abs(res.peekFirst() - x)) {
                    res.removeFirst();
                    res.add(arr[i]);
                } else {
                    return res;
                }
            } else {
                res.add(arr[i]);
            }
        }

        return res;

    }

    private int findClosest(int[] arr, int x) {
        int start = 0;
        int end = arr.length-1;

        while (start + 1 < end) {
            int mid = start + (end -start)/2;
            if (x == arr[mid]) {
                return mid;
            } else if (x > arr[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (Math.abs(arr[start] - x) <= Math.abs(arr[end] - x)) {
            return start;
        }
        return end;
    }
}