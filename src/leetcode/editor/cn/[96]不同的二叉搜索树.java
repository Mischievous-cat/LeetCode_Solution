//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 2746 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numTrees(int n) {
        /*动态规划
        假定n个节点存在BST的个数是G(n),令f(0)为以i为根的BST的个数，则
        G(n) = f(1) + f(2) + f(3) + f(4) + ... + f(n)
        当i为根节点时，其左子树节点个数为i-1个，右子树节点为n-i个，则
        f(i) = G(i-1) * G(n-i)
        综合上述两式得到卡特兰数：
        G(n) = G(0)*G(n-1)+G(1)*G(n-2)+...+G(n-1)*G(0)
        * */
        /*int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j-1] * G[i-j];
            }
        }
        return G[n];*/
        /*卡特兰数
        C0，Cn+1=[2(2n+1)/(n+2)]Cn
        * */
        long C = 1;
        for (int i = 0; i < n; i++) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
