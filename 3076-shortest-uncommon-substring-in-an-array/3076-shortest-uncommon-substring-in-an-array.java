class Solution {
    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = arr[i];
            int m = s.length();
            Set<String> seen = new HashSet<>();

            for (int l = 0; l < m; l++) {
                for (int r = l + 1; r <= m; r++) {
                    seen.add(s.substring(l, r));
                }
            }

            for (String sub : seen) {
                freq.put(sub, freq.getOrDefault(sub, 0) + 1);
            }
        }
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            String s = arr[i];
            int m = s.length();
            String best = null;

            for (int l = 0; l < m; l++) {
                for (int r = l + 1; r <= m; r++) {
                    String sub = s.substring(l, r);
                    if (freq.getOrDefault(sub, 0) == 1) {
                        if (best == null
                                || sub.length() < best.length()
                                || (sub.length() == best.length() && sub.compareTo(best) < 0)) {
                            best = sub;
                        }
                    }
                }
            }

            ans[i] = (best == null) ? "" : best;
        }

        return ans;
    }
}
