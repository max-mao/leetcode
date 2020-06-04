//https://leetcode.com/problems/flatten-2d-vector/
//Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.
//
//
//
//        Example:
//
//        Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
//
//        iterator.next(); // return 1
//        iterator.next(); // return 2
//        iterator.next(); // return 3
//        iterator.hasNext(); // return true
//        iterator.hasNext(); // return true
//        iterator.next(); // return 4
//        iterator.hasNext(); // return false

class Vector2D {
    int[][] arrs;
    int i;
    int j;

    public Vector2D(int[][] v) {
        arrs = v;
        i =0;
        j = 0;
    }

    public int next() {
        if (hasNext()) {
            return arrs[i][j++];
        }
        else return -1;
    }

    public boolean hasNext() {
        while (i < arrs.length && j == arrs[i].length) {
            i++;
            j =0;
        }

        return i < arrs.length;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */