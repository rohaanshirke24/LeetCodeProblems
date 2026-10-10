class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long totalOps = (long) k1 + k2;
        int n = nums1.length;
        long[] diff = new long[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs((long) nums1[i] - (long) nums2[i]);
        }
        Arrays.sort(diff);
        long[] arr = new long[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = diff[n - 1 - i];
        }
        arr[n] = 0;

        long[] suffixSq = new long[n + 1];
        suffixSq[n] = 0;
        for (int j = n - 1; j >= 0; j--) {
            suffixSq[j] = suffixSq[j + 1] + arr[j] * arr[j];
        }

        long k = totalOps;
        for (int i = 0; i < n; i++) {
            long gap = arr[i] - arr[i + 1];
            long numElements = i + 1;
            if (k >= gap * numElements) {
                k -= gap * numElements;
            } else {
                long t0 = k / numElements;
                long r = k % numElements;
                long base = arr[i] - t0;
                long totalSq = r * (base - 1) * (base - 1) + (numElements - r) * base * base + suffixSq[i + 1];
                return totalSq;
            }
        }
        return 0;
    }
}