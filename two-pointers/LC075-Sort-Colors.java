class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        for(int n : nums) {
            if(n == 0) {
                count0++;
            } else if(n == 1) {
                count1++;
            }
        }
        int i = 0;
        while(i<count0) {
            nums[i] = 0;
            i++;
        }
        while(i<count0+count1) {
            nums[i] = 1;
            i++;
        }
        for(; i<nums.length; i++) {
            nums[i] = 2;
        }    
    }
}