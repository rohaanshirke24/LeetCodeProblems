class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int result = 10; 
        int uniqueDigits = 9; 
        for (int i = 2; i <= n && i <= 10; i++) {
            uniqueDigits *= (11 - i);
            result += uniqueDigits;
        }
        return result;
    }
}