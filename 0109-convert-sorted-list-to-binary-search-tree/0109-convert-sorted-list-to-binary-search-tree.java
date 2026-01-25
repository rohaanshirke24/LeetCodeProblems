class Solution {
    private ListNode curr;

    public TreeNode sortedListToBST(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;
        curr = head;
        return build(n);
    }
    private TreeNode build(int n) {
        if (n <= 0) return null;

        TreeNode left = build(n / 2);

        TreeNode root = new TreeNode(curr.val);
        curr = curr.next;

        root.left = left;
        root.right = build(n - n / 2 - 1);

        return root;
    }
}
