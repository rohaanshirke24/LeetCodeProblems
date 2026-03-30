class Solution {
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();
        long ans = 0;

        while (arr[0] > 0) {
            int[] count = new int[10];

            for (int i = 0; i < n; i++) {
                int digit = arr[i] % 10;
                ans += i - count[digit];
                count[digit]++;
                arr[i] /= 10;
            }
        }

        return ans;
    }
}