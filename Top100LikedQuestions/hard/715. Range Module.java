//https://leetcode.com/problems/range-module/
//A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.
//
//addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
//queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.
//removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).
//Example 1:
//addRange(10, 20): null
//removeRange(14, 16): null
//queryRange(10, 14): true (Every number in [10, 14) is being tracked)
//queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
//queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)

//solution: Treemap: https://leetcode.com/problems/range-module/discuss/108910/Java-TreeMap
class RangeModule {
    TreeMap<Integer, Integer> map;

    public RangeModule() {
        this.map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);

        if (start != null && map.get(start) >= left) {
            left = start;
        }

        if (end != null && map.get(end) > right) {
            right = map.get(end);
        }

        map.put(left, right);
        map.subMap(left, false, right, true).clear();
    }

    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        if (start == null) {
            return false;
        }
        return map.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);

        if (end != null && map.get(end) > right) {
            map.put(right, map.get(end));
        }

        if (start != null && map.get(start) > left) {
            map.put(start, left);
        }
        map.subMap(left, true, right, false).clear();
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */