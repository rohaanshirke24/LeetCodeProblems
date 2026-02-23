class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (k > n) return false;

        int need = 1 << k;                 
        if (n - k + 1 < need) return false;

        boolean[] seen = new boolean[need];
        int mask = need - 1;              
        int window = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            window = ((window << 1) & mask) | (s.charAt(i) - '0');

            if (i >= k - 1 && !seen[window]) {
                seen[window] = true;
                if (++count == need) return true;
            }
        }
        return false;
    }
}
