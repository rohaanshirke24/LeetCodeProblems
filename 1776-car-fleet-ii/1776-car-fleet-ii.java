class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = -1.0;
            int posI = cars[i][0];
            int speedI = cars[i][1];
            while (top >= 0) {
                int j = stack[top];
                int posJ = cars[j][0];
                int speedJ = cars[j][1];
                if (speedI <= speedJ) {
                    top--;
                } else {
                    double collisionTime = (double) (posJ - posI) / (speedI - speedJ);
                    if (ans[j] > 0 && collisionTime >= ans[j]) {
                        top--;
                    } else {
                        ans[i] = collisionTime;
                        break;
                    }
                }
            }
            stack[++top] = i;
        }
        return ans;
    }
}