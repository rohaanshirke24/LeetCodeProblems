class Solution {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        boolean[][] dug = new boolean[n][n];

        for (int[] d : dig) {
            dug[d[0]][d[1]] = true;
        }

        int count = 0;

        for (int[] a : artifacts) {
            int r1 = a[0], c1 = a[1], r2 = a[2], c2 = a[3];
            boolean canExtract = true;

            for (int r = r1; r <= r2 && canExtract; r++) {
                for (int c = c1; c <= c2; c++) {
                    if (!dug[r][c]) {
                        canExtract = false;
                        break;
                    }
                }
            }

            if (canExtract) {
                count++;
            }
        }

        return count;
    }
}