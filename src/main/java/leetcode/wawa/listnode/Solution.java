package leetcode.wawa.listnode;

import main.leetcode.model.ListNode;
import main.leetcode.wawa.listnode.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author sliu
 * @date 2021/3/17 8:33 下午
 */
public class Solution {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = swapNodes(node1, 2);
        printListNode(node);
    }

    /**
     * 给你链表的头节点 head 和一个整数 k 。
     * <p>
     * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
     *
     * @param head
     * @param k
     *
     * @return
     */
    public static ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        // 模拟指针，用来遍历链表
        ListNode cur = head;
        // 用来定位正数第k个节点
        ListNode first = head;
        // 用来定位倒数第k个节点
        ListNode last = head;
        // 用于节点的计数，和节点值的交换
        int count = 1;
        while (cur.next != null) {
            // 找到正数第k个节点
            if (count < k - 1) {
                first = first.next;
                // 找到倒数第k个节点
            } else if(count >k+1){
                last = last.next;
            }
            count++;
            cur = cur.next;
        }
        // 交换正数第k个节点和倒数第k个节点的值

        count = first.val;
        first.val = last.val;
        last.val = count;
        return head;
    }


    /**
     * 对链表进行插入排序。
     * <p>
     * <p>
     * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
     * <p>
     *  
     * <p>
     * 插入排序算法：
     * <p>
     * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     * 重复直到所有输入数据插入完为止。
     *
     * @param head
     *
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head;
        // 待插入
        ListNode curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * <p>
     * 如果有两个中间结点，则返回第二个中间结点。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     * <p>
     *
     * @param head
     *
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static ListNode removeElementsLoop(ListNode head, int val) {
        if (head == null) return null;
        ListNode dump = new ListNode(-1);
        dump.next = head;
        ListNode cur = dump;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dump.next;
    }


    public static ListNode revertPart(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dump = new ListNode(-1);
        dump.next = head;
        ListNode cur = dump;
        while (cur.next != null && cur.next.next != null) {
            ListNode next1 = cur.next;
            ListNode next2 = next1.next;
            cur.next = next2;

            ListNode temp = next2.next;
            next2.next = next1;
            next1.next = temp;

            cur = next1;
            printListNode(dump);
        }
        return dump.next;
    }


    public static void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 请实现 copyRandomList 函数，复制一个复杂链表。
     * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     *
     * @param head
     *
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }


    /**
     * 输入一个链表，输出该链表中倒数第k个节点。
     * 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     */

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return head;
        ListNode cur = head;
        int index = 0;
        while (cur != null && index < k) {
            cur = cur.next;
            index++;
        }
        while (cur != null) {
            cur = cur.next;
            head = head.next;
        }
        return head;
    }

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

    /**
     * 两个链表的第一个公共节点
     *
     * @param headA
     * @param headB
     *
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode ha = headA;
        ListNode hb = headB;
        while (ha != hb) {
            ha = ha == null ? headB : ha.next;
            hb = hb == null ? headA : hb.next;
        }
        return hb;
    }

}
