class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        if (n == 1) return m;
        if (n == 2) return (int) ((long) m * (m - 1) % MOD);

        long[] up = new long[m];
        long[] down = new long[m];

        for (int v = 0; v < m; v++) {
            up[v] = v;             
            down[v] = m - 1 - v;   
        }

        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m];
            long[] newDown = new long[m];

            long prefixDown = 0;
            for (int v = 0; v < m; v++) {
                newUp[v] = prefixDown;
                prefixDown = (prefixDown + down[v]) % MOD;
            }

            long suffixUp = 0;
            for (int v = m - 1; v >= 0; v--) {
                newDown[v] = suffixUp;
                suffixUp = (suffixUp + up[v]) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int v = 0; v < m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}