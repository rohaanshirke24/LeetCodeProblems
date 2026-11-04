class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Random rand = new Random(42);
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = sorted[i];
            sorted[i] = sorted[j];
            sorted[j] = temp;
        }
        Arrays.sort(sorted);

        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[uniqueCount++] = sorted[i];
            }
        }

        int M = uniqueCount;
        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) {
            ranks[i] = Arrays.binarySearch(sorted, 0, uniqueCount, nums[i]) + 1;
        }
        int[] bitCount = new int[M + 1];
        long[] bitSum = new long[M + 1];
        int highestPowerOf2 = Integer.highestOneBit(M);

        long[] cost = new long[n];
        int m = x / 2;
        long windowSum = 0;
        for (int i = 0; i < x; i++) {
            add(bitCount, bitSum, M, ranks[i], 1, nums[i]);
            windowSum += nums[i];
        }
        cost[x - 1] = windowSum 
                    - querySumOfSmallest(bitCount, bitSum, sorted, M, highestPowerOf2, x - m) 
                    - querySumOfSmallest(bitCount, bitSum, sorted, M, highestPowerOf2, m);
        for (int i = x; i < n; i++) {
            add(bitCount, bitSum, M, ranks[i], 1, nums[i]);
            windowSum += nums[i];

            add(bitCount, bitSum, M, ranks[i - x], -1, -nums[i - x]);
            windowSum -= nums[i - x];

            cost[i] = windowSum 
                    - querySumOfSmallest(bitCount, bitSum, sorted, M, highestPowerOf2, x - m) 
                    - querySumOfSmallest(bitCount, bitSum, sorted, M, highestPowerOf2, m);
        }
        long[] prev = new long[n];
        prev[x - 1] = cost[x - 1];
        for (int i = x; i < n; i++) {
            prev[i] = Math.min(prev[i - 1], cost[i]);
        }
        for (int j = 2; j <= k; j++) {
            long[] curr = new long[n];
            int start = j * x - 1;
            curr[start] = prev[start - x] + cost[start];
            for (int i = start + 1; i < n; i++) {
                curr[i] = Math.min(curr[i - 1], prev[i - x] + cost[i]);
            }
            prev = curr;
        }

        return prev[n - 1];
    }
    private void add(int[] bitCount, long[] bitSum, int M, int idx, int countDelta, long valDelta) {
        for (; idx <= M; idx += idx & -idx) {
            bitCount[idx] += countDelta;
            bitSum[idx] += valDelta;
        }
    }
    private long querySumOfSmallest(int[] bitCount, long[] bitSum, int[] sorted, int M, int highestPowerOf2, int count) {
        if (count <= 0) return 0;
        long sum = 0;
        int idx = 0;
        for (int step = highestPowerOf2; step > 0; step >>= 1) {
            if (idx + step <= M && bitCount[idx + step] < count) {
                idx += step;
                count -= bitCount[idx];
                sum += bitSum[idx];
            }
        }
        sum += (long) count * sorted[idx];
        return sum;
    }
}