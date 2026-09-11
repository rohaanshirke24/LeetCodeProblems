class Solution {
    public String largestMultipleOfThree(int[] digits) {
        int[] count = new int[10];
        int sum = 0;
        for (int digit : digits) {
            count[digit]++;
            sum += digit;
        }
        int remainder = sum % 3;
        if (remainder == 1) {
            if (!removeSmallest(count, 1)) {
                removeSmallest(count, 2);
                removeSmallest(count, 2);
            }
        } else if (remainder == 2) {
            if (!removeSmallest(count, 2)) {
                removeSmallest(count, 1);
                removeSmallest(count, 1);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int digit = 9; digit >= 0; digit--) {
            for (int i = 0; i < count[digit]; i++) {
                result.append(digit);
            }
        }
        if (result.length() == 0) {
            return "";
        }
        if (result.charAt(0) == '0') {
            return "0";
        }
        return result.toString();
    }

    private boolean removeSmallest(int[] count, int remainder) {
        for (int digit = remainder; digit <= 9; digit += 3) {
            if (count[digit] > 0) {
                count[digit]--;
                return true;
            }
        }
        return false;
    }
}
