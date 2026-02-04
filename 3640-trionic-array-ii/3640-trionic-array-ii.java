class Solution {
    public long maxSumTrionic(int[] nums) {
        final long NEG = Long.MIN_VALUE / 4; 
        long inc1 = NEG; 
        long dec  = NEG; 
        long inc2 = NEG; 
        long ans  = NEG;

        for (int i = 1; i < nums.length; i++) {
            long prevInc1 = inc1, prevDec = dec, prevInc2 = inc2;

            long newInc1 = NEG, newDec = NEG, newInc2 = NEG;

            if (nums[i - 1] < nums[i]) {
                newInc1 = Math.max(
                        (prevInc1 == NEG ? NEG : prevInc1 + nums[i]),
                        (long) nums[i - 1] + nums[i]
                );
                newInc2 = Math.max(
                        (prevInc2 == NEG ? NEG : prevInc2 + nums[i]),
                        (prevDec  == NEG ? NEG : prevDec  + nums[i])
                );
            }

            if (nums[i - 1] > nums[i]) {
                newDec = Math.max(
                        (prevDec  == NEG ? NEG : prevDec  + nums[i]),
                        (prevInc1 == NEG ? NEG : prevInc1 + nums[i])
                );
            }

            inc1 = newInc1;
            dec  = newDec;
            inc2 = newInc2;

            if (inc2 > ans) ans = inc2;
        }

        return ans;
    }
}
