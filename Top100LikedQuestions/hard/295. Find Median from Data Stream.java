//https://leetcode.com/problems/find-median-from-data-stream/
//Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
//
//        For example,
//        [2,3,4], the median is 3
//
//        [2,3], the median is (2 + 3) / 2 = 2.5
//
//        Design a data structure that supports the following two operations:
//
//        void addNum(int num) - Add a integer number from the data stream to the data structure.
//        double findMedian() - Return the median of all elements so far.
//
//
//        Example:
//
//        addNum(1)
//        addNum(2)
//        findMedian() -> 1.5
//        addNum(3)
//        findMedian() -> 2

class MedianFinder {
    //用两个heap track，small track小的那一半，用最大堆; large track大的那一半，用最小堆
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;

    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue(new Comparator<Integer>() {
            public int compare(Integer n1, Integer n2) {
                return n2 - n1;
            };
        });

        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        //large heap加一个数，然后将large heap的那一半最小的数加到small heap
        large.add(num);
        small.add(large.poll());

        if (large.size() < small.size()) {
            large.add(small.poll());
        }
    }

    public double findMedian() {
        if (large.size() == small.size()) {
            return  (large.peek() + small.peek())/2.0;
        } else {
            return large.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */