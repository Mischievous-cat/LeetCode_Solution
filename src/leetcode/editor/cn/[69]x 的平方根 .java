//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。 
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 4
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= x <= 2³¹ - 1 
// 
//
// Related Topics 数学 二分查找 👍 1776 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int mySqrt(int x) {
        /*二分查找
        * 由于x的平方根的整数部分ans是满足k^2<=x的最大k值，因此我们可以对k进行二分查找，从而得到答案。
        * 二分查找的下界为0，上界可以粗略地设定为x。
        * 在二分查找的每一步中，我们只需要比较中间元素mid的平方与x的大小关系，并通过比较的结果调整上下界的范围。
        * 由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案ans后，也就不需要再去尝试ans+1了
        * */
        int l = 0, r = x, ans = -1;
        while(l < r){
            int mid = l + (r-l)/2;
            if ((long) mid * mid <= x){
                ans = mid;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
