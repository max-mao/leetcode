//https://leetcode.com/problems/open-the-lock/

//You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
//
//        The lock initially starts at '0000', a string representing the state of the 4 wheels.
//
//        You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
//
//        Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
//


// bFS
class Solution {
    public int openLock(String[] deadends, String target) {
        int count = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add("0000");
        visited.add("0000");

        Set<String> set = new HashSet<>();
        for (String str : deadends) {
            set.add(str);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if(cur.equals(target.toString())) {
                    return count;
                }
                if (set.contains(cur)) {
                    continue;
                }

                List<String> nt = getNext(cur);
                for (String next : nt) {
                    if (visited.contains(next) || set.contains(next))
                        continue;
                    queue.offer(next);
                    visited.add(next);
                }

            }
            count++;
        }

        return -1;
    }

    private List<String> getNext(String prev) {
        List<String> nt = new ArrayList<>();
        for (int i = 0; i < prev.length(); i++) {
            int wheel = prev.charAt(i) - '0';
            nt.add(prev.substring(0, i) + String.valueOf((10 + wheel + 1) % 10) +
                    prev.substring(i+1));
            nt.add(prev.substring(0, i) + String.valueOf((10 + wheel - 1) % 10) +
                    prev.substring(i+1));
        }
        return nt;
    }
}