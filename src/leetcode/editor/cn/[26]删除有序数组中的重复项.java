//给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 
//一致 。然后返回 nums 中唯一元素的个数。 
//
// 考虑 nums 的唯一元素的数量为 k。去重后，返回唯一元素的数量 k。 
//
// nums 的前 k 个元素应包含 排序后 的唯一数字。下标 k - 1 之后的剩余元素可以忽略。 
//
// 判题标准: 
//
// 系统会用下面的代码来测试你的题解: 
//
// 
//int[] nums = [...]; // 输入数组
//int[] expectedNums = [...]; // 长度正确的期望答案
//
//int k = removeDuplicates(nums); // 调用
//
//assert k == expectedNums.length;
//for (int i = 0; i < k; i++) {
//    assert nums[i] == expectedNums[i];
//} 
//
// 如果所有断言都通过，那么您的题解将被 通过。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：2, nums = [1,2,_]
//解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,1,2,2,3,3,4]
//输出：5, nums = [0,1,2,3,4,_,_,_,_,_]
//解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -100 <= nums[i] <= 100 
// nums 已按 非递减 顺序排列。 
// 
//
// Related Topics 数组 双指针 👍 3974 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int removeDuplicates(int[] nums) {
        /*快满指针
        对于给定的数组nums是有序的，因此对于任意i<j，如果nums[i]=nums[j]，则对任意i<=k<=j，必有nums[i]=nums[k]=nums[j]，即相等的元素在数组中的下标一定是连续的。利用数组有序特点，可以使用双指针解决问题
        如果数组nums的长度为0，则数组不包含任何元素，因此返回0.
        当数组nums的长度大于0时，数组中至少包含一个元素，在删除重复元素之后也至少剩下一个元素，因此nums[0]保持原状即可，从下标1开始删除重复元素。
        定义两个指针fast和slow分别为快指针和满指针，快指针表示遍历数组到达的下标为止，满指针表示下一个不同元素要填入的下标位置，初始时两个指针都指向下标1.
        假设数组nums的长度为n。将快指针fast依次遍历从1到n-1的每个位置，对于每个位置，如果nums[fast]！=nums[fast-1]，说明nums[fast]和之前的元素都不同，因此将nums[fast]的值复制到nums[slow]，然后将slow的值加1，即指向下一个位置。
        遍历结束后，从nums[0]到nums[slow-1]的每个元素都不相同且包含原数组中的每个不同的元素，因此新的长度即为slow，返回slow即可。
        * */
        int n = nums.length;
        if (n == 0){
            return 0;
        }
        int fast = 1, slow = 1;
        while(fast < n){
            if (nums[fast] != nums[fast-1]){
               nums[slow] = nums[fast];
               ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
