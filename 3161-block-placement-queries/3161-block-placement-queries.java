class Solution {
    static class Fenwick {
        private final int[] bit;

        Fenwick(int n) {
            bit = new int[n + 1];
        }

        private int lowbit(int x) {
            return x & -x;
        }

        void update(int i, int val) {
            while (i < bit.length) {
                bit[i] = Math.max(bit[i], val);
                i += lowbit(i);
            }
        }

        int query(int i) {
            int res = 0;
            while (i > 0) {
                res = Math.max(res, bit[i]);
                i -= lowbit(i);
            }
            return res;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int limit = Math.min(50000, queries.length * 3);

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(limit);

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        Fenwick fenwick = new Fenwick(limit + 2);

        Integer prev = null;
        for (int pos : obstacles) {
            if (prev != null) {
                fenwick.update(pos, pos - prev);
            }
            prev = pos;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; --i) {
            int[] q = queries[i];
            int type = q[0];
            int x = q[1];

            if (type == 1) {
                Integer left = obstacles.lower(x);
                Integer right = obstacles.higher(x);
                if (left != null && right != null) {
                    fenwick.update(right, right - left);
                }
                obstacles.remove(x);
            } else {
                int sz = q[2];
                int left = obstacles.floor(x);
                ans.add(fenwick.query(left) >= sz || x - left >= sz);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}