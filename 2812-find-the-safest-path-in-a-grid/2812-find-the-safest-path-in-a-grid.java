class Solution {
    private final int[] roww = {0, 0, -1, 1};
    private final int[] coll = {-1, 1, 0, 0};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }
        int[][] score = new int[n][n];
        for (int[] row : score) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        bfs(grid, score, n);
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[]{score[0][0], 0, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int safe = current[0];
            int i = current[1], j = current[2];
            if (i == n - 1 && j == n - 1) {
                return safe;
            }
            
            visited[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + roww[k];
                int newY = j + coll[k];
                if (newX >= 0 && newX < n && newY >= 0 && newY < n && !visited[newX][newY]) {
                    int minSafe = Math.min(safe, score[newX][newY]);
pq.offer(new int[]{minSafe, newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
        return -1;
    }
    private void bfs(List<List<Integer>> grid, int[][] score, int n) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    score[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];
            int s = score[x][y];
            for (int i = 0; i < 4; i++) {
                int newX = x + roww[i];
                int newY = y + coll[i];
                if (newX >= 0 && newX < n && newY >= 0 && newY < n && score[newX][newY] > 1 + s) {
                    score[newX][newY] = 1 + s;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
    }
}