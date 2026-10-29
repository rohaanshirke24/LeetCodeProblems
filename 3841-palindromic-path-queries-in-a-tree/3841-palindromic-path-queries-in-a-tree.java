class Solution {
    private static final int LOG = 17; 

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int m = edges.length; 
        int[] to = new int[2 * m];
        int[] next = new int[2 * m];
        int edgeCount = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            to[edgeCount] = v;
            next[edgeCount] = head[u];
            head[u] = edgeCount++;

            to[edgeCount] = u;
            next[edgeCount] = head[v];
            head[v] = edgeCount++;
        }
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] depth = new int[n];
        int[] P = new int[n];
        int[] up = new int[n * LOG]; 
        char[] currChars = s.toCharArray();

        int[] curEdge = new int[n];
        System.arraycopy(head, 0, curEdge, 0, n);

        int[] stack = new int[n];
        int top = 0;
        int timer = 0;

        stack[0] = 0;
        tin[0] = ++timer;
        depth[0] = 0;
        P[0] = 1 << (currChars[0] - 'a');
        up[0] = 0; 

        while (top >= 0) {
            int u = stack[top];
            int e = curEdge[u];
            boolean pushed = false;
            while (e != -1) {
                int v = to[e];
                curEdge[u] = next[e];
                if (v != up[u * LOG + 0]) {
                    up[v * LOG + 0] = u;
                    depth[v] = depth[u] + 1;
                    P[v] = P[u] ^ (1 << (currChars[v] - 'a'));
                    tin[v] = ++timer;
                    stack[++top] = v;
                    pushed = true;
                    break;
                }
                e = curEdge[u];
            }
            if (!pushed) {
                tout[u] = timer;
                top--;
            }
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int anc = up[i * LOG + (j - 1)];
                up[i * LOG + j] = up[anc * LOG + (j - 1)];
            }
        }
        int[] nodeAt = new int[n + 1];
        for (int i = 0; i < n; i++) {
            nodeAt[tin[i]] = i;
        }

        int[] bit = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            int D_i = P[nodeAt[i]] ^ (i > 1 ? P[nodeAt[i - 1]] : 0);
            bit[i] ^= D_i;
            int parent = i + (i & -i);
            if (parent < bit.length) {
                bit[parent] ^= bit[i];
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            if (q.charAt(0) == 'u') {
                int firstSpace = q.indexOf(' ');
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                int ui = 0;
                for (int i = firstSpace + 1; i < secondSpace; i++) {
                    ui = ui * 10 + (q.charAt(i) - '0');
                }
                char c = q.charAt(secondSpace + 1);

                if (currChars[ui] != c) {
                    int delta = (1 << (currChars[ui] - 'a')) ^ (1 << (c - 'a'));
                    currChars[ui] = c;
                    updateBit(bit, tin[ui], delta);
                    updateBit(bit, tout[ui] + 1, delta);
                }
            } else {
                int firstSpace = q.indexOf(' ');
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                int ui = 0;
                for (int i = firstSpace + 1; i < secondSpace; i++) {
                    ui = ui * 10 + (q.charAt(i) - '0');
                }
                int vi = 0;
                for (int i = secondSpace + 1; i < q.length(); i++) {
                    vi = vi * 10 + (q.charAt(i) - '0');
                }

                int lca = getLCA(ui, vi, up, depth);
                int p_u = queryBit(bit, tin[ui]);
                int p_v = queryBit(bit, tin[vi]);
                int val_lca = 1 << (currChars[lca] - 'a');

                int pathMask = p_u ^ p_v ^ val_lca;
                ans.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return ans;
    }

    private void updateBit(int[] bit, int i, int delta) {
        for (; i < bit.length; i += i & -i) {
            bit[i] ^= delta;
        }
    }

    private int queryBit(int[] bit, int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) {
            sum ^= bit[i];
        }
        return sum;
    }

    private int getLCA(int u, int v, int[] up, int[] depth) {
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        for (int j = LOG - 1; j >= 0; j--) {
            if (depth[u] - (1 << j) >= depth[v]) {
                u = up[u * LOG + j];
            }
        }
        if (u == v) {
            return u;
        }
        for (int j = LOG - 1; j >= 0; j--) {
            int ancU = up[u * LOG + j];
            int ancV = up[v * LOG + j];
            if (ancU != ancV) {
                u = ancU;
                v = ancV;
            }
        }
        return up[u * LOG + 0];
    }
}