https://leetcode.com/problems/queue-reconstruction-by-height/
//Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        class myComparator implements Comparator<int[]> {
            public int compare(int[] i1, int[] i2) {
                if (i1[0] != i2[0]) {
                    return i2[0] - i1[0];
                } else {
                    return i1[1] - i2[1];
                }
            }
        }

        Arrays.sort(people, new myComparator());

        LinkedList<int[]> list = new LinkedList<>();

        for (int i=0; i < people.length; i++) {
            list.add(people[i][1], people[i]);
        }

        int[][] result = list.toArray(new int[people.length][]);
        return result;
    }
}
