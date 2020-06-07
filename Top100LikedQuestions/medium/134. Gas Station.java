//https://leetcode.com/problems/gas-station/
//There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
//        You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
//
//        Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
//
//        Note:
//
//        If there exists a solution, it is guaranteed to be unique.
//        Both input arrays are non-empty and have the same length.
//        Each element in the input arrays is a non-negative integer.

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int total_gas = 0;
        int cur_gas = 0;

        for (int i =0; i < gas.length; i ++) {
            cur_gas += gas[i] - cost[i];
            if (cur_gas < 0) {
                start = i +1;
                cur_gas = 0;
            }

            total_gas += gas[i] - cost[i];
        }

        return total_gas >= 0? start: -1;

    }
}