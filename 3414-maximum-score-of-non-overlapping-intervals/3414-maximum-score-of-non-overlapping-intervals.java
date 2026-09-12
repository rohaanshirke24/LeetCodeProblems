class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) ->
            Integer.compare(intervals.get(a).get(1), intervals.get(b).get(1)));

        int[] ends = new int[n];
        for (int i = 0; i < n; i++) ends[i] = intervals.get(order[i]).get(1);

        long[][] score = new long[n + 1][5];
        int[][][] pick = new int[n + 1][5][];
        for (int k = 0; k <= 4; k++) pick[0][k] = new int[0];

        for (int i = 1; i <= n; i++) {
            int id = order[i - 1];
            int l = intervals.get(id).get(0);
            int w = intervals.get(id).get(2);
            int j = countLess(ends, i - 1, l);

            for (int k = 0; k <= 4; k++) {
                score[i][k] = score[i - 1][k];
                pick[i][k]  = pick[i - 1][k];
                if (k > 0) {
                    long cand = score[j][k - 1] + w;
                    if (cand > score[i][k]) {
                        score[i][k] = cand;
                        pick[i][k]  = insertSorted(pick[j][k - 1], id);
                    } else if (cand == score[i][k]) {
                        int[] candPick = insertSorted(pick[j][k - 1], id);
                        if (lexCompare(candPick, pick[i][k]) < 0) {
                            pick[i][k] = candPick;
                        }
                    }
                }
            }
        }
        return pick[n][4];
    }
    private int countLess(int[] ends, int len, int target) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (ends[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
    private int[] insertSorted(int[] arr, int val) {
        int[] res = new int[arr.length + 1];
        int i = 0;
        while (i < arr.length && arr[i] < val) { res[i] = arr[i]; i++; }
        res[i] = val;
        for (; i < arr.length; i++) res[i + 1] = arr[i];
        return res;
    }

    private int lexCompare(int[] a, int[] b) {
        int m = Math.min(a.length, b.length);
        for (int i = 0; i < m; i++) {
            if (a[i] != b[i]) return Integer.compare(a[i], b[i]);
        }
        return Integer.compare(a.length, b.length);
    }
}
