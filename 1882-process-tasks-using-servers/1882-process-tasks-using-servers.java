class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }
        int time = 0;
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[]{server[1], server[2]});
            }
            if (freeServers.isEmpty()) {
                int[] nextServer = busyServers.poll();
                time = nextServer[0];
                freeServers.offer(new int[]{nextServer[1], nextServer[2]});
                while (!busyServers.isEmpty() && busyServers.peek()[0] == time) {
                    int[] server = busyServers.poll();
                    freeServers.offer(new int[]{server[1], server[2]});
                }
            }
            int[] bestServer = freeServers.poll();
            ans[i] = bestServer[1];
            int finishTime = time + tasks[i];
            busyServers.offer(new int[]{finishTime, bestServer[0], bestServer[1]});
        }
        
        return ans;
    }
}