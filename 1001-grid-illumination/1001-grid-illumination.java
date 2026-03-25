class Solution {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> rows = new HashMap<>();
        Map<Integer, Integer> cols = new HashMap<>();
        Map<Integer, Integer> diag1 = new HashMap<>(); // row - col
        Map<Integer, Integer> diag2 = new HashMap<>(); // row + col
        Set<Long> on = new HashSet<>();

        for (int[] lamp : lamps) {
            int r = lamp[0], c = lamp[1];
            long key = encode(r, c);
            if (on.add(key)) {
                add(rows, r, 1);
                add(cols, c, 1);
                add(diag1, r - c, 1);
                add(diag2, r + c, 1);
            }
        }

        int[] ans = new int[queries.length];
        int[] dirs = {-1, 0, 1};

        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0], c = queries[i][1];

            if (rows.getOrDefault(r, 0) > 0 ||
                cols.getOrDefault(c, 0) > 0 ||
                diag1.getOrDefault(r - c, 0) > 0 ||
                diag2.getOrDefault(r + c, 0) > 0) {
                ans[i] = 1;
            }

            for (int dr : dirs) {
                for (int dc : dirs) {
                    int nr = r + dr;
                    int nc = c + dc;

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                        continue;
                    }

                    long key = encode(nr, nc);
                    if (on.remove(key)) {
                        add(rows, nr, -1);
                        add(cols, nc, -1);
                        add(diag1, nr - nc, -1);
                        add(diag2, nr + nc, -1);
                    }
                }
            }
        }

        return ans;
    }

    private void add(Map<Integer, Integer> map, int key, int delta) {
        int next = map.getOrDefault(key, 0) + delta;
        if (next == 0) {
            map.remove(key);
        } else {
            map.put(key, next);
        }
    }

    private long encode(int r, int c) {
        return (((long) r) << 32) | (c & 0xffffffffL);
    }
}
