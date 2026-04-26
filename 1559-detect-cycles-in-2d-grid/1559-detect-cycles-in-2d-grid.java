class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        // Iterate through each cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    // Start DFS from this cell
                    if (dfs(grid, visited, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false; // No cycle found
    }

    private boolean dfs(char[][] grid, boolean[][] visited, int x, int y, int prevX, int prevY, char target) {
        // Mark the current cell as visited
        visited[x][y] = true;

        // Define directions for movement: up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            // Check boundaries
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                // Ignore the cell we just came from
                if (newX == prevX && newY == prevY) {
                    continue;
                }

                // If the cell matches the target character
                if (grid[newX][newY] == target) {
                    // If already visited, a cycle is found
                    if (visited[newX][newY]) {
                        return true;
                    }

                    // Continue DFS
                    if (dfs(grid, visited, newX, newY, x, y, target)) {
                        return true;
                    }
                }
            }
        }

        return false; // No cycle found from this path
    }
}