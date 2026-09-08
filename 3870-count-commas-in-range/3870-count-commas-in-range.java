class Solution {
    public int countCommas(int n) {
        int totalCommas = 0;
        for (long threshold = 1000; threshold <= n; threshold *= 1000) {
            totalCommas += (n - threshold + 1);
        }
        return totalCommas;
    }
}