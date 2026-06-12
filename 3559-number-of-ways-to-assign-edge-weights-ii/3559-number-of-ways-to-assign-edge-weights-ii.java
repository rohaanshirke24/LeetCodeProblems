class Solution {
    static final int MOD = 1_000_000_007;
    static final int LOG = 17; 

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        int log = 1;
        while ((1 << log) <= n) log++;
        int[][] up = new int[log][n + 1];
        int[] depth = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        vis[1] = true;
        up[0][1] = 0;
        depth[1] = 0;

        while (!dq.isEmpty()) {
            int u = dq.poll();
            for (int v : g[u]) {
                if (vis[v]) continue;
                vis[v] = true;
                depth[v] = depth[u] + 1;
                up[0][v] = u;
                dq.add(v);
            }
        }

        for (int j = 1; j < log; j++) {
            for (int i = 1; i <= n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        int maxK = n - 1;
        long[] pow2 = new long[maxK + 1];
        pow2[0] = 1;
        for (int i = 1; i <= maxK; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int u = queries[qi][0];
            int v = queries[qi][1];
            int l = lca(u, v, up, depth, log);
            int k = depth[u] + depth[v] - 2 * depth[l];
            ans[qi] = (k == 0) ? 0 : (int) pow2[k - 1];
        }

        return ans;
    }

    private int lca(int a, int b, int[][] up, int[] depth, int log) {
        if (depth[a] < depth[b]) {
            int t = a; a = b; b = t;
        }

        int diff = depth[a] - depth[b];
        for (int j = 0; j < log; j++) {
            if (((diff >> j) & 1) == 1) {
                a = up[j][a];
            }
        }

        if (a == b) return a;

        for (int j = log - 1; j >= 0; j--) {
            if (up[j][a] != up[j][b]) {
                a = up[j][a];
                b = up[j][b];
            }
        }

        return up[0][a];
    }
}