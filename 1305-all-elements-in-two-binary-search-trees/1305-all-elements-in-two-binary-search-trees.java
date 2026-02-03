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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> s1 = new ArrayDeque<>();
        Deque<TreeNode> s2 = new ArrayDeque<>();

        pushLeft(root1, s1);
        pushLeft(root2, s2);

        while (!s1.isEmpty() || !s2.isEmpty()) {
            Deque<TreeNode> pick;

            if (s2.isEmpty()) {
                pick = s1;
            } else if (s1.isEmpty()) {
                pick = s2;
            } else {
                pick = (s1.peek().val <= s2.peek().val) ? s1 : s2;
            }

            TreeNode node = pick.pop();
            ans.add(node.val);
            pushLeft(node.right, pick);
        }

        return ans;
    }

    private void pushLeft(TreeNode node, Deque<TreeNode> stack) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}