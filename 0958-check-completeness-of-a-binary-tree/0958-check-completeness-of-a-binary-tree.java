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
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean nullSeen = false;
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                nullSeen = true;
            } else {
                if (nullSeen) {
                    return false;
                }
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        
        return true;
    }
}