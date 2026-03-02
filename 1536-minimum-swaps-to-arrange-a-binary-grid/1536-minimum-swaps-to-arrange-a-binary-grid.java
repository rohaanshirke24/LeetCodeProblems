class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] tz = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) count++;
            tz[i] = count;
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            int need = n - i - 1;

            int j = i;
            while (j < n && tz[j] < need) j++;
            if (j == n) return -1;
            while (j > i) {
                int tmp = tz[j];
                tz[j] = tz[j - 1];
                tz[j - 1] = tmp;
                swaps++;
                j--;
            }
        }

        return swaps;
    }
}
