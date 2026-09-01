class Solution {
    private List<List<Integer>> tree;
    private int[] coins;
    private int k;
    private Integer[][] dp;

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        this.coins = coins;
        this.k = k;
        this.tree = new ArrayList<>();
        for (int i = 0; i < n; i++) tree.add(new ArrayList<>());
        for (int[] e : edges) {
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }
        this.dp = new Integer[n][15];
        return dfs(0, 0, -1);
    }

    private int dfs(int node, int level, int parent) {
        if (level >= 14) return 0; 
        if (dp[node][level] != null) return dp[node][level];

        int effective = coins[node] >> level;
        int way1 = effective - k;
        for (int child : tree.get(node)) {
            if (child != parent) {
                way1 += dfs(child, level, node);
            }
        }
        int way2 = effective >> 1;
        for (int child : tree.get(node)) {
            if (child != parent) {
                way2 += dfs(child, level + 1, node);
            }
        }

        dp[node][level] = Math.max(way1, way2);
        return dp[node][level];
    }
}
