class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 1 || nums.length == 2) {
            return nums.length;
        }
        int k=1;
        for(int i=2; i<nums.length; i++) {
            if(nums[k]!=nums[i] || nums[i]!=nums[k-1]) {
                nums[k+1] = nums[i];
                k++;
            }
        }

        return k+1;    
    }
}

// Improvement:
// The length check above can be simplified to:
// if(nums.length <= 2)
