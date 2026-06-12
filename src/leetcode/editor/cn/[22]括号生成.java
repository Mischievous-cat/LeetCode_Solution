//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 4100 👎 0


import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*public List<String> generateParenthesis(int n) {
        // 创建一个列表，用来存储所有有效的括号组合。
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2*n], 0, combinations);
        return combinations;
    }
    public void generateAll(char[] current, int pos, List<String> result){
        // 递归终止条件：当pos=数组长度时，检查是否有效
        if (pos == current.length){
            // 有效，转成字符串加入结果列表
            if (valid(current)){
                result.add(new String(current));
            }
        }else{
            // 在当前位置放 (，然后递归填充下一个位置。
            current[pos] = '(';
            generateAll(current, pos+1, result);
            // 在当前位置放 )，然后递归填充下一个位置。
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }
    public boolean valid(char[] current){
        int balance = 0;
        for (char c: current){
            if (c == '('){
                ++balance;
            }else{
                --balance;
            }
            if (balance < 0){
                return false;
            }
        }
        return balance == 0;
    }*/
    /*回溯法
    * 可以只在序列仍然保持有效时添加'('或')'，而不是每次添加。
    * 可以通过跟踪到目前为止放置的左括号和右括号的树木来做到
    * 1、如果左括号数量不大于n，放一个左括号
    * 2、右括号<左括号，放一个右括号
    * */
    public List<String> generateParenthesis(int n){
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }
    public void backtrack(List<String> ans, StringBuilder cur, int open ,int close, int max){
        if (cur.length() == max * 2){
            ans.add(cur.toString());
            return;
        }
        if (open < max){
            cur.append('(');
            backtrack(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length() - 1);// 递归返回
        }
        if (close < open){
            cur.append(')');
            backtrack(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
