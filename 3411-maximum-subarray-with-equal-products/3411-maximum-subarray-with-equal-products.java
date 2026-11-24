class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long g = 0;
            long l = 1;

            for (int j = i; j < n; j++) {
                int val = nums[j];
                prod *= val;
                g = (g == 0) ? val : gcd(g, val);
                l = lcm(l, val);

                if (prod == g * l) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
                if (prod > 25200) {
                    break;
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}