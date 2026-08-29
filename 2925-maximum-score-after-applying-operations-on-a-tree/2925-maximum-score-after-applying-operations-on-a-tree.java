class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        long totalSum = 0;
        for (int v : values) totalSum += v;
        long[] dp = new long[n];
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];
        int[] order = new int[n]; 
        int idx = 0;

        Arrays.fill(parent, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (visited[u]) continue;
            visited[u] = true;
            order[idx++] = u;
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    parent[v] = u;
                    stack.push(v);
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            long childSum = 0;
            boolean isLeaf = true;
            for (int v : adj.get(u)) {
                if (v != parent[u]) {
                    isLeaf = false;
                    childSum += dp[v];
                }
            }
            dp[u] = isLeaf ? values[u] : Math.min(values[u], childSum);
        }

        return totalSum - dp[0];
    }
}