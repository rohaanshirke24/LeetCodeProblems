class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;
        int sr = -1, sc = -1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } else if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        int allMask = (1 << litterCount) - 1;
        if (allMask == 0) {
            return 0;
        }
        int[][][] best = new int[m][n][1 << litterCount];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(best[r][c], -1);
            }
        }

        ArrayDeque<State> queue = new ArrayDeque<>();

        int startMask = 0;
        if (litterId[sr][sc] != -1) {
            startMask |= 1 << litterId[sr][sc];
        }

        best[sr][sc][startMask] = energy;
        queue.offer(new State(sr, sc, startMask, energy, 0));

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            int r = cur.r;
            int c = cur.c;
            int mask = cur.mask;
            int e = cur.energy;
            int moves = cur.moves;

            if (mask == allMask) {
                return moves;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                if (e == 0) {
                    
                    continue;
                }
                int ne = e - 1;
                int nmask = mask;
                if (litterId[nr][nc] != -1) {
                    nmask |= 1 << litterId[nr][nc];
                }
                if (classroom[nr].charAt(nc) == 'R') {
                    ne = energy;
                }
                if (ne <= best[nr][nc][nmask]) {
                    continue;
                }

                best[nr][nc][nmask] = ne;
                queue.offer(new State(nr, nc, nmask, ne, moves + 1));
            }
        }

        return -1;
    }

    static class State {
        int r, c, mask, energy, moves;

        State(int r, int c, int mask, int energy, int moves) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
            this.moves = moves;
        }
    }
}