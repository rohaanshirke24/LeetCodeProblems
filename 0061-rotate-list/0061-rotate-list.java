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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k==0){
            return head;
        }
        int length = 1;// Length of the list
        ListNode tail = head;
        // Calculate the length of the list and find the tail node

        while(tail.next != null){
            tail = tail.next;
            length++;
        }

        k = k % length; // Calculate the actual value of k
        if(k==0){
            return head;// No rotation required
        }
        ListNode newTail = head;
         // Traverse to the new tail node
        for(int i=0; i<length-k-1; i++){
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;// New head node
        newTail.next = null; // Set the next of new tail node to null
        tail.next = head; // Set the next of the current tail to the original head
        return newHead;// Return the new head of the rotated list
    }
}