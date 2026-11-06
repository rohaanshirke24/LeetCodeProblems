class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        int[] serverCount = new int[n + 1];
        int activeServers = 0;
        int left = 0;
        int right = 0;
        int numLogs = logs.length;

        for (int[] query : sortedQueries) {
            int qTime = query[0];
            int origIdx = query[1];
            while (right < numLogs && logs[right][1] <= qTime) {
                int server = logs[right][0];
                if (serverCount[server] == 0) {
                    activeServers++;
                }
                serverCount[server]++;
                right++;
            }
            while (left < numLogs && logs[left][1] < qTime - x) {
                int server = logs[left][0];
                serverCount[server]--;
                if (serverCount[server] == 0) {
                    activeServers--;
                }
                left++;
            }
            ans[origIdx] = n - activeServers;
        }

        return ans;
    }
}