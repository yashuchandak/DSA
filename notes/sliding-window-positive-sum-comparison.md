# Sliding Window: Subarray Sum = K (Positive Numbers)

## Problem

Given an array of strictly positive integers, count non-empty contiguous subarrays whose sum equals `k`.

Example: `nums = [1, 1, 1], k = 2` → output: `2`.

Note: This is a positive-numbers-only variation. The full LeetCode 560 problem allows negative numbers and needs Prefix Sum + HashMap.

## My approach

```java
if(nums.length == 1) {
            if(nums[0] == k ) {
                return 1;
            } else {
                return 0;
            }
        }
        int i=0;
        int j=1;
        int sum = nums[0];
        int count = 0;
        while (true) {
            if(sum>k && i<nums.length) {
                sum = sum - nums[i];
                i++;
            } else if(sum<k && j<nums.length) {
                sum = sum + nums[j];
                j++;
            } else if(sum == k) {
                count++;
                if(j == nums.length) {
                    break;
                }
                sum = sum + nums[j];
                j++;
            } else {
                break;
            }
        }
        return count;
```

**What was good:** Running sum and two pointers avoid recalculating every subarray.

**Cons:**
- `while (true)` and multiple branches make pointer movement and termination harder to reason about.
- Special-casing a one-element array suggests the general logic doesn't cover all cases.
- Shrinking can create an empty window; for `k = 0`, that empty window may be counted incorrectly.

## Cleaner alternative

```java
int left = 0;
int sum = 0;
int count = 0;

for (int right = 0; right < nums.length; right++) {
    sum += nums[right];

    while (left <= right && sum > k) {
        sum -= nums[left];
        left++;
    }

    if (sum == k && left <= right) {
        count++;
    }
}

return count;
```

**Why it's cleaner:**
- `right` always expands the window.
- `left` shrinks it while the sum is too large.
- `left <= right` ensures the window is non-empty.

**Complexity:** O(n) time, O(1) extra space. Each pointer moves forward at most n times.

## Key takeaway

Sliding Window works here because all numbers are positive: expanding increases the sum and shrinking decreases it. With negative numbers, that property no longer holds.
