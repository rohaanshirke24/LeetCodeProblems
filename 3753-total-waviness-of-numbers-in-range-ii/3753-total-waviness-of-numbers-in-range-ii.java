class Solution {
    private static final int NONE = 10;

    private static class Node {
        long count; 
        long sum;   
        Node(long count, long sum) {
            this.count = count;
            this.sum = sum;
        }
    }

    private char[] digits;
    private Node[][][][][] memo; 

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x <= 0) return 0;

        digits = Long.toString(x).toCharArray();
        memo = new Node[digits.length + 1][2][3][11][11];

        return dfs(0, 1, 0, NONE, NONE).sum;
    }

    private Node dfs(int pos, int tight, int len, int a, int b) {
        if (pos == digits.length) {
            return new Node(1L, 0L);
        }

        if (memo[pos][tight][len][a][b] != null) {
            return memo[pos][tight][len][a][b];
        }

        int limit = (tight == 1) ? (digits[pos] - '0') : 9;
        long totalCount = 0L;
        long totalSum = 0L;

        for (int d = 0; d <= limit; d++) {
            int nextTight = (tight == 1 && d == limit) ? 1 : 0;
            Node child;

            if (len == 0) {
                if (d == 0) {
                    child = dfs(pos + 1, nextTight, 0, NONE, NONE);
                } else {
                    child = dfs(pos + 1, nextTight, 1, NONE, d);
                }
                totalCount += child.count;
                totalSum += child.sum;
            } else if (len == 1) {
                child = dfs(pos + 1, nextTight, 2, b, d);
                totalCount += child.count;
                totalSum += child.sum;
            } else {
                long add = ((b > a && b > d) || (b < a && b < d)) ? 1L : 0L;
                child = dfs(pos + 1, nextTight, 2, b, d);

                totalCount += child.count;
                totalSum += child.sum + add * child.count;
            }
        }

        return memo[pos][tight][len][a][b] = new Node(totalCount, totalSum);
    }
}