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

// Approach 2: Sorting + Two Pointers
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        Set<List<Integer>> triplets = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            int target = -nums[i];
            int j=i+1;
            int k=nums.length-1;
            while(j<k) {
                int sum = nums[j] + nums[k];
                if(sum == target) {
                    triplets.add(List.of(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if(sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return triplets.stream().toList();  
    }
}

// Further optimizations:
// 1. Skip duplicate nums[i] values so we don't repeat the same search for the same first element.
// 2. After finding a triplet, skip duplicate nums[j] and nums[k] values while moving both pointers to avoid generating duplicate triplets. This can eliminate the need for the HashSet.