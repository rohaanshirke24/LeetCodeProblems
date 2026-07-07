class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        int sum = 0;

        char[] digits = String.valueOf(n).toCharArray();

        for (char ch : digits) {
            int digit = ch - '0';
            if (digit != 0) {
                x = x * 10 + digit;
                sum += digit;
            }
        }

        return x * sum;
    }
}