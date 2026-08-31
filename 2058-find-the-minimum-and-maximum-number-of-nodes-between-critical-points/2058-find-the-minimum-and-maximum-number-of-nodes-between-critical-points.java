/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = Integer.MIN_VALUE;
        
        int firstCriticalIndex = -1;
        int previousCriticalIndex = -1;
        
        int index = 0;
        ListNode prev = null;
        ListNode current = head;

        while (current != null && current.next != null) {
            if (prev != null) {
                boolean isCritical = (prev.val < current.val && current.val > current.next.val) || 
                                     (prev.val > current.val && current.val < current.next.val);
                if (isCritical) {
                    if (previousCriticalIndex != -1) {
                        int distance = index - previousCriticalIndex;
                        minDistance = Math.min(minDistance, distance);
                        maxDistance = Math.max(maxDistance, index - firstCriticalIndex);
                    }
                    if (firstCriticalIndex == -1) {
                        firstCriticalIndex = index;
                    }
                    previousCriticalIndex = index;
                }
            }
            prev = current;
            current = current.next;
            index++;
        }

        if (minDistance == Integer.MAX_VALUE) {
            return new int[] {-1, -1};
        }
        return new int[] {minDistance, maxDistance};
    }
}