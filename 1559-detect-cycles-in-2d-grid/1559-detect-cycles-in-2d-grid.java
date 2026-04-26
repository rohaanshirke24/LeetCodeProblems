class Solution {
    private int m, n;
    private boolean[][] visited;
    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};

    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (!visited[r][c]) {
                    if (dfs(grid, r, c, -1, -1, grid[r][c])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, int r, int c, int pr, int pc, char ch) {
        visited[r][c] = true;

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                continue;
            }

            if (grid[nr][nc] != ch) {
                continue;
            }

            if (nr == pr && nc == pc) {
                continue;
            }

            if (visited[nr][nc]) {
                return true;
            }

            if (dfs(grid, nr, nc, r, c, ch)) {
                return true;
            }
        }

        return false;
    }
}