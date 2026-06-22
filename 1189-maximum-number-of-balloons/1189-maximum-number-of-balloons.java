class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[26];

        for (int i = 0; i < text.length(); i++) {
            cnt[text.charAt(i) - 'a']++;
        }

        cnt['l' - 'a'] /= 2;
        cnt['o' - 'a'] /= 2;

        return Math.min(
            Math.min(cnt['b' - 'a'], cnt['a' - 'a']),
            Math.min(
                Math.min(cnt['l' - 'a'], cnt['o' - 'a']),
                cnt['n' - 'a']
            )
        );
    }
}