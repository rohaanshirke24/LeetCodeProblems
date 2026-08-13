class Solution {
    
    static class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int maxLength;
        int length;

        Node(char ch) {
            this.leftChar = ch;
            this.rightChar = ch;
            this.prefix = 1;
            this.suffix = 1;
            this.maxLength = 1;
            this.length = 1;
        }
    }

    private char[] chars;
    private Node[] tree;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryCharacters.length();
        chars = s.toCharArray();
        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int index = queryIndices[i];
            char newChar = queryCharacters.charAt(i);
            if (chars[index] != newChar) {
                chars[index] = newChar;
                update(1, 0, n - 1, index, newChar);
            }
            result[i] = tree[1].maxLength;
        }
        return result;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int start, int end, int index, char ch) {
        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }
        int mid = start + (end - start) / 2;
        if (index <= mid) {
            update(node * 2, start, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, end, index, ch);
        }
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node left, Node right) {
        Node merged = new Node(left.leftChar);
        merged.length = left.length + right.length;
        merged.leftChar = left.leftChar;
        merged.rightChar = right.rightChar;
        merged.prefix = left.prefix;
        if (left.prefix == left.length && left.rightChar == right.leftChar) {
            merged.prefix = left.length + right.prefix;
        }
        merged.suffix = right.suffix;
        if (right.suffix == right.length && left.rightChar == right.leftChar) {
            merged.suffix = right.length + left.suffix;
        }
        merged.maxLength = Math.max(left.maxLength, right.maxLength);
        if (left.rightChar == right.leftChar) {
            merged.maxLength = Math.max(
                merged.maxLength,
                left.suffix + right.prefix
            );
        }

        return merged;
    }
}