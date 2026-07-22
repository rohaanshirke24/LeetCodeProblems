class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        for (int left = 0, right = jobs.length - 1; left < right; left++, right--) {
            int temp = jobs[left];
            jobs[left] = jobs[right];
            jobs[right] = temp;
        }

        int low = jobs[0]; 
        int high = 0;      
        for (int job : jobs) {
            high += job;
        }

        while (low < high) {
            int limit = low + (high - low) / 2;
            int[] workerLoad = new int[k];

            if (canAssign(jobs, 0, workerLoad, limit)) {
                high = limit;      
            } else {
                low = limit + 1;  
            }
        }

        return low;
    }

    private boolean canAssign(int[] jobs, int jobIndex, int[] workerLoad, int limit) {
        if (jobIndex == jobs.length) {
            return true;
        }

        int job = jobs[jobIndex];

        for (int worker = 0; worker < workerLoad.length; worker++) {
            if (workerLoad[worker] + job > limit) {
                continue;
            }
            boolean sameLoadSeen = false;
            for (int previous = 0; previous < worker; previous++) {
                if (workerLoad[previous] == workerLoad[worker]) {
                    sameLoadSeen = true;
                    break;
                }
            }
            if (sameLoadSeen) {
                continue;
            }

            workerLoad[worker] += job;

            if (canAssign(jobs, jobIndex + 1, workerLoad, limit)) {
                return true;
            }

            workerLoad[worker] -= job;
        }

        return false;
    }
}