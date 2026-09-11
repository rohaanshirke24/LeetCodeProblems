class Solution {
    public String largestMultipleOfThree(int[] digits) {
        int[] count = new int[10];
        int sum = 0;
        for (int d : digits) {
            count[d]++;
            sum += d;
        }
        int remainder = sum % 3;
        if (remainder == 1) {
            if (!removeDigits(count, 1, 1)) {
                if (!removeDigits(count, 2, 2)) {
                    return "";
                }
            }
        } else if (remainder == 2) {
            if (!removeDigits(count, 2, 1)) {
                if (!removeDigits(count, 1, 2)) {
                    return "";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int d = 9; d >= 0; d--) {
            while (count[d]-- > 0) {
                sb.append(d);
            }
        }
        if (sb.length() == 0) {
            return "";
        }
        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }
    private boolean removeDigits(int[] count, int rem, int k) {
        int available = 0;
        for (int d = rem; d < 10; d += 3) {
            available += count[d];
        }

        if (available < k) {
            return false;
        }
        for (int d = rem; d < 10 && k > 0; d += 3) {
            int take = Math.min(count[d], k);
            count[d] -= take;
            k -= take;
        }

        return true;
    }
}