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

// Improvement:
// HashSet.add() returns false when the element is already present.
// This lets us combine the contains() check and add() into one operation:
//
// for (int num : nums) {
//     if (!set.add(num)) {
//         return true;
//     }
// }
//
// This avoids doing two hash lookups for each new element.
