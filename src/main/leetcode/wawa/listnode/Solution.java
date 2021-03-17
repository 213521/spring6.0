package main.leetcode.wawa.listnode;

/**
 * 功能描述:
 *
 * @author sliu
 * @date 2021/3/17 8:33 下午
 */
public class Solution {


    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     *
     * @param head
     *
     * @return
     */
    public ListNode reverseListNode(ListNode head) {
//        if (head == null) return null;
//        ListNode pre = null, next;
//        while (head != null) {
//            next = head.next;
//            head.next = pre;
//            pre = head;
//            head = next;
//        }
//        return pre;
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListNode(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;

    }

}
