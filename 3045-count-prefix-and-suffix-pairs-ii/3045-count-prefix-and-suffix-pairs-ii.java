class Solution {
    public long countPrefixSuffixPairs(String[] words) {
        int totalLen = 1;
        for (String w : words) totalLen += w.length();
        int[] next = new int[(totalLen + 2) * 26];
        int[] endCount = new int[totalLen + 2];

        int root = 1, size = 1;
        long ans = 0;

        for (String s : words) {
            int n = s.length();
            int[] pi = new int[n];
            for (int i = 1; i < n; i++) {
                int j = pi[i - 1];
                while (j > 0 && s.charAt(i) != s.charAt(j)) {
                    j = pi[j - 1];
                }
                if (s.charAt(i) == s.charAt(j)) j++;
                pi[i] = j;
            }
            boolean[] isBorderLen = new boolean[n + 1];
            int len = n;
            while (len > 0) {
                isBorderLen[len] = true;
                len = pi[len - 1];
            }

            int node = root;
            for (int i = 0; i < n; i++) {
                int c = s.charAt(i) - 'a';
                int idx = node * 26 + c;
                int child = next[idx];
                if (child == 0) {
                    child = ++size;
                    next[idx] = child;
                }
                node = child;

                int prefLen = i + 1;
                if (isBorderLen[prefLen]) {
                    ans += endCount[node]; 
                }
            }
            endCount[node]++;
        }

        return ans;
    }
}
