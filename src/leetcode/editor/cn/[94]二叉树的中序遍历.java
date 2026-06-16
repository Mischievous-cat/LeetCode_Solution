//给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 2442 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        /**递归
         中序遍历：按照访问左子树-根节点-右子树的方式遍历这棵树，而且在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数模拟这一过程
         定义inorder（root）表示当前遍历到root节点的答案，那么按照定义，我们只要递归调用inorder(root.left)来遍历root节点的左子树，然后将root节点的值加入答案，再递归调用inorder(root.right)来遍历root节点的右子树即可，递归终止的条件为碰到空节点
         */

        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }
    // 在 inorder 方法中，res 是一个用于收集结果的列表，它会被传递到每一次递归调用中，同一个列表会被不断地添加元素
    // 可以将res看作一个篮子
    public void inorder(TreeNode root, List<Integer> res){
        // 节点为空，直接返回
        if(root == null){
            return;
        }
        // 先遍历左子树
        inorder(root.left, res);
        // 访问当前节点
        res.add(root.val);
        // 遍历右子树
        inorder(root.right, res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
