class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 1;
        long maxWt = 0;

        for (int wt : workerTimes) {
            maxWt = Math.max(maxWt, wt);
        }
        long right = maxWt * (long) mountainHeight * (mountainHeight + 1) / 2;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (canFinish(mid, mountainHeight, workerTimes)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canFinish(long time, int mountainHeight, int[] workerTimes) {
        long total = 0;

        for (int wt : workerTimes) {
            long x = maxHeightByWorker(time, wt);
            total += x;
            if (total >= mountainHeight) {
                return true;
            }
        }

        return false;
    }

    private long maxHeightByWorker(long time, int wt) {
        return (long) ((Math.sqrt(1.0 + 8.0 * time / wt) - 1.0) / 2.0);
    }
}
