class Solution {
    static class Node {
        Node[] child = new Node[26];
        int id = -1;
    }
    private static final long INF = (1L << 60);
    private Node root;
    private int idx;           
    private long[][] dist;   
    private char[] s, t;
    private Long[] memo;

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int m = cost.length;
        root = new Node();
        idx = 0;
        s = source.toCharArray();
        t = target.toCharArray();
        int V = m << 1;
        dist = new long[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int u = insert(original[i]);
            int v = insert(changed[i]);
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }
        for (int k = 0; k < idx; k++) {
            for (int i = 0; i < idx; i++) {
                if (dist[i][k] >= INF) continue;
                for (int j = 0; j < idx; j++) {
                    long nd = dist[i][k] + dist[k][j];
                    if (nd < dist[i][j]) dist[i][j] = nd;
                }
            }
        }

        memo = new Long[s.length];
        long ans = dfs(0);
        return ans >= INF ? -1 : ans;
    }

    private int insert(String w) {
        Node cur = root;
        for (int k = 0; k < w.length(); k++) {
            int c = w.charAt(k) - 'a';
            if (cur.child[c] == null) cur.child[c] = new Node();
            cur = cur.child[c];
        }
        if (cur.id == -1) cur.id = idx++;
        return cur.id;
    }

    private long dfs(int i) {
        if (i == s.length) return 0;
        if (memo[i] != null) return memo[i];

        long best = (s[i] == t[i]) ? dfs(i + 1) : INF;

        Node ps = root, pt = root;
        for (int j = i; j < s.length; j++) {
            ps = (ps == null) ? null : ps.child[s[j] - 'a'];
            pt = (pt == null) ? null : pt.child[t[j] - 'a'];
            if (ps == null || pt == null) break;

            if (ps.id != -1 && pt.id != -1) {
                long c = dist[ps.id][pt.id];
                if (c < INF) {
                    best = Math.min(best, c + dfs(j + 1));
                }
            }
        }

        memo[i] = best;
        return best;
    }
}
