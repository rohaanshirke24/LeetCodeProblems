class Solution {
    public double getProbability(int[] balls) {
        int sum = 0;
        for (int b : balls) sum += b;
        int n = sum / 2;
        double[] result = new double[1];
        dfs(balls, 0, 0, 0, 0, 1.0, n, result);
        return result[0] / comb(sum, n);
    }
    private void dfs(int[] balls, int idx, int box1, int d1, int d2, double ways, int n, double[] result) {
        if (box1 > n) return;
        if (idx == balls.length) {
            if (box1 == n && d1 == d2) {
                result[0] += ways;
            }
            return;
        }
        int remaining = 0;
        for (int i = idx; i < balls.length; i++) remaining += balls[i];
        if (box1 + remaining < n) return;

        for (int j = 0; j <= balls[idx]; j++) {
            dfs(balls, idx + 1, box1 + j,
                d1 + (j > 0 ? 1 : 0),
                d2 + (balls[idx] - j > 0 ? 1 : 0),
                ways * comb(balls[idx], j),
                n, result);
        }
    }
    private double comb(int a, int b) {
        if (b > a || b < 0) return 0;
        double res = 1;
        for (int i = 0; i < b; i++) {
            res = res * (a - i) / (i + 1);
        }
        return res;
    }
}