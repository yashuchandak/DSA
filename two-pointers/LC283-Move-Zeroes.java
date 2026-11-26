class Solution {
    public void moveZeroes(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == 0) {
                int j = i+1;
                while(j<=nums.length-1 && nums[j] == 0) {
                    j++;
                }
                if(j == nums.length) {
                    return;
                }
                nums[i] = nums[j];
                nums[j] = 0;
            }
        }
    }
}

/*
 * Standard approach: Read pointer + write pointer
 *
 * i scans every element; j marks where the next non-zero element belongs.
 * Copy each non-zero element to nums[j], increment j, then fill the
 * remaining positions with zeroes.
 *
 * Dry run for [0, 1, 0, 3, 12]:
 *
 * My find-and-swap approach:
 * [0, 1, 0, 3, 12]
 * [1, 0, 0, 3, 12]
 * [1, 3, 0, 0, 12]
 * [1, 3, 12, 0, 0]
 *
 * Standard read-and-write approach:
 * [0, 1, 0, 3, 12]
 * [1, 1, 0, 3, 12]
 * [1, 3, 0, 3, 12]
 * [1, 3, 12, 3, 12]
 * [1, 3, 12, 0, 0]
 *
 * Both approaches take O(n) time and O(1) extra space.
 * The standard approach has simpler pointer management, but does not
 * necessarily perform fewer array writes for every input.
 */