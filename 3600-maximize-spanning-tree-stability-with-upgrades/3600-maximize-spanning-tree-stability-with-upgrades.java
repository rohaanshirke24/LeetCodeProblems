class Solution {
    static class DSU {
        int[] parent;
        int[] rank;
        int components;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;

            if (rank[pa] < rank[pb]) {
                parent[pa] = pb;
            } else if (rank[pa] > rank[pb]) {
                parent[pb] = pa;
            } else {
                parent[pb] = pa;
                rank[pa]++;
            }
            components--;
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int maxStrength = 0;
        for (int[] e : edges) {
            maxStrength = Math.max(maxStrength, e[2]);
        }

        if (!can(n, edges, k, 1)) return -1;

        int lo = 1, hi = maxStrength * 2, ans = 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (can(n, edges, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int n, int[][] edges, int k, int target) {
        DSU dsu = new DSU(n);
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (s < target) return false;
                if (!dsu.union(u, v)) return false; 
            }
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0 && s >= target) {
                dsu.union(u, v);
            }
        }
        int usedUpgrades = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0 && s < target && 2 * s >= target) {
                if (dsu.union(u, v)) {
                    usedUpgrades++;
                    if (usedUpgrades > k) return false;
                }
            }
        }

        return dsu.components == 1;
    }
}
