class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            long cur = len[i];
            
            if (ch >= 'a' && ch <= 'z') {
                len[i + 1] = cur + 1;
            } else if (ch == '*') {
                len[i + 1] = Math.max(0L, cur - 1);
            } else if (ch == '#') {
                len[i + 1] = cur * 2;
            } else { // ch == '%'
                len[i + 1] = cur;
            }
        }
        
        if (k < 0 || k >= len[n]) {
            return '.';
        }
        
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prevLen = len[i];
            long curLen = len[i + 1];
            
            if (ch >= 'a' && ch <= 'z') {
                if (k == curLen - 1) {
                    return ch;
                }
            } else if (ch == '*') {
            } else if (ch == '#') {
                if (prevLen > 0 && k >= prevLen) {
                    k -= prevLen;
                }
            } else { 
                k = prevLen - 1 - k;
            }
        }
        
        return '.';
    }
}