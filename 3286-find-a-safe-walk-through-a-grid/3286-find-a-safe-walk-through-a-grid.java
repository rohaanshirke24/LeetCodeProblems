class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dist[0][0] = grid.get(0).get(0);

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});

        int[] dirs = {-1, 0, 1, 0, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int newCost = dist[x][y] + grid.get(nx).get(ny);
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return dist[m - 1][n - 1] < health;
    }
}