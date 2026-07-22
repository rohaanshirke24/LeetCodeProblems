class Solution {

    static class Group {
        int start;
        int length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    static class SparseTable {
        private final int[][] st;

        SparseTable(int[] nums) {
            int n = nums.length;

            if (n == 0) {
                st = new int[1][0];
                return;
            }

            int levels = 32 - Integer.numberOfLeadingZeros(n);
            st = new int[levels][n];
            System.arraycopy(nums, 0, st[0], 0, n);

            for (int p = 1; p < levels; p++) {
                int len = 1 << p;
                int half = len >> 1;

                for (int i = 0; i + len <= n; i++) {
                    st[p][i] = Math.max(st[p - 1][i], st[p - 1][i + half]);
                }
            }
        }

        int query(int left, int right) {
            int len = right - left + 1;
            int p = 31 - Integer.numberOfLeadingZeros(len);

            return Math.max(
                st[p][left],
                st[p][right - (1 << p) + 1]
            );
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        List<Group> zeroGroups = new ArrayList<>();

        int[] groupIndex = new int[n];
        Arrays.fill(groupIndex, -1);

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
            }

            groupIndex[i] = zeroGroups.size() - 1;
        }

        int[] adjacentValue = new int[Math.max(0, zeroGroups.size() - 1)];

        for (int i = 0; i + 1 < zeroGroups.size(); i++) {
            adjacentValue[i] =
                zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }

        SparseTable sparseTable = new SparseTable(adjacentValue);
        List<Integer> answer = new ArrayList<>(queries.length);

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            int best = totalOnes;

            int leftPart = -1;
            if (s.charAt(l) == '0') {
                int g = groupIndex[l];
                Group group = zeroGroups.get(g);
                leftPart = group.length - (l - group.start);
            }

            int rightPart = -1;
            if (s.charAt(r) == '0') {
                int g = groupIndex[r];
                Group group = zeroGroups.get(g);
                rightPart = r - group.start + 1;
            }

            if (s.charAt(l) == '0' && s.charAt(r) == '0'
                    && groupIndex[l] + 1 == groupIndex[r]) {
                best = Math.max(best, totalOnes + leftPart + rightPart);
            }

            int firstFullGroup = groupIndex[l] + 1;
            int lastFullGroup =
                (s.charAt(r) == '1') ? groupIndex[r] : groupIndex[r] - 1;

            int pairLeft = firstFullGroup;
            int pairRight = lastFullGroup - 1;

            if (pairLeft <= pairRight) {
                best = Math.max(
                    best,
                    totalOnes + sparseTable.query(pairLeft, pairRight)
                );
            }

            if (s.charAt(l) == '0'
                    && groupIndex[l] + 1 <= lastFullGroup) {
                best = Math.max(
                    best,
                    totalOnes + leftPart + zeroGroups.get(groupIndex[l] + 1).length
                );
            }

            if (s.charAt(r) == '0'
                    && groupIndex[l] < groupIndex[r] - 1) {
                best = Math.max(
                    best,
                    totalOnes + rightPart + zeroGroups.get(groupIndex[r] - 1).length
                );
            }

            answer.add(best);
        }

        return answer;
    }
}