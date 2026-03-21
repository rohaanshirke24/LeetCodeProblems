class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int row = 0; row < k / 2; row++) {
            int top = x + row;
            int bottom = x + k - 1 - row;

            for (int col = y; col < y + k; col++) {
                int temp = grid[top][col];
                grid[top][col] = grid[bottom][col];
                grid[bottom][col] = temp;
            }
        }
        return grid;
    }
}
