package com.xiaojun;

import com.xiaojun.entity.ListNode;

/**
 * @author long.luo
 * @date 2019/4/29 11:51
 */
public class ReverseList {

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static boolean checkCircle(ListNode list) {
        if (list == null) {
            return false;
        }
        ListNode fast = list.next;
        ListNode slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            System.out.println("次数");
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static ListNode findMiddleNode(ListNode list) {
        if (list == null) return null;

        ListNode fast = list;
        ListNode slow = list;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void bubbleSort(int[] a, int n) {
        if (n == 1) return;
        for (int i = 0; i < n; ++i) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    public static void insertionSort(int[] a, int n) {
        if (n == 1) return;
        for (int i = 1; i < n; ++i) {
            int value = a[i];

            int j = i - 1;
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }

            a[j + 1] = value;
        }
    }

    public static void selectSort(int[] a, int n) {
        if (n == 1) return;

        for (int i = 0; i < n - 1; ++i) {

            int minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }

    }

    public static void main(String[] args) {
        /*ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode1);

        System.out.println(listNode1.val);
        System.out.println(listNode1.next.val);
        System.out.println(listNode1.next.next.val);

        ListNode listNode = reverseList(listNode1);

        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        System.out.println(listNode.next.next.val);

        System.out.println(checkCircle(listNode));

        System.out.println(findMiddleNode(listNode1).val);*/

        int[] a = {5, 3, 1, 6, 7, 9, 4};
        //bubbleSort(a, 7);
        insertionSort(a, 7);
    }
}
