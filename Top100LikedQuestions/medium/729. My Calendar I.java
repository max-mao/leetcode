//https://leetcode.com/problems/my-calendar-i/
//Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
//
//        Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
//
//        A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
//
//        For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
//
//        Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
//        Example 1:
//
//        MyCalendar();
//        MyCalendar.book(10, 20); // returns true
//        MyCalendar.book(15, 25); // returns false
//        MyCalendar.book(20, 30); // returns true
//        Explanation:
//        The first event can be booked.  The second can't because time 15 is already booked by another event.
//        The third event can be booked, as the first event takes every time less than 20, but not including 20.

//solution: https://leetcode.com/problems/my-calendar-i/discuss/109475/JavaC%2B%2B-Clean-Code-with-Explanation

class MyCalendar {
    List<int[]> list;

    public MyCalendar() {
        list = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] time : list) {
            if (Math.max(time[0], start) < Math.min(time[1], end)) {
                return false;
            }
        }
        list.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */

//soluition 2:
class MyCalendar {
    TreeMap<Integer, Integer> map;

    public MyCalendar() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        java.util.Map.Entry<Integer, Integer> floor = map.floorEntry(start);
        java.util.Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(start);
        if (floor != null && floor.getValue() > start) {
            return false;
        }
        if (ceiling != null && ceiling.getKey() < end) {
            return false;
        }
        map.put(start, end);
        return true;
    }
}