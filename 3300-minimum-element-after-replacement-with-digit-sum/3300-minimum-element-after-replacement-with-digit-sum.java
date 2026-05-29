class Solution {
    public int minElement(int[] nums) {
        int ans = 100;

        for (int x : nums) {
            int sum = 0;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            ans = Math.min(ans, sum);
        }

        return ans;
    }
}