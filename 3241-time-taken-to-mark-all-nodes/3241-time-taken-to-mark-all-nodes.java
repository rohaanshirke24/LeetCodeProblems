class Solution {
    public int[] timeTaken(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] order = new int[n];
        int idx = 0;
        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 0;
        parent[0] = -2;

        while (top > 0) {
            int u = stack[--top];
            order[idx++] = u;

            for (int v : graph[u]) {
                if (v == parent[u]) continue;
                parent[v] = u;
                stack[top++] = v;
            }
        }
        int[] down = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            for (int v : graph[u]) {
                if (parent[v] != u) continue;

                int costToV = (v % 2 == 1) ? 1 : 2;
                down[u] = Math.max(down[u], costToV + down[v]);
            }
        }
        int[] up = new int[n];
        for (int i = 0; i < n; i++) {
            int u = order[i];

            int best1 = -1;
            int best2 = -1;
            int bestChild = -1;

            for (int v : graph[u]) {
                if (parent[v] != u) continue;

                int costToV = (v % 2 == 1) ? 1 : 2;
                int value = costToV + down[v];

                if (value > best1) {
                    best2 = best1;
                    best1 = value;
                    bestChild = v;
                } else if (value > best2) {
                    best2 = value;
                }
            }

            for (int v : graph[u]) {
                if (parent[v] != u) continue;
                int bestOutside;

                if (bestChild == v) {
                    bestOutside = Math.max(up[u], best2);
                } else {
                    bestOutside = Math.max(up[u], best1);
                }
                int costToU = (u % 2 == 1) ? 1 : 2;

                up[v] = costToU + bestOutside;
            }
        }
        int[] times = new int[n];

        for (int u = 0; u < n; u++) {
            times[u] = Math.max(down[u], up[u]);
        }

        return times;
    }
}