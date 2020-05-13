//https://leetcode.com/problems/range-sum-query-mutable/
//Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
//
//        The update(i, val) function modifies nums by updating the element at index i to val.
//
//        Example:
//
//        Given nums = [1, 3, 5]
//
//        sumRange(0, 2) -> 9
//        update(1, 2)
//        sumRange(0, 2) -> 8

//Binary index tree : https://leetcode.com/problems/range-sum-query-mutable/discuss/75766/Java-Binary-Indexed-Tree

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

class NumArray {
    //new_nums is the binary index tree array
    int[] new_nums;
    int[] nums;

    public NumArray(int[] nums) {
        this.new_nums = new int[nums.length + 1];
        this.nums = nums;
        for (int i =1; i < new_nums.length; i++) {
            updateSum(i, nums[i-1]);
        }
    }

    public void update(int i, int val) {
        updateSum(i+1, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        return sum(j+1) - sum(i);
    }

    /*
     * he
     * @param i
     * @return
     */
    private int sum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += new_nums[i];
            i -= lastBit(i);
        }
        return sum;
    }

    private void updateSum(int i, int delta) {
        while(i < new_nums.length) {
            new_nums[i] += delta;
            i += lastBit(i);
        }
    }

    private int lastBit(int i) {
        return i &(- i);
    }
}