//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// Related Topics 链表 双指针 👍 3330 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.*;
import java.util.Deque;
import java.util.LinkedList;

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

/*方法一：计算链表长度
 * 从头节点开始对链表进行一次遍历，得到链表长度L。
 * 随后再从头节点开始对链表进行一次遍历，当遍历到第L-n+1个节点时，它就是我们需要删除的节点
 * 为了方便删除操作，我们可以从哑节点开始遍历L-n+1个节点。
 * 当遍历到第L-n+1个节点时，它的下一个节点就是我们需要删除的节点，这样我们只需要修改一次指针，就能完成删除操作。
 * */
/*class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    public int getLength(ListNode head){
        int length = 0;
        while(head != null){
            ++length;
            head = head.next;
        }
        return length;
    }
}*/

/*方法二：栈
利用栈先进后出的特点，弹出的第n个节点就是待删节点
 * *//*
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}*/




/*方法三：双指针
使用两个指针first和second，first比second超前n个节点。first到链尾时，second处于n
初始时first和second都指向头节点。首先使用first对链表进行遍历，遍历的次数为n。
此时，first与second之间隔了n-1个节点，即first比second超前了n个节点。
之后，同时使用first和second对链表进行遍历。当当、first遍历到链表的末尾（即first为空指针时），second恰好指向倒数第n个节点。
如果我们能得到的是倒数第n个节点的钱去节点而不是倒数第n个节点的话，删除操作会更加方便。因此考虑在初始时将second指向哑节点。
当first遍历到链表的末尾时，second的下一个节点就是我们要删除的节点。
 * */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        ListNode first = head;
        ListNode second = dummy;
        for(int i = 0; i < n; ++i){
            first= first.next;
        }
        while(first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
