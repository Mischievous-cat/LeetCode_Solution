//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
// 
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
//
// Related Topics 递归 链表 数学 👍 11991 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
        * 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。
        * 具体而言，如果当前两个链表处相应位置的数字为n1，n2，进位值为carry，则它们的和为n1+n2+carry；
        * 其中，答案链表处相应位置的数字为（n1+n2+carry）mod 10，而新的进位值为n1+n2+carry向下取整。
        * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个0。
        * 此外，如果链表遍历结束后，有carry>0，还需要在答案链表的后面附加一个节点，节点的值为carry*/
        ListNode head = null, tail = null;
        int carry = 0;
        // 只要两个链表至少有一个还有节点，就继续循环
        while (l1 != null || l2 != null){
            // 如果当前节点存在，取它的值；不存在，取0
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            // 当前位的总和 = 第一个数的当前位 + 第二个数的当前位 +上一次的进位
            int sum = n1 + n2 + carry;
            // 创建第一个节点
            if(head == null){
                head = tail = new ListNode(sum % 10);
            }else{
                // 追加后续节点
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            // 更新进位
            carry = sum / 10;
            // 移动指针
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        // 两个链表均为空 即两个链表处理完成
        // 如果还有进位 追加进位节点
        if(carry > 0){
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
