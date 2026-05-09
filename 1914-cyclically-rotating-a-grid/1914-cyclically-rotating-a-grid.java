class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {
            int top = layer, left = layer;
            int bottom = m - 1 - layer, right = n - 1 - layer;
            java.util.List<Integer> vals = new java.util.ArrayList<>();
            for (int j = left; j < right; j++) {
                vals.add(grid[top][j]);
            }
            for (int i = top; i < bottom; i++) {
                vals.add(grid[i][right]);
            }
            for (int j = right; j > left; j--) {
                vals.add(grid[bottom][j]);
            }
            for (int i = bottom; i > top; i--) {
                vals.add(grid[i][left]);
            }

            int len = vals.size();
            int shift = k % len;
            if (shift == 0) continue;

            java.util.List<Integer> rotated = new java.util.ArrayList<>(len);
            rotated.addAll(vals.subList(shift, len));
            rotated.addAll(vals.subList(0, shift));

            int idx = 0;
            for (int j = left; j < right; j++) {
                grid[top][j] = rotated.get(idx++);
            }
            for (int i = top; i < bottom; i++) {
                grid[i][right] = rotated.get(idx++);
            }
            for (int j = right; j > left; j--) {
                grid[bottom][j] = rotated.get(idx++);
            }
            for (int i = bottom; i > top; i--) {
                grid[i][left] = rotated.get(idx++);
            }
        }

        return grid;
    }
}