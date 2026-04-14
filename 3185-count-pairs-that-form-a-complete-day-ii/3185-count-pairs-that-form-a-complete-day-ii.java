class Solution {
    public long countCompleteDayPairs(int[] hours) {
        long ans = 0;
        int[] count = new int[24];

        for (int h : hours) {
            int rem = h % 24;
            int need = (24 - rem) % 24;

            ans += count[need];
            count[rem]++;
        }

        return ans;
    }
}