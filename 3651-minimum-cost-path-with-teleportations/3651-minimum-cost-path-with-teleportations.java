class Solution {
    static class State {
        int id;
        long dist;
        State(int id, long dist) { this.id = id; this.dist = dist; }
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int MN = m * n;
        int[] all = new int[MN];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                all[i * n + j] = grid[i][j];
            }
        }
        int[] tmp = all.clone();
        Arrays.sort(tmp);
        int U = 0;
        for (int x : tmp) {
            if (U == 0 || tmp[U - 1] != x) tmp[U++] = x;
        }
        int[] vals = Arrays.copyOf(tmp, U);
        int[] valIdxOfCell = new int[MN];
        for (int idx = 0; idx < MN; idx++) {
            valIdxOfCell[idx] = Arrays.binarySearch(vals, all[idx]);
        }
        int[] counts = new int[U];
        for (int idx = 0; idx < MN; idx++) counts[valIdxOfCell[idx]]++;
        int[][] cellsByVal = new int[U][];
        for (int v = 0; v < U; v++) cellsByVal[v] = new int[counts[v]];
        Arrays.fill(counts, 0);
        for (int idx = 0; idx < MN; idx++) {
            int v = valIdxOfCell[idx];
            cellsByVal[v][counts[v]++] = idx;
        }
        int cellNodes = (k + 1) * MN;
        int valueNodes = k * U;
        int totalNodes = cellNodes + valueNodes;

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[totalNodes];
        Arrays.fill(dist, INF);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        int start = 0;
        dist[start] = 0;
        pq.add(new State(start, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int id = cur.id;
            long d = cur.dist;
            if (d != dist[id]) continue;

            if (id < cellNodes) {
                int t = id / MN;
                int idx = id % MN;

                if (idx == MN - 1) return (int) d; 

                int i = idx / n;
                int j = idx % n;
                if (j + 1 < n) {
                    int nid = t * MN + (idx + 1);
                    long nd = d + grid[i][j + 1];
                    if (nd < dist[nid]) {
                        dist[nid] = nd;
                        pq.add(new State(nid, nd));
                    }
                }
                if (i + 1 < m) {
                    int nid = t * MN + (idx + n);
                    long nd = d + grid[i + 1][j];
                    if (nd < dist[nid]) {
                        dist[nid] = nd;
                        pq.add(new State(nid, nd));
                    }
                }
                if (t < k) {
                    int vIdx = valIdxOfCell[idx];
                    int layer = t + 1; 
                    int vid = cellNodes + (layer - 1) * U + vIdx;
                    if (d < dist[vid]) {
                        dist[vid] = d;
                        pq.add(new State(vid, d));
                    }
                }
            } else {
                int p = id - cellNodes;
                int layer = (p / U) + 1; 
                int vIdx = p % U;
                if (vIdx > 0) {
                    int nid = cellNodes + (layer - 1) * U + (vIdx - 1);
                    if (d < dist[nid]) {
                        dist[nid] = d;
                        pq.add(new State(nid, d));
                    }
                }
                for (int cellIdx : cellsByVal[vIdx]) {
                    int nid = layer * MN + cellIdx; 
                    if (d < dist[nid]) {
                        dist[nid] = d;
                        pq.add(new State(nid, d));
                    }
                }
            }
        }
        return -1;
    }
}
