class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] diag1 = new int[rows + 1][cols + 2];
        int[][] diag2 = new int[rows + 1][cols + 2];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                diag1[i][j] = diag1[i - 1][j - 1] + grid[i - 1][j - 1];
                diag2[i][j] = diag2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }

        TreeSet<Integer> set = new TreeSet<>();

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                set.add(grid[r - 1][c - 1]);
                if (set.size() > 3) set.pollFirst();

                int maxRadius = Math.min(
                    Math.min(r - 1, rows - r),
                    Math.min(c - 1, cols - c)
                );

                for (int k = 1; k <= maxRadius; k++) {
                    int a = diag1[r + k][c] - diag1[r][c - k];
                    int b = diag1[r][c + k] - diag1[r - k][c];
                    int c1 = diag2[r][c - k] - diag2[r - k][c];
                    int d = diag2[r + k][c] - diag2[r][c + k];

                    int sum = a + b + c1 + d
                            - grid[r + k - 1][c - 1]
                            + grid[r - k - 1][c - 1];

                    set.add(sum);
                    if (set.size() > 3) set.pollFirst();
                }
            }
        }

        int[] ans = new int[set.size()];
        int idx = 0;
        for (int val : set.descendingSet()) {
            ans[idx++] = val;
        }
        return ans;
    }
}
