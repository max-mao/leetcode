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

//preorder, solution: https://www.youtube.com/watch?v=suj1ro8TIVY
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }

        return root.val +"," + serialize(root.left) +"," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(",")));

        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(LinkedList<String> queue) {
        String node_val = queue.poll();
        if (node_val.equals("X")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(node_val));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);

        return root;
    }
}