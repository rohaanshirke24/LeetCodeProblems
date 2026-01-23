class Solution {
    static class Node {
        long val;
        int startIndex;      
        int ver = 0;         
        boolean alive = true;
        Node prev, next;

        Node(long v, int idx) {
            val = v;
            startIndex = idx;
        }
    }

    static class Pair {
        Node left, right;
        long sum;
        int leftStart;       
        int leftVer, rightVer;

        Pair(Node l, Node r) {
            left = l; right = r;
            sum = l.val + r.val;
            leftStart = l.startIndex;
            leftVer = l.ver;
            rightVer = r.ver;
        }
    }

    private void addPair(PriorityQueue<Pair> pq, Node a, Node b) {
        if (a != null && b != null) pq.offer(new Pair(a, b));
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node(nums[i], i);
        for (int i = 0; i < n; i++) {
            if (i > 0) nodes[i].prev = nodes[i - 1];
            if (i + 1 < n) nodes[i].next = nodes[i + 1];
        }
        int inv = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (nodes[i].val > nodes[i + 1].val) inv++;
        }
        if (inv == 0) return 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.sum != p2.sum) return Long.compare(p1.sum, p2.sum);
            return Integer.compare(p1.leftStart, p2.leftStart);
        });

        for (int i = 0; i + 1 < n; i++) addPair(pq, nodes[i], nodes[i + 1]);

        int ops = 0;
        int size = n;

        while (inv > 0 && size > 1) {
            Pair cur = pq.poll();
            if (cur == null) break;

            Node a = cur.left, b = cur.right;
            if (a == null || b == null || !a.alive || !b.alive) continue;
            if (a.next != b) continue;
            if (a.ver != cur.leftVer || b.ver != cur.rightVer) continue;

            Node p = a.prev;
            Node nx = b.next;
            if (p != null && p.val > a.val) inv--;
            if (a.val > b.val) inv--;
            if (nx != null && b.val > nx.val) inv--;
            a.val = a.val + b.val;
            a.ver++;
            b.alive = false;

            a.next = nx;
            if (nx != null) nx.prev = a;
            if (p != null && p.val > a.val) inv++;
            if (nx != null && a.val > nx.val) inv++;

            ops++;
            size--;
            if (p != null) addPair(pq, p, a);
            if (nx != null) addPair(pq, a, nx);
        }

        return ops;
    }
}
