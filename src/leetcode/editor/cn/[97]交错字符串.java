//给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。 
//
// 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串： 
//
// 
// s = s1 + s2 + ... + sn 
// t = t1 + t2 + ... + tm 
// |n - m| <= 1 
// 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ... 
// 
//
// 注意：a + b 意味着字符串 a 和 b 连接。 
//
// 
//
// 示例 1： 
// 
// 
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s1 = "", s2 = "", s3 = ""
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s1.length, s2.length <= 100 
// 0 <= s3.length <= 200 
// s1、s2、和 s3 都由小写英文字母组成 
// 
//
// 
//
// 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它? 
//
// Related Topics 字符串 动态规划 👍 1205 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        /*动态规划
        双指针错在哪里？指针p1指向s1的头部，指针p2指向s2的头部，指针p3指向s3的头部，每次观察p1和p2指向的元素哪一个和p3指向的元素相等，相等则匹配并后移指针
         反例：s1=aabcc，s2=dbbca，s3=aadbbcbcac
         解决的正确方法是动态规划。首先如果|s1|+|s2|不等于s3，那s3必然不可能由s1和s2交错组成。在|s1|+|s2|=|s3|时，我们可以用动态规划来求解。
         我们定义f(i,j)表示s1的前i个元素和s2的前j个元素是否能交错组成s3的前i+j个元素。如果s1的第i个元素和s3的前i+j个元素相等，那么s1的前i个元素和s2的前j个元素是否能交错组成s3的前i+j个元素取决于s1的前i-1个元素和s2的前j个元素是否能交错组成s3的前i+j-1个元素，即此时f(i,j)取决于f(i-1,j)，在此情况下如果f(i-1,j)为真，则f(i,j)也为真。于是我们可以推导出这样的动态规划转移方程：
         f(i,j) = [f(i-1,j) and s1(i-1) = s3(p)] or [f(i,j-1) and s2(j-1) = s3(p)]
         其中p=i+j-1。边界条件为f(0,0) = True。
         */
        /*int n = s1.length(), m = s2.length(), t = s3.length();
        // 如果s1和s2的长度之和不等于s3的长度，那么s3不可能由它们交错组成吗直接返回false。
        if (n + m != t){
            return false;
        }
        // 创建一个二维布尔数组f，大小为(n+1)*(m+1)
        boolean[][] f = new boolean[n+1][m+1];

        // f[i][j]表示：s1的前i个字符和s2的前j个字符能否交错组成s3的前i+j个字符
        // 空串+空串可以组成空串，所以初始化为true
        f[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 因为 f[i][j]对应的是s3的前i+j个字符，所以最后一个字符的索引是i+j-1
                int p = i + j - 1;
                // 如果 i>0，说明可以从f[i-1][j]转移过来（即s1多取一个字符）
                // 条件是：f[i-1][j]为true，且s1的第i个子非鱼（索引i-1）等于s3当前字符s3[p]
                if (i > 0){
                    // ｜｜ 防止未来的修改导致 bug 如果以后代码逻辑变了，比如 f[i][j] 在进入循环前可能已经有值了，用 || 能保证不会意外覆盖：
                    f[i][j] = f[i][j] || (f[i-1][j] && s1.charAt(i-1) == s3.charAt(p));
                }
                // 条件是：f[i][j-1] 为 true，且 s2 的第 j 个字符（索引 j-1）等于 s3 当前字符 s3[p]。
                if (j > 0){
                    f[i][j] = f[i][j] || (f[i][j-1] && s2.charAt(j-1) == s3.charAt(p));
                }
            }
        }
        return f[n][m];*/
        /*滚动数组优化
        这个实现的时间复杂度和空间复杂度都是 O(nm)。
        使用滚动数组优化空间复杂度。 因为这里数组 f 的第 i 行只和第 i−1 行相关，所以我们可以用滚动数组优化这个动态规划，这样空间复杂度可以变成 O(m)。
        * */
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t){
            return false;
        }
        boolean[] f = new boolean[m+1];
        f[0] = true;
        // 外层：逐行更新 内层：从左到右更新当前行
        // 滚动数组是 保存dp二位数组-->只保存一行，用上一行更新当前行
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                // 尝试从上边来
                if (i > 0){
                    f[j] = f[j] && s1.charAt(i-1) == s3.charAt(p);
                }
                // 尝试从左边来
                if (j > 0){
                    // 这里用｜｜的意思是：不管“从上方来”的结果如何，我再试试“从左方来”
                    // 如果“从上方来”已经成功了（f[j]是true），那太好了，保持true
                    // 如果“从上方来”失败了（f[j]是false），没关系，我再看看“从左方来”能否成功
                    f[j] = f[j] || (f[j-1] && s2.charAt(j-1) == s3.charAt(p));
                }
            }

        }
        return f[m];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
