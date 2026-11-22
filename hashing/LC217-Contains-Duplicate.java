class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n=0; n<nums.length; n++) {
            if(set.contains(nums[n])) {
                return true;
            }
            set.add(nums[n]);
        }   
        return false;
    }
}