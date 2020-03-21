//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
//Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
//        Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
//        Example:
//
//        You may serialize the following tree:
//
//        1
//        / \
//        2   3
//        / \
//        4   5
//
//        as "[1,2,3,null,null,4,5]"
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                sb.append(cur.val);
                queue.add(cur.left);
                queue.add(cur.right);
            } else {
                sb.append("null");
            }

            if (!queue.isEmpty()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null ||data == "") {
            return null;
        }
        String[] strings = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeft = true;
        for (int i = 1; i < strings.length; i++) {
            if (!strings[i].equals("null")) {
                TreeNode cur = queue.peek();
                TreeNode child = new TreeNode(Integer.parseInt(strings[i]));
                if (isLeft) {
                    cur.left = child;
                } else {
                    cur.right = child;
                }
                queue.add(child);
            }
            if (isLeft == false) {
                queue.poll();
            }
            isLeft = !isLeft;
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));