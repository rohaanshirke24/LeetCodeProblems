import java.util.Arrays;

class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int totalPoints = 0;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            totalPoints += reward2[i];
            diff[i] = reward1[i] - reward2[i];
        }
        Arrays.sort(diff);
        for (int i = 0; i < k; i++) {
            totalPoints += diff[n - 1 - i];
        }
        
        return totalPoints;
    }
}