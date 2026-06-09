//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 7981 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**二分查找
         给定两个有序数组，要求找到两个有序数组的中位数，最直观的思路有两种
         1、使用归并的方式，合并两个有序数组，得到一个大的有序数组。大的有序数组的中间位置的元素，即为中位数
         2、不需要合并两个有序数组，只要找到中位数即可。由于两个数组的长度已知，因此中位数对应的两个数组的下标之和也是已知的。维护两个指针，初始时分别指向两个数组的下标0的位置，每次将指向较小值的指针后移一位（如果一个针织已经到达数组末尾，则只需要移动另一个数组的指针），直到到达中位数的位置
         根据中位数的定义，当m+n是奇数时，中位数是两个有序数组中的第（m+n+1）/2个元素，当m+n是偶数时，中位数是两个有序数组中的第（m+n）/2个元素和第（m+n）/2+1个元素的平均值。因此，这道题可以转化为寻找两个有序数组中的第k小的数，其中k为（m+n）/2或（m+n）/2+1

         思路整理分析：
         首先可以想到两种思路：
         1、归并 归并后大的数组的中间位置即为中位数 时间O（m+n） 空间 O（m+n）
         2、不需合并 数组长度已知 中位数的下标已知 维护两个指针 时间O（m+n） 空间O（1）
         题目要求时间复杂度为O（log（m+n）） 怎么做？二分法
         m+n为奇数时 中位数为 （m+n+1）/2
         m+n为偶数时 中位数为 （m+n）/2和（m+n）/2+1的均值
         问题转换为寻找第k小的数
         奇 k=（m+n+1）/2
         偶 k=（m+n）/2和k=（m+n）/2+1
         找分割线
         1）左右元素相等/左-右=1
         2）左<=右
         eg.奇
         2 4 6 15
         1 7 8 10 17
         sizeleft = sizeright + 1
         eg.偶
         3 8 9 10
         2 4 6 12 18 20
         sizeleft = sizeright
         1）假设数组1的长度为m，数组2的长度为n
         当m+n为偶，sizeleft = （m+n）/2=（m+n+1）/2
         当m+n为奇，sizeleft = （m+n+1）/2
         这样我们将式子统一为（m+n+1）/2
         不用分奇偶讨论，只需确定其中一个数组的分割线位置另一个数组的分割线位置可以计算出来
         2）左<=右
         数组有序，则同一个数组内一定满足，数组之间需要满足 左max<右min （交叉小于等于）
         eg.满足条件
         2 4 6 | 15
         1 7 | 8 10 17
         eg.右边的数太小——将中位数分割线在数组1的位置右移
         2 4 | 6 15
         1 7 8 | 10 17
         eg.左边的数太大——将中位数分割线在数组1的位置左移
         2 4 6 8 ｜ 10 17
         1 ｜ 7 15

         特殊：在短数组上确定分割线位置
         1 1 ｜
         1 1 1 1 ｜ 1 1 1 1 1

         ｜ 1 1 1
         1 1 1 1 1 1 1 ｜ 1 1 1

         1 1 1 1 ｜
         ｜ 1 1 1 1

         ｜ 1 1 1 1
         1 1 1 1 ｜
         */

        if(nums1.length > nums2.length){
            int temp[] = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        // 分割线左边的所有元素需满足的个数m+(n-m+1)/2
        int totalLeft = (m+n+1) / 2;

        // 在nums的区间[0,m]内查找恰当的分割线
        // 使nums1[i-1] <= nums2[j] && nums2[j-1] <= nums1[i](数组有序考虑组间交叉小于等于即可)
        int left = 0;
        int right = m;

        while(left < right){
            // +1 是为了取右中位数，避免二分查找死循环
            // 当 left 和 right 相邻时（right = left + 1），不同的取法会导致不同结果：
            // 左中位数	left + (right - left)/2	left + 1/2 = left	取到左边界
            // 右中位数	left + (right - left + 1)/2	left + 2/2 = left+1	取到右边界
            /**
             while (left < right) {
             int i = left + (right - left) / 2;  // 取左中位数
             if (nums1[i-1] > nums2[j]) {
             right = i - 1;   // 右边界左移
             } else {
             left = i;        // 左边界移到 i
             }
             }
             当 right = left + 1 时：
             i = left + 1/2 = left（取到左边界）
             如果进入 else 分支：left = i = left（没变！）
             循环条件 left < right 仍然满足
             下一次循环 i 还是 left，left 还是不变
             无限循环 ❌
             */
            int i = left + (right - left + 1) / 2;
            int j = totalLeft -i;
            if(nums1[i-1] > nums2[j]){
                // 分割线太靠右了
                // 下一轮搜索的区间[left,i-1]
                right = i - 1;
            }else{
                // 分割线太靠左了
                // 下一轮搜索的区间[i,right]
                left = i;
            }

        }
        // 确定分割线位置
        int i = left;
        int j = totalLeft - i;
        // 如果 i == 0（分割线在最左边），左边没有元素，用最小值代替
        int nums1LeftMax =  (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
        // 如果 i == m（分割线在最右边），右边没有元素，用最大值代替
        int nums1RightMin = (i == m ? Integer.MAX_VALUE : nums1[i]);
        int nums2LeftMax = (j == 0 ? Integer.MIN_VALUE : nums2[j-1]);
        int nums2RightMin = (j == n ? Integer.MAX_VALUE : nums2[j]);

        // 返回中位数
        if((m+n)%2 == 1){
            // 总长度为奇数：中位数就是左边最大值中的较大者
            return Math.max(nums1LeftMax,nums2LeftMax);
        }else{
            // 总长度为偶数：中位数 = (左边最大值 + 右边最小值) / 2.0
            return (double) (Math.max(nums1LeftMax,nums2LeftMax) + Math.min(nums1RightMin,nums2RightMin) )/ 2;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
