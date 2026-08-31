class Solution {
    static class Info {
        int size;
        List<Long> vals;

        Info(int size, List<Long> vals) {
            this.size = size;
            this.vals = vals;
        }
    }
    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
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

        long[] coin = new long[n];
        dfs(0, -1, graph, cost, coin);
        return coin;
    }

    private Info dfs(
            int u,
            int parent,
            List<Integer>[] graph,
            int[] cost,
            long[] coin) {

        int size = 1;
        List<Long> vals = new ArrayList<>();
        vals.add((long) cost[u]);
        for (int v : graph[u]) {
            if (v == parent) continue;

            Info child = dfs(v, u, graph, cost, coin);

            size += child.size;

            vals.addAll(child.vals);

            vals = keepExtremes(vals);
        }
        if (size < 3) {
            coin[u] = 1;
        } else {
            long maxProduct = Long.MIN_VALUE;
            for (int i = 0; i < vals.size(); i++) {
                for (int j = i + 1; j < vals.size(); j++) {
                    for (int k = j + 1; k < vals.size(); k++) {
                        long product =
                                vals.get(i)
                                    * vals.get(j)
                                    * vals.get(k);

                        maxProduct = Math.max(maxProduct, product);
                    }
                }
            }
            coin[u] = Math.max(0L, maxProduct);
        }
        return new Info(size, vals);
    }

    private List<Long> keepExtremes(List<Long> vals) {
        Collections.sort(vals);
        int n = vals.size();
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < Math.min(3, n); i++) {
            res.add(vals.get(i));
        }
        for (int i = Math.max(3, n - 3); i < n; i++) {
            res.add(vals.get(i));
        }

        return res;
    }
}