class Solution {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;

        while (i < j) {
            int area = Math.min(height[i], height[j]) * (j - i);
            max = Math.max(max, area);

            // Move the shorter wall: moving the taller one only reduces width while the shorter wall still limits the height.
            // Example: [1, 8, 3, 7] -> moving 7 inward gives area 1 * 2 = 2, less than the current 1 * 3 = 3.
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return max;
    }
}