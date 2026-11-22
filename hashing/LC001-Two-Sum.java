class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n=0; n<nums.length; n++) {
            if(map.containsKey(target-nums[n])) {
                return new int[]{n,map.get(target-nums[n])};
            }
            map.put(nums[n], n);
        }
        return new int[]{};
    }
}