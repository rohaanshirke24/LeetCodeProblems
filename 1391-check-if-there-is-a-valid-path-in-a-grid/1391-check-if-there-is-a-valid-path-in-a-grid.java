class Solution {
    private static final int[][][] DIRS = {
        {},
        {{0, -1}, {0, 1}},   // left, right
        {{-1, 0}, {1, 0}},   // up, down
        {{0, -1}, {1, 0}},   // left, down
        {{0, 1}, {1, 0}},    // right, down
        {{0, -1}, {-1, 0}},  // left, up
        {{0, 1}, {-1, 0}}    // right, up
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            for (int[] d : DIRS[grid[r][c]]) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) {
                    continue;
                }

                if (isConnected(grid[nr][nc], -d[0], -d[1])) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return false;
    }

    private boolean isConnected(int type, int dr, int dc) {
        for (int[] d : DIRS[type]) {
            if (d[0] == dr && d[1] == dc) {
                return true;
            }
        }
        return false;
    }
}