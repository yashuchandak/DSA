/*
First attempt: horizontal-level simulation.

class Solution {
    public int trap(int[] height) {
        int max = 0;
        for(int i=0; i<height.length; i++) {
            if(height[i]>max) {
                max = height[i];
            }
        }

        int area = 0;
        for(int n=0; n<max; n++) {
            int j=0;
            level:
            while(j<height.length-2) {
                while(height[j]<=0 || height[j+1]>0) {
                    j++;
                    if(j == height.length-2) break level;
                }
                int k = j+1;
                while(height[k]<=0) {
                    k++;
                    if(k == height.length) break level;
                }
                area = area + (k-j-1);
                j = k;
                k = k+1;
            }
            for(int i=0; i<height.length; i++) {
                height[i]--;
            }
        }
        return area;  
    }
}
*/

class Solution {
    public int trap(int[] height) {
        int i = 0;
        int j = height.length-1;
        int area = 0;
        int levelReached = 0;
        while(i<j) {
            while(height[i] <= levelReached) {
                i++;
                if(i == j) return area;
            }
            while(height[j] <= levelReached) {
                j--;
                if(i == j) return area;
            }
            int diffLevel = Math.min(height[i], height[j]) - levelReached;
            int blockSum = 0;
            for(int k=i+1; k<j; k++) {
                int current = 0;
                if(height[k]-levelReached > diffLevel) {
                    current = diffLevel;
                } else if(height[k]-levelReached > 0) {
                    current = height[k]-levelReached;
                }
                blockSum = blockSum + current;
            }
            area = area + diffLevel*(j-i-1) - blockSum;
            if(height[i]<height[j]) {
                i++;
            } else if(height[i]>height[j]) {
                j--;
            } else {
                i++;
                j--;
            }
            levelReached = levelReached + diffLevel;
        }
        return area;
    }
}