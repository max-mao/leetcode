//https://leetcode.com/problems/exclusive-time-of-functions/
//On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
//
//        We store logs in timestamp order that describe when a function is entered or exited.
//
//        Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
//
//        A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
//
//        The CPU is single threaded which means that only one function is being executed at a given time unit.
//
//        Return the exclusive time of each function, sorted by their function id.

//solution: https://leetcode.com/problems/exclusive-time-of-functions/discuss/105062/Java-Stack-Solution-O(n)-Time-O(n)-Space
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        // separate time to several intervals, add interval to their function
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        // pre means the start of the interval
        int prev = 0;

        for (String log : logs) {
            String[] cur = log.split(":");
            if (cur[1].equals("start")) {
                if (!stack.isEmpty()) {
                    // arr[2] is the start of next interval, doesn't belong to current interval.
                    res[stack.peek()] += Integer.parseInt(cur[2]) - prev;
                }
                stack.push(Integer.parseInt(cur[0]));
                prev = Integer.parseInt(cur[2]);
            } else {
                // arr[2] is end of current interval, belong to current interval. That's why we have +1 here
                res[stack.pop()] += Integer.parseInt(cur[2]) - prev +1;

                // pre means the start of next interval, so we need to +1
                prev = Integer.parseInt(cur[2]) + 1;
            }
        }

        return res;
    }
}

