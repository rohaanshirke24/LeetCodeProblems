class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long total = 0;
        for (int[] row : grid) {
            for (int v : row) {
                total += v;
            }
        }

        return checkHorizontal(grid, total) || checkVertical(grid, total);
    }

    private boolean checkHorizontal(int[][] grid, long total) {
        int m = grid.length, n = grid[0].length;

        Map<Integer, Integer> top = new HashMap<>();
        Map<Integer, Integer> bottom = new HashMap<>();

        for (int[] row : grid) {
            for (int v : row) {
                add(bottom, v);
            }
        }

        long topSum = 0;

        for (int cut = 0; cut < m - 1; cut++) {
            for (int v : grid[cut]) {
                topSum += v;
                add(top, v);
                remove(bottom, v);
            }

            long bottomSum = total - topSum;
            if (topSum == bottomSum) {
                return true;
            }

            long diff = Math.abs(topSum - bottomSum);
            if (topSum > bottomSum) {
                if (canRemoveFromTopHorizontal(grid, cut, diff, top)) {
                    return true;
                }
            } else {
                if (canRemoveFromBottomHorizontal(grid, cut, diff, bottom)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkVertical(int[][] grid, long total) {
        int m = grid.length, n = grid[0].length;

        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        for (int[] row : grid) {
            for (int v : row) {
                add(right, v);
            }
        }

        long leftSum = 0;

        for (int cut = 0; cut < n - 1; cut++) {
            for (int i = 0; i < m; i++) {
                int v = grid[i][cut];
                leftSum += v;
                add(left, v);
                remove(right, v);
            }

            long rightSum = total - leftSum;
            if (leftSum == rightSum) {
                return true;
            }

            long diff = Math.abs(leftSum - rightSum);
            if (leftSum > rightSum) {
                if (canRemoveFromLeftVertical(grid, cut, diff, left)) {
                    return true;
                }
            } else {
                if (canRemoveFromRightVertical(grid, cut, diff, right)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean canRemoveFromTopHorizontal(int[][] grid, int cut, long diff,
                                               Map<Integer, Integer> top) {
        if (diff > Integer.MAX_VALUE) return false;
        int target = (int) diff;

        int n = grid[0].length;
        int topRows = cut + 1;

        if (n == 1) {
            return grid[0][0] == target || grid[cut][0] == target;
        }

        if (topRows == 1) {
            return grid[0][0] == target || grid[0][n - 1] == target;
        }

        return top.containsKey(target);
    }

    private boolean canRemoveFromBottomHorizontal(int[][] grid, int cut, long diff,
                                                  Map<Integer, Integer> bottom) {
        if (diff > Integer.MAX_VALUE) return false;
        int target = (int) diff;

        int m = grid.length, n = grid[0].length;
        int bottomRows = m - cut - 1;

        if (n == 1) {
            return grid[cut + 1][0] == target || grid[m - 1][0] == target;
        }

        if (bottomRows == 1) {
            return grid[m - 1][0] == target || grid[m - 1][n - 1] == target;
        }

        return bottom.containsKey(target);
    }

    private boolean canRemoveFromLeftVertical(int[][] grid, int cut, long diff,
                                              Map<Integer, Integer> left) {
        if (diff > Integer.MAX_VALUE) return false;
        int target = (int) diff;

        int m = grid.length;
        int leftCols = cut + 1;

        if (m == 1) {
            return grid[0][0] == target || grid[0][cut] == target;
        }

        if (leftCols == 1) {
            return grid[0][0] == target || grid[m - 1][0] == target;
        }

        return left.containsKey(target);
    }

    private boolean canRemoveFromRightVertical(int[][] grid, int cut, long diff,
                                               Map<Integer, Integer> right) {
        if (diff > Integer.MAX_VALUE) return false;
        int target = (int) diff;

        int m = grid.length, n = grid[0].length;
        int rightCols = n - cut - 1;

        if (m == 1) {
            return grid[0][cut + 1] == target || grid[0][n - 1] == target;
        }

        if (rightCols == 1) {
            return grid[0][n - 1] == target || grid[m - 1][n - 1] == target;
        }

        return right.containsKey(target);
    }

    private void add(Map<Integer, Integer> map, int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    private void remove(Map<Integer, Integer> map, int x) {
        int cnt = map.get(x);
        if (cnt == 1) {
            map.remove(x);
        } else {
            map.put(x, cnt - 1);
        }
    }
}
