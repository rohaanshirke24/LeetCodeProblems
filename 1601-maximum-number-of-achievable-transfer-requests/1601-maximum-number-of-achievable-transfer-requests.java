class Solution {
    private int maxRequests = 0;
    public int maximumRequests(int n, int[][] requests) {
        int[] balance = new int[n];
        dfs(0, 0, requests, balance);
        return maxRequests;
    }

    private void dfs(int index, int chosen, int[][] requests, int[] balance) {
        if (index == requests.length) {
            for (int value : balance) {
                if (value != 0) {
                    return;
                }
            }

            maxRequests = Math.max(maxRequests, chosen);
            return;
        }
        dfs(index + 1, chosen, requests, balance);
        int from = requests[index][0];
        int to = requests[index][1];

        balance[from]--; 
        balance[to]++;   

        dfs(index + 1, chosen + 1, requests, balance);
        balance[from]++;
        balance[to]--;
    }
}