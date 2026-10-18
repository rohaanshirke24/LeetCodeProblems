class Solution {
    public int minimumRounds(int[] tasks) {
        Arrays.sort(tasks);
        int totalRounds = 0;
        int n = tasks.length;
        
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && tasks[j] == tasks[i]) {
                j++;
            }
            int count = j - i;
            
            if (count == 1) {
                return -1;
            }
            
            totalRounds += (count + 2) / 3;
            i = j;
        }
        
        return totalRounds;
    }
}