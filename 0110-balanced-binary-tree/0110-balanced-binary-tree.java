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
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // Check if the height difference between left and right subtrees is not more than 1
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }
        // Recursively check ifboth left and right subtrees are balanced
        return isBalanced(root.left) && isBalanced(root.right);
    }

    // Helper function to calculate the height of a subtree
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // Height of the subtree is the maximum height of its left and right subtrees + 1
        return 1 + Math.max(height(node.left), height(node.right));
    }
}