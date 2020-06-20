package com.xiaojun.entity;

/**
 * @author long.luo
 * @date 2019/4/29 11:49
 */
public class ListNode {

    public  int val;
    public ListNode next;

    public ListNode(int x) {
        this.val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
