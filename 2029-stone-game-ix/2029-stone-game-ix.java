class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        for (int stone : stones) {
            count[stone % 3]++;
        }
        int mod0 = count[0];
        int mod1 = count[1];
        int mod2 = count[2];
        if (mod0 % 2 == 0) {
            return mod1 > 0 && mod2 > 0;
        }
        return Math.abs(mod1 - mod2) > 2;
    }
}