//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 请必须使用时间复杂度为 O(log n) 的算法。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,3,5,6], target = 5
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,3,5,6], target = 2
//输出: 1
// 
//
// 示例 3: 
//
// 
//输入: nums = [1,3,5,6], target = 7
//输出: 4
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 为 无重复元素 的 升序 排列数组 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 二分查找 👍 2698 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        /*二分查找
        * 考虑插入的位置pos，它成立的条件为
        * nums[pos-1] < target <= nums[pos]
        * 其中nums代表排序数组。由于如果存在这个目标值，我们返回的索引也是pos，因此我们可以将两个条件合并得出最后的目标：
        * 在一个有序数组中找第一个大于等于target的下标
        * 不断用二分法逼近查找第一个大于等于target的下标
        * */
        int n = nums.length;
        int left = 0, right = n - 1;
        while(left < right){
            int mid = ((right - left) >> 1) + left;
            if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        // 最终 left 会停在第一个 >= target 的位置。
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
