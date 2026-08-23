class Solution {
    public int numDupDigitsAtMostN(int n) {
        return n - countUniqueDigitsAtMostN(n);
    }
    
    private int countUniqueDigitsAtMostN(int n) {
        List<Integer> digits = new ArrayList<>();
        int temp = n;
        while (temp > 0) {
            digits.add(temp % 10);
            temp /= 10;
        }
        Collections.reverse(digits);
        
        int L = digits.size();
        int count = 0;
        for (int len = 1; len < L; len++) {
            count += 9 * P(9, len - 1);
        }
        boolean[] seen = new boolean[10];
        for (int i = 0; i < L; i++) {
            int d = digits.get(i);
            int start = (i == 0) ? 1 : 0;
            for (int val = start; val < d; val++) {
                if (!seen[val]) {
                    count += P(9 - i, L - 1 - i);
                }
            }
            if (seen[d]) {
                return count;
            }
            seen[d] = true;
        }
        return count + 1;
    }
    private int P(int m, int r) {
        int res = 1;
        for (int i = 0; i < r; i++) {
            res *= (m - i);
        }
        return res;
    }
}