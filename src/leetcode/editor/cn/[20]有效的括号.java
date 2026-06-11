//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 每个右括号都有一个对应的相同类型的左括号。 
// 
//
// 
//
// 示例 1： 
//
// 
// 输入：s = "()" 
// 
//
// 输出：true 
//
// 示例 2： 
//
// 
// 输入：s = "()[]{}" 
// 
//
// 输出：true 
//
// 示例 3： 
//
// 
// 输入：s = "(]" 
// 
//
// 输出：false 
//
// 示例 4： 
//
// 
// 输入：s = "([])" 
// 
//
// 输出：true 
//
// 示例 5： 
//
// 
// 输入：s = "([)]" 
// 
//
// 输出：false 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
//
// Related Topics 栈 字符串 👍 4979 👎 0


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        /*栈
        * 判断括号的有效性可以使用栈这一数据结构来解决。
        * 我们遍历给定的字符串s。当我们遇到一个左括号时，我们会期望在后续的遍历中，有一个相同类型的右括号将其闭合。
        * 由于后遇到的左括号要先闭合，因此我们可以将这个左括号放入栈顶。
        * 当我们遇到一个右括号时，我们需要将一个相同类型的左括号闭合。此时，我们可以取出栈顶的左括号并判断它们是否是相同类型的括号。
        * 如果不是相同的类型，或者栈中并没有左括号，那么字符串s无效，返回false。
        * 为了快速判断括号的类型，我们可以使用哈希表存储每一种括号。哈希表的键为右括号，值为相同类型的左括号。
        * 在遍历结束后，如果栈中没有左括号，说明我们将字符串中的所有左括号闭合，返回True，否则发挥False。
        * 注意到有效字符串的长度一定为偶数，一次如果字符串的长度为奇数，我们可以直接返回False，省去后续的遍历判断过程。*/
        int n = s.length();// 获取字符串长度
        if(n % 2 ==1 ){// 如果长度是奇数
            return false;// 绝对不可能配对，直接返回false
        }
        // 创建括号配对Map
        Map<Character, Character> pairs = new HashMap<Character,Character>(){{
            put(')','(');
            put(']','[');
            put('}','{');
        }};
        // 创建栈，存储左括号
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {// 遍历每个字符
            char ch = s.charAt(i);// 拿到当前字符
            // 当前是右括号
            if (pairs.containsKey(ch)){
                // 如果栈空或者栈顶左括号和当前右括号不匹配
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)){
                    return false;// 直接无效
                }
                stack.pop();// 匹配成功，弹出栈顶的左括号
            }else{
                // 当前是左括号
                // 压入栈，等待匹配
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
