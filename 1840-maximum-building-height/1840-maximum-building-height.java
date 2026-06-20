import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        int[][] arr = new int[m + 2][2];

        for (int i = 0; i < m; i++) {
            arr[i][0] = restrictions[i][0];
            arr[i][1] = restrictions[i][1];
        }

        arr[m][0] = 1;
        arr[m][1] = 0;
        arr[m + 1][0] = n;
        arr[m + 1][1] = n - 1;

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < arr.length; i++) {
            int dist = arr[i][0] - arr[i - 1][0];
            arr[i][1] = Math.min(arr[i][1], arr[i - 1][1] + dist);
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            int dist = arr[i + 1][0] - arr[i][0];
            arr[i][1] = Math.min(arr[i][1], arr[i + 1][1] + dist);
        }

        int ans = 0;

        for (int i = 1; i < arr.length; i++) {
            int leftPos = arr[i - 1][0];
            int rightPos = arr[i][0];
            int leftH = arr[i - 1][1];
            int rightH = arr[i][1];
            int dist = rightPos - leftPos;

            int peak = Math.max(leftH, rightH) + (dist - Math.abs(leftH - rightH)) / 2;
            ans = Math.max(ans, peak);
        }

        return ans;
    }
}