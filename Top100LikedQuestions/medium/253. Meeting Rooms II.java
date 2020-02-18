//https://leetcode.com/problems/meeting-rooms-ii/
//Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
//
//        Example 1:
//
//        Input: [[0, 30],[5, 10],[15, 20]]
//        Output: 2

class Solution {

    class myComparator implements Comparator<int[]> {
        public int compare(int[] i1, int[] i2) {
            return i1[1] - i2[1];
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // 先按照start时间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });

        //用一个min heap，确保结束时间早的一直在前面
        PriorityQueue<int[]> heap = new PriorityQueue<>(new myComparator());
        heap.add(intervals[0]);

        for (int i=1; i < intervals.length; i ++) {
            int[] cur = heap.peek();
            //当前结束时间比下一个开始时间小，就merge成一个，再加入heap
            if (cur[1] <= intervals[i][0]) {
                heap.poll();
                cur[1] = intervals[i][1];
                heap.add(cur);
            } else {
                //如果当前结束时间比下个开始时间大，就将下一个加入heap
                heap.add(intervals[i]);
            }
        }

        return heap.size();
    }
}