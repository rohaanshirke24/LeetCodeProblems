/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return false;

        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> parentQ = new LinkedList<>();

        q.add(root);
        parentQ.add(null);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode px = null, py = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                TreeNode parent = parentQ.poll();

                if (node.val == x)
                    px = parent;
                if (node.val == y)
                    py = parent;

                if (node.left != null) {
                    q.add(node.left);
                    parentQ.add(node);
                }
                if (node.right != null) {
                    q.add(node.right);
                    parentQ.add(node);
                }
            }
            if (px != null || py != null) {
                return px != null && py != null && px != py;
            }
        }

        return false;
    }
}