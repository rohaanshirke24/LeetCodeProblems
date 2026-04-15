class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> bad = new HashSet<>();

        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i]) {
                bad.add(fronts[i]);
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int x : fronts) {
            if (!bad.contains(x)) {
                ans = Math.min(ans, x);
            }
        }

        for (int x : backs) {
            if (!bad.contains(x)) {
                ans = Math.min(ans, x);
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}