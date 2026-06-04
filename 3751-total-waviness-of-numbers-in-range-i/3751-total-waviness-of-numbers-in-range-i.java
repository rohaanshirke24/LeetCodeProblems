class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;

        for (int x = num1; x <= num2; x++) {
            char[] digits = String.valueOf(x).toCharArray();

            if (digits.length < 3) continue;

            for (int i = 1; i < digits.length - 1; i++) {
                if ((digits[i] > digits[i - 1] && digits[i] > digits[i + 1]) ||
                    (digits[i] < digits[i - 1] && digits[i] < digits[i + 1])) {
                    total++;
                }
            }
        }

        return total;
    }
}