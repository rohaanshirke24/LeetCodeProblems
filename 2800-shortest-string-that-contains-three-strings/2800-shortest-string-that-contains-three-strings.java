class Solution {
    public String minimumString(String a, String b, String c) {
        String[] words = {a, b, c};
        int[][] permutations = {
            {0, 1, 2}, {0, 2, 1},
            {1, 0, 2}, {1, 2, 0},
            {2, 0, 1}, {2, 1, 0}
        };
        String best = "";
        for (int[] p : permutations) {
            String combined = merge(merge(words[p[0]], words[p[1]]), words[p[2]]);
            best = getBetter(best, combined);
        }
        return best;
    }

    private String merge(String s1, String s2) {
        if (s1.contains(s2)) {
            return s1;
        }
        int maxOverlap = Math.min(s1.length(), s2.length());
        for (int len = maxOverlap; len > 0; len--) {
            if (s1.endsWith(s2.substring(0, len))) {
                return s1 + s2.substring(len);
            }
        }
        return s1 + s2;
    }
    
    private String getBetter(String s1, String s2) {
        if (s1.isEmpty()) return s2;
        if (s1.length() < s2.length()) return s1;
        if (s2.length() < s1.length()) return s2;
        return s1.compareTo(s2) <= 0 ? s1 : s2;
    }
}