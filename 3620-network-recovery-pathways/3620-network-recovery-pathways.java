class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        int[] indegree = new int[n];
        int maxCost = 0;
        
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[]{v, w});
            indegree[v]++;
            maxCost = Math.max(maxCost, w);
        }
        
        int[] topo = buildTopo(graph, indegree, n);
        
        if (!feasible(0, graph, topo, online, k)) return -1;
        
        int lo = 0, hi = maxCost;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (feasible(mid, graph, topo, online, k)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        
        return lo;
    }
    
    private int[] buildTopo(ArrayList<int[]>[] graph, int[] indegree, int n) {
        int[] deg = indegree.clone();
        int[] topo = new int[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) q.offer(i);
        }
        
        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] e : graph[u]) {
                int v = e[0];
                if (--deg[v] == 0) q.offer(v);
            }
        }
        
        return topo;
    }
    
    private boolean feasible(int minEdge, ArrayList<int[]>[] graph, int[] topo, boolean[] online, long k) {
        int n = online.length;
        int target = n - 1;
        long INF = Long.MAX_VALUE / 4;
        
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        
        for (int u : topo) {
            if (dist[u] == INF || dist[u] > k) continue;
            if (u != 0 && !online[u]) continue;
            
            for (int[] e : graph[u]) {
                int v = e[0], w = e[1];
                
                if (w < minEdge) continue;
                if (v != target && !online[v]) continue;
                
                long nd = dist[u] + w;
                if (nd < dist[v] && nd <= k) {
                    dist[v] = nd;
                }
            }
        }
        
        return dist[target] <= k;
    }
}