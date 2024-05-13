package com.lc.linkedlist.example;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SortList_148 {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(-1);
        ListNode a2 = new ListNode(5);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(0);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        a1 = sortList_2(a1);

        while(a1!=null) {
            System.out.print(a1.val+" ");
            a1 = a1.next;
        }
        System.out.println();
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

    /**
     * Method 1: merge sort from top to bottom
     * O(nlogn)
     * */
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy, fast = dummy;

        while(fast.next !=null && fast.next.next!=null) {
            p = p.next;
            fast = fast.next.next;
        }

        // break the list
        ListNode head2 = p.next;
        p.next = null;

        head = sortList(head);
        head2 = sortList(head2);

        dummy.next = mergeSortedList(head, head2);

        return dummy.next;
    }

    private static ListNode mergeSortedList(ListNode head1, ListNode head2) {

        ListNode dummy = new ListNode(0);
        ListNode q = dummy;
        ListNode p1 = head1, p2 = head2;

        while(p1!=null && p2!=null) {
            if(p1.val <= p2.val) {
                q.next = p1;
                p1 = p1.next;
            }
            else {
                q.next = p2;
                p2 = p2.next;
            }
            q = q.next;
        }

        if(p1!=null) q.next = p1;
        if(p2!=null) q.next = p2;

        return dummy.next;
    }


    /**
     * Method 2: Heap
     * O(n)
     * */
    public static ListNode sortList_2(ListNode head) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(
                new Comparator<ListNode>() {
                    @Override
                    public int compare(ListNode o1, ListNode o2) {
                        return o1.val - o2.val;
                    }
                }
        );

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        while(head != null) {
            priorityQueue.add(head);
            head = head.next;
        }

        while(!priorityQueue.isEmpty()) {
            ListNode cur = priorityQueue.poll();
            cur.next = null;
            p.next = cur;
            p = p.next;
        }

        return dummy.next;
    }



}
