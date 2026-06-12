//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// Related Topics 递归 链表 👍 2630 👎 0


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
    public ListNode swapPairs(ListNode head) {
        /*递归
        如果链表中至少有两个节点，则两两交换后，原始头节点变成新的链表的第二个节点，原始链表的第二个节点变成新的链表的头节点，其余节点两两交换可以递归实现。
        head表示原始链表的头节点，newHead表示新的链表的头节点，则原始链表的其余节点的头节点是newHead.next
        head.next = swapPairs(newhead.next)表示将其余节点进行交换，交换后新的头节点为head的下一个节点
        然后令newHead.next = head 即完成所有节点的交换，返回newHead
        * */
        // 递归终止条件：链表中没有节点或者链表中只有一个节点，此时无法进行比较
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
