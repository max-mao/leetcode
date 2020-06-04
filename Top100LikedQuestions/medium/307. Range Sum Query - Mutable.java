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

//Segment tree: https://www.youtube.com/watch?v=rYBtViWXYeI
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

class NumArray {

    class SegmentTreeNode {
        int start;
        int end;
        SegmentTreeNode left;
        SegmentTreeNode right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }

    SegmentTreeNode root;

    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length-1);
    }

    public void update(int i, int val) {
        update(root, i, val);
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            SegmentTreeNode root = new SegmentTreeNode(start, end);
            root.sum = nums[start];
            return root;
        }

        int mid = start + (end - start)/2;
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        root.left = buildTree(nums, start, mid);
        root.right = buildTree(nums, mid+1, end);
        root.sum = root.left.sum + root.right.sum;

        return root;
    }

    private void update(SegmentTreeNode root, int index, int val) {
        if (root.start == index && root.end == index) {
            root.sum = val;
            return;
        }

        int mid = root.start + (root.end - root.start)/2;
        if (index <= mid) {
            update(root.left, index, val);
        } else {
            update(root.right, index, val);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    private int sumRange(SegmentTreeNode root, int i, int j) {
        if (root.start == i && root.end == j) {
            return root.sum;
        }

        int mid = root.start + (root.end - root.start)/2;
        if (j <= mid) {
            return sumRange(root.left, i, j);
        } else if (i > mid) {
            return sumRange(root.right, i, j);
        } else {
            return sumRange(root.left, i, mid) + sumRange(root.right, mid+1, j);
        }
    }
}
