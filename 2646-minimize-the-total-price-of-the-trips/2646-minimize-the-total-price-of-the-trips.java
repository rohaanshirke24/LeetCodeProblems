class Solution {
    private List<Integer>[] graph;
    private int[] count;
    private int[] price;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        this.price = price;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        count = new int[n];
        for (int[] trip : trips) {
            int start = trip[0];
            int end = trip[1];
            markPath(start, end, -1);
        }
        int[][] dp = new int[n][2];
        dfs(0, -1, dp);
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += count[i] * price[i];
        }
        int maxSaving = Math.max(dp[0][0], dp[0][1]);
        return total - maxSaving;
    }
    private boolean markPath(int u, int target, int parent) {
        if (u == target) {
            count[u]++;
            return true;
        }

        for (int v : graph[u]) {
            if (v == parent) continue;

            if (markPath(v, target, u)) {
                count[u]++;
                return true;
            }
        }

        return false;
    }

    private void dfs(int u, int parent, int[][] dp) {
        dp[u][1] = count[u] * (price[u] / 2);
        dp[u][0] = 0;

        for (int v : graph[u]) {
            if (v == parent) continue;

            dfs(v, u, dp);
            dp[u][0] += Math.max(dp[v][0], dp[v][1]);
            dp[u][1] += dp[v][0];
        }
    }
}