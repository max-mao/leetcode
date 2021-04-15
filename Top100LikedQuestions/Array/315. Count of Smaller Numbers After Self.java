//https://leetcode.com/problems/count-of-smaller-numbers-after-self/
//You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
//
//
//
//Example 1:
//
//Input: nums = [5,2,6,1]
//Output: [2,1,1,0]
//Explanation:
//To the right of 5 there are 2 smaller elements (2 and 1).
//To the right of 2 there is only 1 smaller element (1).
//To the right of 6 there is 1 smaller element (1).
//To the right of 1 there is 0 smaller element.

class Solution {

    class Item {
        int val;
        int index;
        public Item (int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        Item[] items = new Item[len];
        for (int i = 0; i < nums.length; i++) {
            items[i] = new Item(nums[i], i);
        }

        int[] count = new int[len];
        mergeSort(items, 0, len -1, count);

        List<Integer> res = new ArrayList<>();
        for (int i : count) {
            res.add(i);
        }
        return res;
    }

    private void mergeSort(Item[] items, int start, int end, int[] count) {
        if (start >= end) return;
        int mid = start + (end - start)/2;
        mergeSort(items, start, mid, count);
        mergeSort(items, mid + 1, end, count);
        merge(items, start, mid, mid+1, end, count);
    }

    private void merge(Item[] items, int leftStart, int leftEnd,
                       int rightStart, int rightEnd, int[] count) {
        int len = rightEnd - leftStart +1;
        Item[] sorted = new Item[len];

        int leftPoint = leftStart, rightPoint = rightStart;
        int idx = 0;
        int rightCount = 0;
        while (leftPoint <= leftEnd && rightPoint <= rightEnd) {
            if (items[leftPoint].val > items[rightPoint].val) {
                rightCount++;
                sorted[idx++] = items[rightPoint++];
            } else {
                count[items[leftPoint].index] += rightCount;
                sorted[idx++] = items[leftPoint++];
            }
        }

        while (leftPoint <= leftEnd) {
            count[items[leftPoint].index] += rightCount;
            sorted[idx++] = items[leftPoint++];
        }

        while (rightPoint <= rightEnd) {
            sorted[idx++] = items[rightPoint++];
        }

        System.arraycopy(sorted, 0, items, leftStart, len);
    }
}