//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序 排列 
// 
//
// Related Topics 链表 双指针 👍 1451 👎 0


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
    public ListNode deleteDuplicates(ListNode head) {
        /*一次遍历
        * 给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
        * 由于链表的头节点可能会被删除，因此我们需要额外使用一个哑节点（dummy node）指向链表的头节点。
        * 具体地，我们从指针指向链表的哑节点，随后开始对链表进行遍历。如果当前cur.next与cur.next.next对应的元素相同，那么我们就需要将cur.next以及后面拥有相同元素值的链表节点全部删除。
        * 我们记下这个元素值x，随后不断将cur.next从链表中移除，直到cur.next为空节点或者其元素值不等于x为止。此时，我们将链表中所有元素值为x的节点全部删除。
        * 如果当前cur.next与cur.next.next对应的元素不相同，那么说明链表中只有一个元素值为cur.next的节点，那么我们就可以将cur指向cur.next。
        * 当遍历完整个链表之后，我们返回链表的哑节点的下一个节点dummy.next即可。
        * 注意：cur.next以及cur.next.next可能为空节点
        * */
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            if (cur.next.val == cur.next.next.val){
                int x = cur.next.val;
                while(cur.next != null && cur.next.val == x){
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
