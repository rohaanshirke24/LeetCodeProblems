class Solution {
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        ArrayList<Integer> filtered = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            boolean redundant = false;
            for (int j = 0; j < i; j++) {
                if (coins[i] % coins[j] == 0) {
                    redundant = true;
                    break;
                }
            }
            if (!redundant) filtered.add(coins[i]);
        }

        int n = filtered.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = filtered.get(i);

        long left = 1;
        long right = (long) a[0] * (long) k;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (countMultiplesLE(mid, a) >= (long) k) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private long countMultiplesLE(long x, int[] coins) {
        int n = coins.length;
        long res = 0;

        int totalMasks = 1 << n;
        for (int mask = 1; mask < totalMasks; mask++) {
            long lcm = 1;
            boolean tooBig = false;

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    lcm = lcmLimited(lcm, coins[i], x);
                    if (lcm > x) {
                        tooBig = true;
                        break;
                    }
                }
            }
            if (tooBig) continue;

            long term = x / lcm;
            if ((Integer.bitCount(mask) & 1) == 1) res += term;
            else res -= term;
        }
        return res;
    }

    private long lcmLimited(long a, long b, long limit) {
        long g = gcd(a, b);
        long t = a / g;
        if (t > limit / b) return limit + 1;
        return t * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
