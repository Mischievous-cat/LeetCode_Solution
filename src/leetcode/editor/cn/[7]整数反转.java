//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−2³¹, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= x <= 2³¹ - 1 
// 
//
// Related Topics 数学 👍 4181 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        /*题目需要判断反转后的数字是否超过32位有符号整数的范围[-2^31,2^31-1],例如x = 2123456789反转后的rev = 9876543212 > 2^31-1=2147483647,超过了32位有符号整数的范围。
        因此我们需要在推入之前，判断是否满足
        -2^31<=rev*10+digit<=2^记rev为翻转后的数字，为完成翻转，我们可以重复弹出x的末尾数字，将其推入rev的末尾，直至x为0
        【弹出和推入】
        弹出：digit = x % 10; x /= 10
        推入：rev = rev * 1031-1
        若该不等式不成立则返回0
        题目要求不允许使用64位整数，即运算过程中的数字必须在32位有符号整数的范围内，因此我们不能直接按照上述式子计算，另寻他路。
        转化为INT_MAX/INT_MIN的逆运算
        rev * 10 + digit <= INT_MAX
        rev * 10 + digit <= INT_MAX/10」 * 10 + （INT_MAX mod 10）
        rev * 10 + digit <= 214748364 * 10 + 7
        digit <= 214748364 * 10 + 7 - rev * 10
        情况一：rev < 214748364 不溢出
        情况二：rev > 214748364 一定溢出
        情况三：rev = 214748364 ：1）digit<=7 不溢出；2）digit>7 溢出
        */
        int rev = 0;
        while(x != 0){
            int num = x % 10;
            x /= 10;
            // 正整数溢出处理
            if (rev > Integer.MAX_VALUE || (rev == Integer.MAX_VALUE && num > Integer.MAX_VALUE % 10)){
                return 0;
            }
            // 负整数溢出处理
            if (rev < Integer.MIN_VALUE || (rev == Integer.MIN_VALUE && num < Integer.MIN_VALUE % 10)){
                return 0;
            }

            rev = rev * 10 + num;
        }
        return rev;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
