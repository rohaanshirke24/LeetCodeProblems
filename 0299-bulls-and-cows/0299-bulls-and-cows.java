class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int[] sCount = new int[10];
        int[] gCount = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if (s == g) {
                bulls++;
            } else {
                sCount[s - '0']++;
                gCount[g - '0']++;
            }
        }

        int cows = 0;
        for (int d = 0; d < 10; d++) {
            cows += Math.min(sCount[d], gCount[d]);
        }

        return bulls + "A" + cows + "B";
    }
}