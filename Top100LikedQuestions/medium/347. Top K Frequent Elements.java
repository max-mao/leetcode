//https://leetcode.com/problems/top-k-frequent-elements/

//Given a non-empty array of integers, return the k most frequent elements.
//
//        Example 1:
//
//        Input: nums = [1,1,1,2,2,3], k = 2
//        Output: [1,2]
//        Example 2:
//
//        Input: nums = [1], k = 1
//        Output: [1]
class Solution {
    class Pair {
        int key;
        int frequency;
        public Pair (int key, int frequency) {
            this.key = key;
            this.frequency = frequency;
        }
    }

    class MyComparator implements Comparator<Pair> {
        public int compare(Pair p1, Pair p2) {
            return p2.frequency - p1.frequency;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        Map<Integer, Pair> map = new HashMap<>();
        for (int i=0; i < nums.length; i ++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).frequency += 1;
            } else {
                map.put(nums[i], new Pair(nums[i], 1));
            }
        }

        PriorityQueue<Pair> heap = new PriorityQueue<>(new MyComparator());
        for (Map.Entry<Integer, Pair> e : map.entrySet()) {
            heap.add(e.getValue());
        }

        for (int i = 0 ; i < k; i++) {
            result.add(heap.poll().key);
        }

        return result;
    }
}