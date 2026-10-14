class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum < 1 || (long) num * 9 < sum) {
            return "";
        }
        int count9 = sum / 9;
        int rem = sum % 9;

        char[] result = new char[num];
        int idx = 0;
        for (int i = 0; i < count9; i++) {
            result[idx++] = '9';
        }
        if (rem > 0) {
            result[idx++] = (char) ('0' + rem);
        }
        while (idx < num) {
            result[idx++] = '0';
        }

        return new String(result);
    }
}