//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
//
// Related Topics 字符串 👍 2664 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convert(String s, int numRows) {
        /*利用二维矩阵模拟
        * 设n为字符串s的长度，r=numRows。
        * 对于r=1（只有一行）或者r>=n（只有一列）的情况，答案与s相同，我们可以直接返回s。
        * 对于其余情况，考虑创建一个二维矩阵，然后在矩阵上按Z字形填写字符串s，最后逐行扫描矩阵中的非空字符，组成答案
        * 根据题意，当我们在矩阵上填写字符时，会向下填写r个字符，然后向右上继续填写r-2个字符，最后回到第一行，
        * 因此Z字形变换的周期t=r+r-2=2r-2，每个周期会占用矩阵上的1+r-2=r-1列。
        * 因此我们有n/t向上取整个周期（最后一个周期视作完整周期），乘上每个周期的列数，得到矩阵的列数c=n/t向上取整乘（r-1）
        * 创建一个r行c列的矩阵，然后遍历字符串s并按Z字形填写。具体来说，设当前填写的位置为（x,y），即矩阵的x行y列。初始（x，y）=（0，0），即矩阵左上角。
        * 若当前数字下标i满足i mod t < r - 1，则向下移动，否则向上移动。
        * 填写完成后，逐行扫描矩阵中的非空字符，组成答案。
        * */
        int n = s.length(),r = numRows;
        if ( r == 1 || r >= n){
            return s;
        }
        // z字形变换的周期
        int t = r * 2 - 2;
        // n/t向上取整乘上每个周期的列数
        int c = (n + t - 1) / t * (r - 1);
        // 创建一个r行c列的矩阵，然后遍历字符串s并按z字形填写。具体来说，设当前填写的位置为（x，y），即矩阵的x行y列。
        char[][] mat = new char[r][c];
        // 初始（x，y）=（0，0），即矩阵左上角
        // 若当前字符下标i满足i mod t < r - 1 ，则向下移动，否则向上移动。
        for (int i = 0, x = 0, y = 0; i < n; i++) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1){
                ++x;// 向下移动
            }else{
                --x;
                ++y;// 向右上移动
            }

        }
        StringBuffer ans = new StringBuffer();
        // 遍历矩阵的每一行
        for (char[] row : mat){
            // 遍历每行的每个字符
            for (char ch : row){
                // 把非空字符追加到结果字符串中
                if (ch != 0){
                    ans.append(ch);
                }
            }
        }
        return ans.toString();

    }
}
//leetcode submit region end(Prohibit modification and deletion)
