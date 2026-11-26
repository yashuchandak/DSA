# LC283 – Move Zeroes

## My approach: Find and swap
- Iterate through the array.
- When a zero is found, scan forward to find the next non-zero element.
- Swap it into the zero's position.
- Both pointers only move forward, so the total time is O(n), with O(1) extra space.

## Standard approach: Read pointer + write pointer

Use two pointers:
- `i` scans every element.
- `j` marks where the next non-zero element should be written.

First compact all non-zero elements to the beginning:

```java
int j = 0;

for (int i = 0; i < nums.length; i++) {
    if (nums[i] != 0) {
        nums[j] = nums[i];
        j++;
    }
}
```

Then fill the remaining positions with zeroes:

```java
while (j < nums.length) {
    nums[j] = 0;
    j++;
}
```

### Example

Input: `[0, 1, 0, 3, 12]`

After compacting non-zero elements: `[1, 3, 12, 3, 12]`

After filling the rest with zeroes: `[1, 3, 12, 0, 0]`

### Key idea
- `i` moves on every iteration.
- `j` moves only when a non-zero element is found.
- Since `j <= i`, writing at `j` never overwrites an unprocessed element.
- Time: O(n); extra space: O(1).

The standard approach compacts non-zero elements first, then fills the remaining positions with zeroes.