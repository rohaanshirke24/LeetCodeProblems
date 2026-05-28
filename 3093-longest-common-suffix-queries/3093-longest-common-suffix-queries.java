class Solution {

    static class Node {
        Node[] next = new Node[26];
        int bestIndex = -1;  
        int bestLen = Integer.MAX_VALUE;
    }

    private void relax(Node node, int idx, int len) {
        if (len < node.bestLen || (len == node.bestLen && idx < node.bestIndex)) {
            node.bestLen = len;
            node.bestIndex = idx;
        }
    }

    private void insert(Node root, String word, int idx) {
        int len = word.length();
        Node cur = root;
        relax(cur, idx, len);

        for (int i = len - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';
            if (cur.next[c] == null) cur.next[c] = new Node();
            cur = cur.next[c];
            relax(cur, idx, len);
        }
    }

    private int query(Node root, String q) {
        Node cur = root;
        for (int i = q.length() - 1; i >= 0; i--) {
            int c = q.charAt(i) - 'a';
            if (cur.next[c] == null) break;
            cur = cur.next[c];
        }
        return cur.bestIndex;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Node root = new Node();

        for (int i = 0; i < wordsContainer.length; i++) {
            insert(root, wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = query(root, wordsQuery[i]);
        }
        return ans;
    }
}