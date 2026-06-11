//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
// 
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
//
// Related Topics 递归 链表 👍 4067 👎 0


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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /*递归
        list1[0] + merge(list1[1:],list2) list1[0]<list2[0]
        list2[0] + merge(list1[1],list2[1:]) otherwise
        边界处理：l1、l2为空时
        * */
        if(list1 == null){
            // 递归终止条件1:如果l1空了直接返回l2剩下的部分
            return list2;
        } else if (list2 == null) {
            // 递归终止条件2:如果l1空了直接返回l2剩下的部分
            return list1;
        } else if (list1.val < list2.val) { // 核心比较
            // 让l1的next指向[剩下的l1和l2合并后的结果]
            list1.next = mergeTwoLists(list1.next,list2);
            // 小的节点作为当前层的头返回
            return list1;
        }else{
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
