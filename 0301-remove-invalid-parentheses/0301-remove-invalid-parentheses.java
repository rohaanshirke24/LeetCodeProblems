class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (isValid(curr)) {
                    result.add(curr);
                    found = true;
                }
                if (!found) {
                    for (int j = 0; j < curr.length(); j++) {
                        char c = curr.charAt(j);
                        if (c != '(' && c != ')') continue;

                        String next = curr.substring(0, j) + curr.substring(j + 1);
                        if (visited.add(next)) {
                            queue.offer(next);
                        }
                    }
                }
            }
            if (found) break;
        }
        return result;
    }

    private boolean isValid(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') count++;
            else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}