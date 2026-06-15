//给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 10⁵ 
// 
//
// Related Topics 贪心 数组 动态规划 👍 3278 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canJump(int[] nums) {
        /*贪心
        * 对于数组中的任意一个位置y，如何判断它是否可以到达？
        * 只要存在一个位置x，它本身可以到达，并且它跳跃的最大长度为x+nums[x]，
        * 这个值大于等于y，即x+nums[x] >= y，那么位置y也可以到达
        * 换句话说，对于每一个可以到达的位置x，它使得x+1，x+2，...，x+nums[x]这些连续的位置都可以到达
        * 依次遍历数组中的每一个位置，并且实时维护最远可以到达的位置
        * 在遍历的过程中，如果最远可以到达的位置大于等于数组中的最后一个位置，那就说明最后一个位置可达，我们就可以直接返回true作为答案。
        * 反之，如果在遍历结束后，最后一个位置仍然不可达，返回false。
        * */
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; i++) {
            if(i <= rightmost){
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n-1){
                    return true;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
