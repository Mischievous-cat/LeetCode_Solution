//给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
//如果 needle 不是 haystack 的一部分，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "sadbutsad", needle = "sad"
//输出：0
//解释："sad" 在下标 0 和 6 处匹配。
//第一个匹配项的下标是 0 ，所以返回 0 。
// 
//
// 示例 2： 
//
// 
//输入：haystack = "leetcode", needle = "leeto"
//输出：-1
//解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= haystack.length, needle.length <= 10⁴ 
// haystack 和 needle 仅由小写英文字符组成 
// 
//
// Related Topics 双指针 字符串 字符串匹配 👍 2583 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strStr(String haystack, String needle) {
        /**暴力匹配
         我们可以让字符串needle与字符串haystack的所有长度为m的子串均匹配一次。
         为了减少不必要的匹配，我们每次匹配失败即立刻停止当前子串的匹配，对下一个子串继续匹配。如果当前子串匹配成功，我们返回当前子串的开始位置即可。如果所有子串都匹配失败，则返回-1。
         */
        // 草堆的长度，针的长度
        int n = haystack.length(), m = needle.length();
        // 确保从i开始截取m个字符不会越界
        for (int i= 0; i+ m <= n; i++){
            // 假设当前位置i能够匹配needle
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                // 草堆中从位置i开始，第j个字符 ！= 针中第j个字符
                if (haystack.charAt(i+j) != needle.charAt(j)){
                    flag = false;
                    break;
                }
            }
            if (flag){
                return i;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
