class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] ans = new char[n][m];

        // Step 1: Rotate the matrix 90 degrees clockwise
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[j][m - i - 1] = box[i][j];
            }
        }

        // Step 2: Apply gravity to the stones
        for (int j = 0; j < m; ++j) {
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; --i) {
                if (ans[i][j] == '*') {
                    q.clear(); // Clear the queue when encountering an obstacle
                } else if (ans[i][j] == '.') {
                    q.offer(i); // Add empty spaces to the queue
                } else if (!q.isEmpty()) {
                    ans[q.pollFirst()][j] = '#'; // Move the stone to the first empty space
                    ans[i][j] = '.'; // Mark the current position as empty
                    q.offer(i); // Add the current position to the queue
                }
            }
        }

        return ans;
    }
}