class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (arr[current] == 0) {
                return true;
            }

            int left = current - arr[current];
            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                queue.add(left);
            }

            int right = current + arr[current];
            if (right < n && !visited[right]) {
                visited[right] = true;
                queue.add(right);
            }
        }

        return false;
    }
}