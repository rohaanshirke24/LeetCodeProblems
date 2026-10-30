class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (n == 1) {
            return 0;
        }

        char[] labelArr = labels.toCharArray();
        List<int[]>[] sameAdj = new ArrayList[n];
        List<int[]>[] diffAdj = new ArrayList[n];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (labelArr[u] == labelArr[v]) {
                if (sameAdj[u] == null) sameAdj[u] = new ArrayList<>();
                sameAdj[u].add(new int[]{v, w});
            } else {
                if (diffAdj[u] == null) diffAdj[u] = new ArrayList<>();
                diffAdj[u].add(new int[]{v, w});
            }
        }
        int stride = k + 1;
        int[] dist = new int[n * stride];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] minCountPopped = new int[n];
        Arrays.fill(minCountPopped, k + 1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        dist[0 * stride + 1] = 0;
        pq.offer(new int[]{0, 1, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int c = curr[1];
            int d = curr[2];
            if (d > dist[u * stride + c]) {
                continue;
            }
            if (u == n - 1) {
                return d;
            }
            if (c >= minCountPopped[u]) {
                continue;
            }

            boolean isFirstVisit = (minCountPopped[u] == k + 1);
            minCountPopped[u] = c;
            if (isFirstVisit && diffAdj[u] != null) {
                for (int[] edge : diffAdj[u]) {
                    int v = edge[0];
                    int w = edge[1];
                    int new_d = d + w;
                    int new_c = 1;

                    if (new_c >= minCountPopped[v]) continue;
                    if (dist[v * stride + 1] <= new_d) continue;

                    dist[v * stride + 1] = new_d;
                    pq.offer(new int[]{v, 1, new_d});
                }
            }
            if (c + 1 <= k && sameAdj[u] != null) {
                int new_c = c + 1;
                for (int[] edge : sameAdj[u]) {
                    int v = edge[0];
                    int w = edge[1];
                    int new_d = d + w;

                    if (new_c >= minCountPopped[v]) continue;
                    boolean dominated = false;
                    int base = v * stride;
                    for (int cnt = 1; cnt <= new_c; cnt++) {
                        if (dist[base + cnt] <= new_d) {
                            dominated = true;
                            break;
                        }
                    }
                    if (dominated) continue;

                    dist[base + new_c] = new_d;
                    pq.offer(new int[]{v, new_c, new_d});
                }
            }
        }

        return -1;
    }
}