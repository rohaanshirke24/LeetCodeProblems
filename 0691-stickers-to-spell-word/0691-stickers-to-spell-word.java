class Solution {
    public int minStickers(String[] stickers, String target) {
        int m = target.length();
        int fullMask = (1 << m) - 1;
        int[][] stickerCount = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char ch : stickers[i].toCharArray()) {
                stickerCount[i][ch - 'a']++;
            }
        }

        int[] dp = new int[1 << m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int mask = 0; mask <= fullMask; mask++) {
            if (dp[mask] == Integer.MAX_VALUE) {
                continue;
            }

            for (int[] sticker : stickerCount) {
                int[] available = sticker.clone();
                int nextMask = mask;
                for (int i = 0; i < m; i++) {
                    boolean alreadyFilled = (nextMask & (1 << i)) != 0;
                    int letter = target.charAt(i) - 'a';

                    if (!alreadyFilled && available[letter] > 0) {
                        nextMask |= (1 << i);
                        available[letter]--;
                    }
                }
                if (nextMask != mask) {
                    dp[nextMask] = Math.min(dp[nextMask], dp[mask] + 1);
                }
            }
        }

        return dp[fullMask] == Integer.MAX_VALUE ? -1 : dp[fullMask];
    }
}