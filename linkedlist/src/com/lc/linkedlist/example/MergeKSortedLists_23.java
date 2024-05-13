package com.lc.linkedlist.example;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class MergeKSortedLists_23 {
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;

        ListNode c1 = new ListNode(2);
        ListNode c2 = new ListNode(6);
        c1.next = c2;

        ListNode[] lists = new ListNode[]{a1, b1, c1};

        a1 = mergeKLists_2(lists);

        while(a1!=null) {
            System.out.print(a1.val+" ");
            a1 = a1.next;
        }

    }

    /**
     * Method 1: use queue and merge two lists each time
     * */
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        Deque<ListNode> queue = new ArrayDeque<>();

        for(ListNode list: lists) {
            if(list != null)
                queue.offer(list);
        }


        while(queue.size() > 1) {
            ListNode head1 = queue.pollFirst();
            ListNode head2 = queue.pollFirst();
            head1 = mergeTwoLists(head1, head2);
            queue.offer(head1);
        }
        return queue.pollFirst();
    }

    private static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode();
        ListNode q = dummy;

        while(head1 != null && head2 != null) {
            if(head1.val <= head2.val) {
                q.next = head1;
                head1 = head1.next;
            } else {
                q.next = head2;
                head2 = head2.next;
            }
            q = q.next;
            q.next = null;
        }

        if(head1!=null) q.next = head1;
        if(head2!=null) q.next = head2;

        return dummy.next;
    }

    /**
     * Method 2: PriorityQueue
     * */
    public static ListNode mergeKLists_2(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(
                new Comparator<ListNode>() {
                    @Override
                    public int compare(ListNode o1, ListNode o2) {
                        return o1.val - o2.val;
                    }
                }
        );

        for(ListNode list: lists) {
            while(list!=null) {
                priorityQueue.offer(list);
                list = list.next;
            }
        }

        ListNode dummy = new ListNode();
        ListNode q = dummy;

        while(!priorityQueue.isEmpty()) {
            q.next = priorityQueue.poll();
            q = q.next;
            q.next = null;
        }

        return dummy.next;
    }
}
