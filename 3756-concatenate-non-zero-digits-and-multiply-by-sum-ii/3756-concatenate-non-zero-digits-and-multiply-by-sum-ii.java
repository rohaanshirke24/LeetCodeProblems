class Solution {
    private static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') k++;
        }

        int[] pos = new int[k];
        int[] digits = new int[k];

        for (int i = 0, j = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch != '0') {
                pos[j] = i;
                digits[j] = ch - '0';
                j++;
            }
        }

        long[] pref = new long[k + 1];
        long[] sum = new long[k + 1];
        long[] pow10 = new long[k + 1];
        pow10[0] = 1;

        for (int i = 0; i < k; i++) {
            pref[i + 1] = (pref[i] * 10 + digits[i]) % MOD;
            sum[i + 1] = sum[i] + digits[i];
            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);   
            int right = upperBound(pos, r);  

            if (left >= right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left;
            long x = (pref[right] - (pref[left] * pow10[len]) % MOD + MOD) % MOD;
            long digitSum = sum[right] - sum[left];

            ans[i] = (int) ((x * (digitSum % MOD)) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] > target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}