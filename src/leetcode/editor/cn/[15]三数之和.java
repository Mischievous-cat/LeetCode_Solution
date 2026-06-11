//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != 
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 8087 👎 0


import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        /*排序+双指针
        很容易想到的是三重循环。最坏的情况下，数组中的元素全部为0，即[0,0,0,0,0,...,0,0,0]
        任意一个三元组的和都为0.如果我们直接使用三重循环枚举三元组，会得到O(N^3)个满足题目要求的三元组（其中N是数组的长度）时间复杂度至少为O(N^3)。在这之后，我们还需要使用哈希表进行去重操作，得到不包含重复三元组的最终答案，又消耗了大量的空间。这个做法时间复杂度和空间复杂度都很高，因此我们换一种思路。
        不重复的本质是什么？保持三重循环的大框架不变，只需要保证：
        第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
        第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。
        也就是说，我们枚举的三元组（a,b,c）满足a<=b<=c，保证了只有(a,b,c)这个顺序会被枚举到，而 (b,a,c)、(c,b,a) 等等这些不会，这样就减少了重复。要实现这一点，我们可以将数组中的元素从小到大进行排序，随后使用普通的三重循环就可以满足上面的要求。
        同时，对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。
        eg.
        [0, 1, 2, 2, 2, 3]
         ^  ^  ^
         我们使用三重循环枚举到的第一个三元组为 (0,1,2)，如果第三重循环继续枚举下一个元素，那么仍然是三元组 (0,1,2)，产生了重复。因此我们需要将第三重循环「跳到」下一个不相同的元素，即数组中的最后一个元素 3，枚举三元组 (0,1,3)。

        nums.sort()
        for first = 0 .. n-1
            // 只有和上一次枚举的元素不相同，我们才会进行枚举
            if first == 0 or nums[first] != nums[first-1] then
                for second = first+1 .. n-1
                    if second == first+1 or nums[second] != nums[second-1] then
                        for third = second+1 .. n-1
                            if third == second+1 or nums[third] != nums[third-1] then
                                // 判断是否有 a+b+c==0
                                check(first, second, third)

        这种方法的时间复杂度仍然为O(N^3)，毕竟我们还是没有跳出三重循环的大框架。然而它是很容易继续优化的，可以发现，如果我们固定了前两重循环枚举到的元素a和b，那么只有唯一的c满足a+b+c=0.当第二重循环往后枚举一个元素b'时，由于b'>b，那么满足a+b'+c'=0的c'一定有c'<c，即c'在数组中一定出现在c的左侧。也就是说，我们可以从小到大枚举b，同时从大到小枚举c，即第二重循环和第三重循环实际上是并列的关系。
        有了这样的发现，我们就可以保持第二重循环不变，而将第三重循环编程一个从数组最右端开始向左移动的指针
        nums.sort()
        for first = 0 .. n-1
            if first == 0 or nums[first] != nums[first-1] then
                // 第三重循环对应的指针
                third = n-1
                for second = first+1 .. n-1
                    if second == first+1 or nums[second] != nums[second-1] then
                        // 向左移动指针，直到 a+b+c 不大于 0
                        while nums[first]+nums[second]+nums[third] > 0
                            third = third-1
                        // 判断是否有 a+b+c==0
                        check(first, second, third)

        这个方法就是我们常说的双指针，当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法，将枚举的时间复杂度从O(N^2)减少至O(N)。为什么是O(N)呢？这是因为在枚举的过程每一步中，「左指针」会向右移动一个位置（也就是题目中的 b），而「右指针」会向左移动若干个位置，这个与数组的元素有关，但我们知道它一共会移动的位置数为 O(N)，均摊下来，每次也向左移动一个位置，因此时间复杂度为 O(N)。
        注意到我们的伪代码中还有第一重循环，时间复杂度为 O(N)，因此枚举的总时间复杂度为 O(N^2)。由于排序的时间复杂度为 O(NlogN)，在渐进意义下小于前者，因此算法的总时间复杂度为 O(N^2)。
        */

        Arrays.sort(nums);// 先排序
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 跳过重复元素
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            // 双指针，目标是找到nums[l] + nums[r]= -nums[i]
            int l = i+1, r = nums.length - 1;
            int target = -nums[i];

            while(l < r){
                int sum = nums[l] + nums[r];
                if (sum == target){
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    // 跳过重复元素
                    while(l < r && nums[l] == nums[l-1]) l++;
                    while(l < r && nums[r] == nums[r+1]) r--;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
