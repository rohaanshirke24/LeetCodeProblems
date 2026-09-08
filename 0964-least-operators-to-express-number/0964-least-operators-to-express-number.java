class Solution {
    public int leastOpsExpressTarget(int x, int target) {
        int c0 = 0; 
        int c1 = 0; 
        int k = 0;  

        while (target > 0) {
            int r0 = target % x;
            target /= x;

            if (k == 0) {
                c0 = r0 * 2;
                c1 = (x - r0) * 2;
            } else {
                int new_c0 = Math.min(c0 + r0 * k, c1 + (r0 + 1) * k);
                int new_c1 = Math.min(c0 + (x - r0) * k, c1 + (x - r0 - 1) * k);
                c0 = new_c0;
                c1 = new_c1;
            }
            k++;
        }
        return Math.min(c0, c1 + k) - 1;
    }
}