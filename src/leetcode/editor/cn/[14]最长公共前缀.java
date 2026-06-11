//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 如果非空，则仅由小写英文字母组成 
// 
//
// Related Topics 字典树 数组 字符串 👍 3514 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        /*横向扫描
        * 用LCP(LCP(LCP(S1,S2),S3),...Sn)
        * 基于该结论，可以得到一种查找字符串数组中的最长公共前缀的简单方法。
        * 一次遍历字符串数组中的每个字符串，对于每个遍历到的字符串，更新最长公共前缀，当遍历完所有的字符串后，即可得到字符串数组中的最长公共前缀。
        * 如果在尚未遍历完所有的字符串时，最长公共前缀已经是空串，则最长公共前缀一定是空串，
        * 因此不需要继续遍历剩下的字符，直接返回空串即可
        * */
        // 边界判断
        if (strs == null || strs.length == 0){
            return "";
        }
        // 把第一个字符串当作初始前缀
        String prefix = strs[0];
        int count = strs.length;
        // 依次和后面每一个字符串比较
        for (int i = 1; i < count; i++) {
            // 拿当前前缀和下一个字符串找公共前缀
            prefix = longestCommonPrefix(prefix,strs[i]);
            if (prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2){
        // 取两个字符串更短的那个长度，因为公共前缀不可能超过更短的那个字符串长度
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        // 核心循环：
        // 条件一：不超过更短的那个字符串的长度；条件二：两个字符串，当前位置的字符，相等
        while(index < length && str1.charAt(index) == str2.charAt(index)){
            index++;
        }
        // 截取str1从0到index的子串，这就是最长公共前缀
        return str1.substring(0,index);
    }


}
//leetcode submit region end(Prohibit modification and deletion)
