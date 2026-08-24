class Solution {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int k = s.length();
        int d = digits.length;
        int count = 0;
        for (int i = 1; i < k; i++) {
            count += Math.pow(d, i);
        }
        for (int i = 0; i < k; i++) {
            boolean hasSameDigit = false;
            char currentDigit = s.charAt(i);

            for (String digit : digits) {
                char c = digit.charAt(0);
                if (c < currentDigit) {
                    count += Math.pow(d, k - 1 - i);
                } else if (c == currentDigit) {
                    hasSameDigit = true;
                    break; 
                } else {
                    break;
                }
            }
            if (!hasSameDigit) {
                return count;
            }
        }
        return count + 1;
    }
}