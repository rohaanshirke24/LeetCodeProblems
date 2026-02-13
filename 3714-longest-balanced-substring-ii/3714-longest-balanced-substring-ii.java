class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 1;
        int run = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) run++;
            else {
                ans = Math.max(ans, run);
                run = 1;
            }
        }
        ans = Math.max(ans, run);
        int ca = 0, cb = 0, cc = 0;
        HashMap<Long, Integer> firstState = new HashMap<>();
        firstState.put(pack(0, 0), 0); 

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') ca++;
            else if (ch == 'b') cb++;
            else cc++;

            int d1 = ca - cb; // a-b
            int d2 = ca - cc; // a-c
            long key = pack(d1, d2);
            int pos = i + 1;

            Integer prev = firstState.get(key);
            if (prev != null) ans = Math.max(ans, pos - prev);
            else firstState.put(key, pos);
        }
        ans = Math.max(ans, longestTwo(s, 'a', 'b', 'c'));
        ans = Math.max(ans, longestTwo(s, 'a', 'c', 'b'));
        ans = Math.max(ans, longestTwo(s, 'b', 'c', 'a'));

        return ans;
    }
    private int longestTwo(String s, char x, char y, char skip) {
        int n = s.length();
        int best = 0;
        int i = 0;

        while (i < n) {
            while (i < n && s.charAt(i) == skip) i++;
            if (i >= n) break;

            int start = i;
            int diff = 0; // count(x) - count(y)
            HashMap<Integer, Integer> first = new HashMap<>();
            first.put(0, start);

            while (i < n && s.charAt(i) != skip) {
                char ch = s.charAt(i);
                diff += (ch == x) ? 1 : -1; 
                int pos = i + 1;

                Integer prev = first.get(diff);
                if (prev != null) best = Math.max(best, pos - prev);
                else first.put(diff, pos);

                i++;
            }
        }
        return best;
    }
    private long pack(int a, int b) {
        return (((long) a) << 32) ^ (b & 0xffffffffL);
    }
}
