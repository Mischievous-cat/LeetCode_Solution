//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
//
// Related Topics 哈希表 字符串 回溯 👍 3269 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> letterCombinations(String digits) {
        /*回溯
        * 首先使用哈希表存储每个数字对应的所有可能的字符，然后进行回溯操作。
        * 回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历万电话号码的所有数字，则已有的数字排列是不完整的）。
        * 该字符串的初始为空。每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能是字母，并将其中的一个字母插入到一有的字母排列后面，
        * 然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。
        * 然后进行回退操作，遍历其余的字母排列。
        * 回溯算法用于寻找所有的可行解，如果发现一个解法不可能，则会舍弃不可行的解。
        * 在这道题中，由于每个数字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可*/

        // 创建一个集合，用来存储最终的所有结果
        List<String> combinations = new ArrayList<String>();
        // 如果输入为空，直接返回空集合
        if (digits.length() == 0){
            return combinations;
        }
        // 创建数字-字母的映射表
        Map<Character, String> phoneMap = new HashMap<Character,String>() {{
            put('2',"abc");
            put('3',"def");
            put('4',"ghi");
            put('5',"jkl");
            put('6',"mno");
            put('7',"pqrs");
            put('8',"tuv");
            put('9',"wxyz");
        }};
        // 调用回溯函数开始组合字母
        backtrack(combinations,phoneMap,digits,0,new StringBuffer());
        // 返回所有组合结果
        return combinations;
    }

    public void backtrack(
            List<String> combinations,// 最终结果
            Map<Character,String> phoneMap,// 映射表
            String digits,// 输入的字符串
            int index,// 正在处理第几个数字
            StringBuffer combination// 当前正在拼接的字符串
    ){
        // 递归终止条件
        if (index == digits.length()){
            combinations.add(combination.toString());
        }else{
            // 拿到当前数字，比如‘2’
            char digit = digits.charAt(index);
            // 拿到数字对应的字母，比如‘abc’
            String letters = phoneMap.get(digit);
            // 字符个数：3
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                // 把当前字母拼上去
                combination.append(letters.charAt(i));
                // 递归：处理下一个数字
                backtrack(combinations,phoneMap,digits,index+1,combination);
                // 回溯：撤销最后一个字母（！关键）
                combination.deleteCharAt(index);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
