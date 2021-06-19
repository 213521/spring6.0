package leetcode.hexin;

import main.leetcode.model.ListNode;
import main.leetcode.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ezrealhexin
 * @date 2021-03-10 00:11
 **/
public class Offer {

    public static void main(String[] args) {

        System.out.println(test());
    }

    public static int test(){
        int i = 1;
        try{
            i++;
            System.out.println("try block, i = "+i);
            return i;
        }catch(Exception e){
            i ++;
            System.out.println("catch block i = "+i);
            return i;
        }finally{
            i = 10;
            System.out.println("finally block i = "+i);
        }
    }

    /**
     * 创建链表并返回头节点
     */
    public static ListNode createLinkedList() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        return node1;
    }


    /**
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * 示例 1：
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * 示例 2：
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     */
    public String reverseLeftWords(String s, int n) {
        if (n > s.length()) {
            return "";
        }
        String s1 = s.substring(0, n);
        s = s.substring(n, s.length());
        return s + s1;
    }


    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     */
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");

    }

    /**
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[2,7] 或者 [7,2]
     * 示例 2：
     * 输入：nums = [10,26,30,31,47,60], target = 40
     * 输出：[10,30] 或者 [30,10]
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{nums[i], target - nums[i]};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 题目:剑指 Offer 52. 两个链表的第一个公共节点
     * 注意：
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     * <p>
     * 让两个指针都循环一圈
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            if (l1 != null) {
                l1 = l1.next;
            } else {
                l1 = headB;
            }
            if (l2 != null) {
                l2 = l2.next;
            } else {
                l2 = headA;
            }
        }
        return l2;
    }


    public static ListNode copyNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = new ListNode(0);
        ListNode next = node;
        while (head != null) {
            next.next = new ListNode(head.val);
            next = next.next;
            head = head.next;
        }
        return node.next;
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
     * 还有一个 random 指针指向链表中的任意节点或者 null。
     * 输入：head = [[1,1],[2,1]]
     * 输出：[[1,1],[2,1]]
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;
        Map<Node, Node> map = new HashMap<>();
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        Node node1 = new Node(0);
        Node node2 = node1;
        while (head != null) {
            Node tempNode = map.get(head);
            node2.next = tempNode;
            tempNode.random = map.get(head.random);
            head = head.next;
            node2 = node2.next;
        }
        return node1.next;
    }

    /**
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，
     * 则最小的4个数字是1、2、3、4。
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * <p>
     * 示例 2：
     * <p>
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ints = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i == k) {
                break;
            }
            ints[i] = arr[i];
        }
        return ints;
    }


    /**
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * 示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    public static String reverseWords(String s) {
        String[] s1 = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int i = s1.length - 1; i >= 0; i--) {
            if (!"".equals(s1[i])) {
                list.add(s1[i]);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (!"".equals(list.get(i))) {
                if (i == list.size() - 1) {
                    stringBuilder.append(list.get(i));
                } else {
                    stringBuilder.append(list.get(i)).append(" ");
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 示例 1:
     * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
     * 输出: 2
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，
     * A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     * 示例 1:
     * 输入: [1,2,3,4,5]
     * 输出: True
     * 示例 2:
     * 输入: [0,0,1,2,5]
     * 输出: True
     * 22300
     */
    public static boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int min = 14;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
            if (!set.add(nums[i])) {
                return false;
            }
        }
        return max - min < 5;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     */
    public int[] exchange(int[] nums) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                list2.add(nums[i]);
            } else {
                list1.add(nums[i]);
            }
        }
        list1.addAll(list2);

        for (int i = 0; i < nums.length; i++) {

            nums[i] = list1.get(i);
        }
        return nums;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
     * 输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     * 示例 1：
     * 输入：[3,4,5,1,2]
     * 输出：1
     * 示例 2：
     * 输入：[2,2,2,0,1]
     * 输出：0
     */
    public int minArray(int[] numbers) {

        Arrays.sort(numbers);
        return numbers[0];
    }

    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * 示例 1：输入：n = 2,输出：1
     * 示例 2：输入：n = 5,输出：5
     * 0 1 1
     */
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            fib[i] = fib[i] % 1000000007;
        }
        return fib[n];
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * 示例 1：输入：n = 2 输出：2
     * 示例 2：输入：n = 7 输出：21
     * 示例 3：输入：n = 0 输出：1
     */
    public int numWays(int n) {
        if (n < 2) {
            return 1;
        }
        int[] fib = new int[n + 1];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            fib[i] = fib[i] % 1000000007;
        }
        return fib[n];
    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return 0;
        }
        int count = 0;
        int record = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                record = mid;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (record == -1) {
            return 0;
        } else {
            for (int i = record; i < nums.length; i++) {
                if (nums[i] == target) {
                    count++;
                } else {
                    break;
                }
            }
            for (int i = record; i >= 0; i--) {
                if (nums[i] == target) {
                    count++;
                } else {
                    break;
                }
            }
            return count - 1;
        }
    }


    /**
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     * 示例:
     * s = "abaccdeff"
     * 返回 "b"
     * s = ""
     * 返回 " "
     */
    public char firstUniqChar(String s) {
        Map<Character, Boolean> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, !map.containsKey(c));
        }
        for (Map.Entry<Character, Boolean> d : map.entrySet()) {
            if (d.getValue()) {
                return d.getKey();
            }
        }
        return ' ';
    }


    /**
     * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * 示例 1：
     * 输入: n = 3
     * 输出: 6
     * 示例 2：
     * 输入: n = 9
     * 输出: 45
     */
    public int sumNums(int n) {
        if (n == 1) {
            return n;
        }
        return n + sumNums(n - 1);
    }


    /**
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     * 示例 1：
     * 输入：nums = [3,4,3,3]
     * 输出：4
     * 示例 2：
     * 输入：nums = [9,1,7,9,7,9,7]
     * 输出：1
     */
    public int singleNumber(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }
}
