class Solution {
    static class Node {
        int l, r;
        long val;
        Node(int l, int r, long val) {
            this.l = l;
            this.r = r;
            this.val = val;
        }
    }

    private int size;
    private long[] maxTree;
    private long[] minTree;

    public long maxTotalValue(int[] nums, int k) {
        build(nums);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));
        HashSet<Long> seen = new HashSet<>();

        push(0, nums.length - 1, pq, seen);

        long ans = 0;
        for (int taken = 0; taken < k; taken++) {
            Node cur = pq.poll();
            ans += cur.val;

            if (cur.l + 1 <= cur.r) {
                push(cur.l + 1, cur.r, pq, seen);
            }
            if (cur.l <= cur.r - 1) {
                push(cur.l, cur.r - 1, pq, seen);
            }
        }
        return ans;
    }

    private void push(int l, int r, PriorityQueue<Node> pq, HashSet<Long> seen) {
        long key = (((long) l) << 32) | (r & 0xffffffffL);
        if (!seen.add(key)) return;

        long mx = queryMax(l, r);
        long mn = queryMin(l, r);
        pq.offer(new Node(l, r, mx - mn));
    }

    private void build(int[] nums) {
        int n = nums.length;
        size = 1;
        while (size < n) size <<= 1;

        maxTree = new long[size << 1];
        minTree = new long[size << 1];

        Arrays.fill(maxTree, Long.MIN_VALUE);
        Arrays.fill(minTree, Long.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            maxTree[size + i] = nums[i];
            minTree[size + i] = nums[i];
        }

        for (int i = size - 1; i >= 1; i--) {
            maxTree[i] = Math.max(maxTree[i << 1], maxTree[i << 1 | 1]);
            minTree[i] = Math.min(minTree[i << 1], minTree[i << 1 | 1]);
        }
    }

    private long queryMax(int l, int r) {
        l += size;
        r += size;
        long res = Long.MIN_VALUE;

        while (l <= r) {
            if ((l & 1) == 1) res = Math.max(res, maxTree[l++]);
            if ((r & 1) == 0) res = Math.max(res, maxTree[r--]);
            l >>= 1;
            r >>= 1;
        }
        return res;
    }

    private long queryMin(int l, int r) {
        l += size;
        r += size;
        long res = Long.MAX_VALUE;

        while (l <= r) {
            if ((l & 1) == 1) res = Math.min(res, minTree[l++]);
            if ((r & 1) == 0) res = Math.min(res, minTree[r--]);
            l >>= 1;
            r >>= 1;
        }
        return res;
    }
}