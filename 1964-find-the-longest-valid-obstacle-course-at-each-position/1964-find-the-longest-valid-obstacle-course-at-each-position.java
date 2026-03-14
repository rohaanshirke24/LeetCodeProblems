class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] ans = new int[n];
        int[] tails = new int[n]; 
        int size = 0;             

        for (int i = 0; i < n; i++) {
            int h = obstacles[i];
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (tails[mid] <= h) lo = mid + 1; 
                else hi = mid;                     
            }

            tails[lo] = h;          
            if (lo == size) size++; 
            ans[i] = lo + 1;        
        }

        return ans;
    }
}
