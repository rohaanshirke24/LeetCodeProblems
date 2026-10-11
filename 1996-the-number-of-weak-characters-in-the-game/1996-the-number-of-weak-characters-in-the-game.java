class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int maxAttack = 0;
        for (int[] p : properties) {
            if (p[0] > maxAttack) {
                maxAttack = p[0];
            }
        }
        int[] maxDefense = new int[maxAttack + 2];
        for (int[] p : properties) {
            maxDefense[p[0]] = Math.max(maxDefense[p[0]], p[1]);
        }
        for (int i = maxAttack; i >= 1; i--) {
            maxDefense[i] = Math.max(maxDefense[i], maxDefense[i + 1]);
        }
        int weakCount = 0;
        for (int[] p : properties) {
            if (p[1] < maxDefense[p[0] + 1]) {
                weakCount++;
            }
        }
        return weakCount;
    }
}