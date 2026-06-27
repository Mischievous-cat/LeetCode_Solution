//有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
// 
//
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新
//排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅由数字组成 
// 
//
// Related Topics 字符串 回溯 👍 1608 👎 0


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) {
        /*回溯
        * 设给出的字符串为s。我们用递归函数dfs(segId,segStart)表示我们正在从s[segStart]的位置开始，搜索IP地址中的第segId段，其中segId属于{0,1,2,3}。
        * 由于IP地址的每一段必须是[0,255]中的整数，因此我们从segStart开始，从小到大依次枚举当前这一段IP地址的结束位置segEnd。
        * 如果满足要求，就递归地进行下一段搜索，调用递归函数dfs(segId+1,segEnd+1)。
        * 特别地，由于IP地址的每一段不能有前导0，因此如果s[segStart]等于字符0，那么IP地址的第segId段只能为0，需要作为特殊情况进行考虑。
        * 在搜索的过程中，如果我们已经得到了全部的四段IP地址（即segId=4），并且遍历完了整个字符串（即segStart=｜s｜，其中｜s｜表示字符串s的长度），那么就复原出了一种满足要求的IP地址，将其加入答案。
        * 在其他的时刻，如果提前遍历完了整个字符串，那么我们需要结束搜索，回溯到上一步。
        * */
        segments = new int[SEG_COUNT];
        dfs(s,0,0);
        return ans;
    }
    public void dfs(String s, int segId, int segStart){
        // 如果找到了4段IP地址并且遍历完了字符串，那么就是一种答案
        if(segId == SEG_COUNT){
            if (segStart == s.length()){
                // 创建一个StringBuffer来拼接IP地址
                // 遍历4段，把每段数字追加进去
                // 如果不是最后一段（i ！= 3），在后面加一个.
                // 拼接完成后，将字符串加入答案列表ans
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; i++) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1){
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }
        // 如果还没有找到4段IP地址就已经遍历完了字符串，那么提前回溯
        // 剪枝条件：还没凑够4段，但字符串已经用完了
        if (segStart == s.length()){
            return;
        }
        // 由于不能有前导零，如果当前数字为0，那么这一段IP地址只能为0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId+1, segStart+1);
            return;
        }
        // 一般情况：枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); segEnd++) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            // 检查当前数字是否在[1,255]范围内
            // 如果是合法的：记录当前段的值到segments[segId]递归处理下一段(segId+1)，从下一位置开始(segEnd+1)
            // 如果超过255: 直接break，不再继续尝试更长的数字（因为只会更大）
            if (addr > 0 && addr <= 0xFF){
                segments[segId] = addr;
                dfs(s, segId+1, segEnd+1);
            }else{
                break;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
