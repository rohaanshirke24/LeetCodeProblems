class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] nodes = new int[n][2];
        for (int i = 0; i < n; i++) {
            nodes[i][0] = nums[i];
            nodes[i][1] = i;
        }

        Arrays.sort(nodes, Comparator.comparingInt(a -> a[0]));
        int[] position = new int[n];
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = nodes[i][0];
            position[nodes[i][1]] = i;
        }

        int[] component = new int[n];
        int componentId = 0;
        component[0] = 0;

        for (int i = 1; i < n; i++) {
            if (values[i] - values[i - 1] > maxDiff) {
                componentId++;
            }
            component[i] = componentId;
        }

        int LOG = 18; 
        int[][] jump = new int[LOG][n];

        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right + 1 < n && values[right + 1] - values[left] <= maxDiff) {
                right++;
            }
            jump[0][left] = right;

            if (right == left && right + 1 < n) {
                
            }
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                jump[k][i] = jump[k - 1][jump[k - 1][i]];
            }
        }

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            if (u == v) {
                answer[q] = 0;
                continue;
            }

            int left = position[u];
            int rightPos = position[v];

            if (left > rightPos) {
                int temp = left;
                left = rightPos;
                rightPos = temp;
            }

            
            if (component[left] != component[rightPos]) {
                answer[q] = -1;
                continue;
            }

            int current = left;
            int distance = 0;

            
            for (int k = LOG - 1; k >= 0; k--) {
                if (jump[k][current] < rightPos) {
                    current = jump[k][current];
                    distance += 1 << k;
                }
            }

            answer[q] = distance + 1;
        }

        return answer;
    }
}