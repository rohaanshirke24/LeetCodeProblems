class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        String t = "1" + s + "1";
        int m = t.length();
        
        List<int[]> runs = new ArrayList<>(); 
        int i = 0;
        while (i < m) {
            int j = i;
            char c = t.charAt(i);
            while (j < m && t.charAt(j) == c) j++;
            runs.add(new int[]{c - '0', j - i});
            i = j;
        }
        
        int baseOnes = 0;
        for (char c : s.toCharArray()) if (c == '1') baseOnes++;
        
        int best = baseOnes; 
        
        int R = runs.size();
        for (int k = 0; k < R; k++) {
            if (runs.get(k)[0] != 1) continue;
            if (k - 1 < 0 || k + 1 >= R) continue;
            int[] leftRun = runs.get(k - 1);
            int[] rightRun = runs.get(k + 1);
            if (leftRun[0] != 0 || rightRun[0] != 0) continue;
            
            int left = leftRun[1];
            int right = rightRun[1];
            
            int candidate = baseOnes + left + right;
            best = Math.max(best, candidate);
        }
        
        return best;
    }
}