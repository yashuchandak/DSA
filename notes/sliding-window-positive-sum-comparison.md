# Sliding Window — Count Subarrays With Sum K (Positive Numbers Only)

## Question

Given an array of **strictly positive integers** and an integer `k`, count the number of contiguous, non-empty subarrays whose sum is exactly `k`.

Example:

```text
nums = [1, 1, 1]
k = 2
Output = 2
```

The two matching subarrays are `[1, 1]` at indices 0–1 and `[1, 1]` at indices 1–2.

> Important: This is a positive-numbers-only variation for practising Sliding Window. LeetCode 560 (Subarray Sum Equals K) allows negative numbers, so this sliding-window approach does not solve the full LC 560 problem.

## 1. My approach

Submitted during practice:

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

### What was good

- Identified the Sliding Window / Two Pointers direction for strictly positive numbers.
- Maintained a running sum instead of recalculating each subarray sum.
- Used the sum to decide whether to expand or shrink the window.

### Cons / things that made it harder

- `while (true)` makes termination and boundary conditions harder to reason about.
- Both pointers are managed manually in several branches, increasing the chance of off-by-one errors.
- The special case for an array of length one is a symptom that the general window setup does not handle all cases cleanly.
- When shrinking, the window can become empty. For `k = 0`, the code can count an empty window, even though a valid subarray must be non-empty.
- The code mixes window movement, boundary handling, and counting in the same conditional chain.

## 2. Alternative approach

Assistant's suggested version for strictly positive numbers:

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

### Why this is easier to reason about

- The `for` loop gives `right` one clear responsibility: expand the window by adding the next element.
- The inner `while` loop gives `left` one clear responsibility: shrink the window while its sum is too large.
- The `left <= right` condition ensures the window is non-empty before counting.
- There is no special case for a one-element array and no open-ended `while (true)` loop.

### Complexity

- Time: O(n) — each pointer moves forward at most n times.
- Auxiliary space: O(1).

## 3. Pattern takeaway

For an array of strictly positive numbers:

- Adding an element increases the window sum.
- Removing an element decreases the window sum.
- Therefore, if the sum is too small, expand; if it is too large, shrink.

This monotonic behaviour is what makes the Sliding Window approach work here.

If negative numbers are allowed, adding or removing an element can move the sum in either direction. The simple sum-based sliding-window decisions are no longer reliable. For the full LeetCode 560 problem, use Prefix Sum + HashMap instead.

## 4. Related practice

**LeetCode 209 — Minimum Size Subarray Sum**

This is a good next Sliding Window problem. It uses positive integers and asks for the minimum length of a contiguous subarray whose sum is greater than or equal to the target.

https://leetcode.com/problems/minimum-size-subarray-sum/
