//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个在 不同下标位置 的整数，使它们的和与 target 最
//接近。 
//
// 返回这三个数的和。 
//
// 假定每组输入只存在恰好一个解。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0], target = 1
//输出：0
//解释：与 target 最接近的和是 0（0 + 0 + 0 = 0）。 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 1000 
// -1000 <= nums[i] <= 1000 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 双指针 排序 👍 1813 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = Integer.MAX_VALUE / 2;// 除2防止减去一个负数溢出
        // 枚举a
        for (int i = 0; i < n - 2; i++) {
            // 优化一：保证和上一次枚举的元素不相等，避免重复
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            // 优化二：无论后面两个怎么选，选出的三数之和不会比s还小，左指针右移无意义
            int s = nums[i] + nums[i+1] + nums[i+2];
            if (s > target){
                if(s - target < Math.abs(best - target)){
                    best = s;
                }
                break;
            }
            // 优化三：x加上后面任意两个数都不超过s，所以下面的双指针就不需要跑了，但是nums[i]可能有更大的
            s = nums[i] + nums[n-2] + nums[n-1];
            if (s < target){
                if (target - s < Math.abs(best - target)){
                    best = s;
                }
                continue;
            }
            // 使用双指针枚举b和c
            int j = i + 1, k = n - 1;
            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为target直接返回答案
                if (sum == target){
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)){
                    best = sum;
                }
                if (sum > target){
                    // 如果和大于target，移动c对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while(j < k0 && nums[k0] == nums[k]){
                        --k0;
                    }
                    k = k0;
                }else{
                    // 如果和小于target，移动b对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while(j0 < k && nums[j0] == nums[j]){
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
