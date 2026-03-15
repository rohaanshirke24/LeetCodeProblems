class Fancy {
    private static final long MOD = 1_000_000_007L;

    private final List<Long> vals;
    private long mul;
    private long add;

    public Fancy() {
        vals = new ArrayList<>();
        mul = 1;
        add = 0;
    }

    public void append(int val) {
        long normalized = (val - add + MOD) % MOD;
        normalized = normalized * modPow(mul, MOD - 2) % MOD;
        vals.add(normalized);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= vals.size()) {
            return -1;
        }
        return (int) ((vals.get(idx) * mul + add) % MOD);
    }

    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}


/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */