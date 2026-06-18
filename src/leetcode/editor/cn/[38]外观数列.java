//「外观数列」是一个数位字符串序列，由递归公式定义： 
//
// 
// countAndSay(1) = "1" 
// countAndSay(n) 是 countAndSay(n-1) 的行程长度编码。 
// 
//
// 
//
// 
// 
//
// 行程长度编码（RLE）是一种字符串压缩方法，其工作原理是通过将连续相同字符（重复两次或更多次）替换为字符重复次数（运行长度）和字符的串联。例如，要压缩字符
//串 "3322251" ，我们将 "33" 用 "23" 替换，将 "222" 用 "32" 替换，将 "5" 用 "15" 替换并将 "1" 用 "11" 替
//换。因此压缩后字符串变为 "23321511"。 
//
// 给定一个整数 n ，返回 外观数列 的第 n 个元素。 
//
// 示例 1： 
//
// 
// 输入：n = 4 
// 
//
// 输出："1211" 
//
// 解释： 
//
// countAndSay(1) = "1" 
//
// countAndSay(2) = "1" 的行程长度编码 = "11" 
//
// countAndSay(3) = "11" 的行程长度编码 = "21" 
//
// countAndSay(4) = "21" 的行程长度编码 = "1211" 
//
// 示例 2： 
//
// 
// 输入：n = 1 
// 
//
// 输出："1" 
//
// 解释： 
//
// 这是基本情况。 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 30 
// 
//
// 
//进阶：你能迭代解决该问题吗？
//
// Related Topics 字符串 👍 1133 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String countAndSay(int n) {
        /*遍历生成
        * 给定的递归公式定义的数字字符串序列如下：
        * countAndSay(1)='1';
        * countAndSay(n)是对countAndSay(n-1)的描述，然后转换成另一个数字字符串
        * 我们定义字符串Si表示countAndSay(i)，我们如果要求得Sn，则我们需先求出Sn-1，然后按照上述描述的方法生成，
        * 即从左到右一次扫描字符串Sn-1中连续相同的字符的最大数目，然后将字符的统计数目转化为数字字符串再连接上对应的字符。
        * 我们已知S1为“1”，我们按照上述方法依次生成S2，S3，...，Sn-1，Sn即可
        * */
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int start = 0;
            int pos = 0;
            while(pos < str.length()){// 外层：处理每一组
                while(pos < str.length() && str.charAt(pos) == str.charAt(start)){// 内层：统计当前组有多少个相同字符
                    pos++;
                }
                // 内层结束后，pos指向下一个不同字符的位置
                sb.append(Integer.toString(pos-start)).append(str.charAt(start));// 添加“个数+字符”
                start = pos;// 移动到下一组的开始
            }
            str = sb.toString();
        }
        return str;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
