//https://leetcode.com/problems/meeting-scheduler/
//Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
//
//If there is no common time slot that satisfies the requirements, return an empty array.
//
//The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
//
//It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
//
//
//
//Example 1:
//
//Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
//Output: [60,68]

class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        List<int[]> common = getCommonSlot(slots1, slots2);

        if (common.size() == 0) {
            return res;
        }

        for (int[] arr : common) {
            if (arr[1] - arr[0] >= duration) {
                res.add(arr[0]);
                res.add(arr[0] + duration);
                return res;
            }
        }

        return res;
    }

    private List<int[]> getCommonSlot(int[][] s1, int[][] s2) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(s1, (a, b) -> (a[0] - b[0]));
        Arrays.sort(s2, (a, b) -> (a[0] - b[0]));

        int index1 = 0;
        int index2 = 0;
        while(index1 < s1.length && index2 < s2.length) {
            int[] arr1 = s1[index1];
            int[] arr2 = s2[index2];

            if (arr1[0] > arr2[1]) {
                index2++;
                continue;
            }
            if (arr1[1] < arr2[0]) {
                index1++;
                continue;
            }
            int[] slot = new int[2];
            slot[0] = Math.max(arr1[0], arr2[0]);
            slot[1] = Math.min(arr1[1], arr2[1]);
            res.add(slot);
            if (arr1[1] > arr2[1]) {
                index2++;
            } else {
                index1++;
            }
        }

        return res;
    }
}