//给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 11500 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        /*
        * 对于一个子串而言，如果它是回文串，并且长度大于2，那么将它首尾的两个字母去除之后，它仍然是个回文串。
        * 例如对于字符串“ababa”，如果我们已经知道“bab”是回文串，那么“ababa”一定是回文串，这是因为它的首尾两个字母都是“a”。
        * 根据这样的思路，我们就可以用动态规划的方法解决本题。
        * 我们用P(i,j)表示是字符串s的第i到j个字母组成的串（下文表示成s[i,j]）是否为回文串：
        * P(i,j) = true，如果子串Si...Sj是回文串
        * P(i,j) = false，其他情况
        * 这里的其他情况包含两种可能性：
        * s[i,j]本身不是一个回文串；
        * i>j，此时s[i,j]本身不合法。
        * 那么我们就可以写出动态规划的转移方程：
        * P(i,j) = P(i+1,j-1)^(Si==Sj)
        * 也就是说，只有s[i+1:j-1]是回文串，并且s的第i和j个字母相同时，s[i,j]才会是回文串。
        * 上述的讨论是建立在子串长度大于2的前提之上的，还需考虑动态规划中的边界条件，即子串的长度为1或2。
        * 长度为1显然是，长度为2只要它的两个字母相同即是。
        * 因此我们就可以写出动态规划的边界条件：
        * P(i,i) = true
        * P(i,i+1) = (Si==Si+1)
        * 根据这个思路，我们就可以完成动态规划了，最终的答案即为所有P(i,j) = true中j-1+1（即子串长度）的最大值。
        * 注意：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，因此一定要注意动态规划的循环顺序。
        * */
        int len = s.length();
        // 如果长度小于2（0或1），字符串本身就是回文，直接返回
        if (len < 2){
            return s;
        }

        // 记录最长回文子串的长度，初始为1（单个字符）
        int maxlen = 1;
        // 记录最长回文子串的起始索引，初始为0
        int begin = 0;
        
        // 创建dp表 为一个len*len的二维布尔数组
        // dp[i][j]表示：从索引i到索引j的子串s[i..j]是否是回文串
        boolean[][] dp = new boolean[len][len];
        
        // 初始化对角线 所有长度为1的子串都是回文
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        // 把字符串转为字符数组，方便快速访问
        char[] charArray = s.toCharArray();
        // 外层循环：枚举子串长度L，从2开始到len结束
        for (int L = 0; L < len; L++) {
            // 内层循环：枚举子串的左边界
            for (int i = 0; i < len; i++) {
                // 根据长度L和左边界i，计算右边界
                // j - i + 1 = L ——> j = L + i - 1
                int j = L + i - 1;
                // 如果右边界j超出了字符串长度，说明这个i无效
                if(j >= len){
                    break;
                }

                // 情况一：首尾字符不相等
                if (charArray[i] != charArray[j]){
                    dp[i][j] = false;
                }else{
                    // 首尾字符相等，需要看去掉首尾之后的子串是否为回文
                    // 子串长度<=3，一定是回文
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else{
                        // 子串长度>=4，取决于dp[i+1][j-1]
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                // 如果是回文，且长度大于之前记录的最长长度，就更新maxLen和begin
                if (dp[i][j] && j - i + 1 > maxlen){
                    maxlen = j - i + 1;
                    begin = i;
                }
            }
        }
        // 根据记录的起始位置和长度，截取并返回最长回文子串
        return s.substring(begin,begin + maxLen);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
