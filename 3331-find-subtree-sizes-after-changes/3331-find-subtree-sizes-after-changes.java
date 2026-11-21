class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }
        int[] newParent = new int[n];
        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, parent, s, origTree, last, newParent);
        int[] degree = new int[n];
        for (int i = 1; i < n; i++) {
            degree[newParent[i]]++;
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (u == 0) continue; 

            int p = newParent[u];
            ans[p] += ans[u];
            if (--degree[p] == 0) {
                queue.offer(p);
            }
        }

        return ans;
    }

    private void dfs(int u, int[] parent, String s, List<Integer>[] origTree, int[] last, int[] newParent) {
        int c = s.charAt(u) - 'a';
        int prev = last[c];

        if (u == 0) {
            newParent[u] = -1;
        } else {
            newParent[u] = (prev != -1) ? prev : parent[u];
        }
        last[c] = u;

        for (int v : origTree[u]) {
            dfs(v, parent, s, origTree, last, newParent);
        }
        last[c] = prev;
    }
}