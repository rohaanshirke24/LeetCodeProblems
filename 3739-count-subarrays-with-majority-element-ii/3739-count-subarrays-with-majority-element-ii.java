class Solution {
    static class Fenwick {
        int n;
        int[] tree;

        Fenwick(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        void update(int i, int delta) {
            while (i <= n) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        Fenwick bit = new Fenwick(2 * n + 1);

        int prefix = n + 1;   
        bit.update(prefix, 1);

        long ans = 0;

        for (int x : nums) {
            prefix += (x == target ? 1 : -1);
            ans += bit.query(prefix - 1);

            bit.update(prefix, 1);
        }

        return ans;
    }
}