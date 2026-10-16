class Solution {
    private static final int MOD = 1_000_000_007;

    private long[] treeSum;
    private long[] treeSumSq;
    private long[] lazy;
    private void apply(int node, int len, long delta) {
        delta %= MOD;
        long s = treeSum[node];
        long sq = treeSumSq[node];

        long term1 = (2 * delta) % MOD * s % MOD;
        long term2 = (delta * delta) % MOD * (len % MOD) % MOD;

        treeSumSq[node] = (sq + term1 + term2) % MOD;
        treeSum[node] = (s + delta * (len % MOD)) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }
    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            apply(2 * node, mid - l + 1, lazy[node]);
            apply(2 * node + 1, r - mid, lazy[node]);
            lazy[node] = 0;
        }
    }
    private void pushUp(int node) {
        treeSum[node] = (treeSum[2 * node] + treeSum[2 * node + 1]) % MOD;
        treeSumSq[node] = (treeSumSq[2 * node] + treeSumSq[2 * node + 1]) % MOD;
    }
    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, r - l + 1, val);
            return;
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val);
        }
        pushUp(node);
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        treeSum = new long[4 * n];
        treeSumSq = new long[4 * n];
        lazy = new long[4 * n];
        int maxVal = 0;
        for (int x : nums) {
            if (x > maxVal) maxVal = x;
        }
        int[] lastPos = new int[maxVal + 1];
        Arrays.fill(lastPos, -1);

        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int prev = lastPos[x];
            int L = prev + 1;
            int R = i;
            update(1, 0, n - 1, L, R, 1);
            lastPos[x] = i;
            totalSum = (totalSum + treeSumSq[1]) % MOD;
        }

        return (int) totalSum;
    }
}