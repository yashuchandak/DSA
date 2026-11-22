import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n = 0; n < nums.length; n++) {
            int complement = target - nums[n];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), n };
            }

            map.put(nums[n], n);
        }

        return new int[] {};
    }
}
