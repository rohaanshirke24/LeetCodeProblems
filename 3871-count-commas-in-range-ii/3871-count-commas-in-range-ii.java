class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        for (long threshold = 1_000L; threshold <= n; threshold *= 1_000L) {
            totalCommas += (n - threshold + 1);
            if (threshold > Long.MAX_VALUE / 1_000L) {
                break;
            }
        }
        
        return totalCommas;
    }
}