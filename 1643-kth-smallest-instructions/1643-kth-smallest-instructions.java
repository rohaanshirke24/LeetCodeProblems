class Solution {
    public String kthSmallestPath(int[] destination, int k) {
        int row = destination[0];
        int col = destination[1];

        long[][] ways = new long[row + 1][col + 1];

        for (int v = 0; v <= row; v++) {
            for (int h = 0; h <= col; h++) {
                if (v == 0 || h == 0) {
                    ways[v][h] = 1;
                } else {
                    ways[v][h] = ways[v - 1][h] + ways[v][h - 1];
                }
            }
        }

        StringBuilder result = new StringBuilder(row + col);
        int v = row;
        int h = col;
        long rank = k;

        while (v > 0 || h > 0) {
            if (h == 0) {
                result.append('V');
                v--;
            } else if (v == 0) {
                result.append('H');
                h--;
            } else {
                long pathsStartingWithH = ways[v][h - 1];

                if (rank <= pathsStartingWithH) {
                    result.append('H');
                    h--;
                } else {
                    result.append('V');
                    v--;
                    rank -= pathsStartingWithH;
                }
            }
        }

        return result.toString();
    }
}
