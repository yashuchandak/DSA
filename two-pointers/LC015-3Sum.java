class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        Set<List<Integer>> triplets = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.get(nums[i])-1);
            for(int j=i+1; j<nums.length; j++) {
                map.put(nums[j], map.get(nums[j])-1);
                int target = -nums[i]-nums[j];
                if(map.get(target) != null && map.get(target) > 0 && target >= nums[j]) {
                    triplets.add(List.of(nums[i], nums[j], -nums[i]-nums[j]));
                }
                map.put(nums[j], map.get(nums[j])+1);
            }
        }
        return triplets.stream().toList();  
    }
}