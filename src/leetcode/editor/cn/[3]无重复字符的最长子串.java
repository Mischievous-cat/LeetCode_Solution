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


import java.util.HashSet;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        /**
         * 以 (a)bcabcbb开始的最长字符串为(abc)abcbb
         * 以 a(b)cabcbb开始的最长字符串为a(bca)bcbb
         * 以 ab(c)abcbb开始的最长字符串为ab(cab)cbb
         * 以 abc(a)bcbb开始的最长字符串为abc(abc)bb
         * 以 abca(b)cbb开始的最长字符串为abca(bc)bb
         * 以 abcab(c)bb开始的最长字符串为abcab(cb)b
         * 以 abcabc(b)b开始的最长字符串为abcabc(b)b
         * 以 abcabcb(b)开始的最长字符串为abcabcb(b)
         * 如果我们一次递增地枚举子串的起始位置，那么子串的结束位置也是递增的！
         * 这里的原因在于，假设我们选择字符串中的第k个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为rk。
         * 那么当我们选择第k+1个字符作为起始位置时，首先从k+1到rk的字符显然是不重复的，并且由于少了原本的第k个字符，我们可以尝试继续增大rk，直到右侧出现了重复字符位置。
         * 滑动窗口
         * 我们使用两个指针表示字符串中的某个字符（或窗口）的左右边界，其中左指针代表上文中枚举子串的起始位置，而右指针则为上文中的rk
         * 在每一步的操作中，我们会将左指针向右移动一格，表示我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。
         * 在移动结束后，这个子串就对应着以左指针开始的，不包含重复字符的最长子串。我们记录下这个子串的长度‘
         * 在枚举结束后，我们找到的最长的子串即为答案。
         * 在上面的流程中，我们还需要使用一种数据结构来判断呢是否有重复的字符，常用的数据结构为哈希集合（HashSet）。
         * 在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。
         */

        HashSet<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为-1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0){
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            // 右指针的下一个位置没有越界且下一个字符不在当前窗口的集合中
            while(rk + 1 < n && !occ.contains(s.charAt(rk + 1)) ){
                occ.add(s.charAt(rk + 1));
                // 右指针移动
                ++rk;
            }
            // 取最长的无重复子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
