/*
First attempt: horizontal-level simulation.
// Time: O(n * H), where H is the maximum height.

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

// Second attempt: jump between boundary levels.
// Time: O(n^2), Space: O(1).
// Note: This implementation modifies the input array.

class Solution {
    public int trap(int[] height) {
        int i = 0;
        int j = height.length-1;
        int area = 0;
        while(i<j) {
            while(height[i] == 0) {
                i++;
                if(i == j) return area;
            }
            while(height[j] == 0) {
                j--;
                if(i == j) return area;
            }
            int level = Math.min(height[i], height[j]);
            int blockSum = 0;
            for(int k=i+1; k<j; k++) {
                int current = 0;
                if(height[k] > level) {
                    current = level;
                } else if(height[k] > 0) {
                    current = height[k];
                }
                blockSum = blockSum + current;
            }
            area = area + level*(j-i-1) - blockSum;
            if(height[i]<height[j]) {
                i++;
            } else if(height[i]>height[j]) {
                j--;
            } else {
                i++;
                j--;
            }
            for(int n=i; n<=j; n++) {
                if(height[n]<level) {
                    height[n] = 0;
                } else {
                    height[n] = height[n] - level;
                }
            }
        }
        return area;
    }
}

/*
Third attempt: precompute maximum height to the right using a HashMap.
Time: O(n), Space: O(n).

Learning note:
A HashMap is not needed here because the keys are contiguous array indices
(0 to height.length - 1). An int[] would be a better fit: simpler, faster,
and less memory overhead. Keep the HashMap implementation below as my attempt.
*/

class Solution {
    public int trap(int[] height) {
        HashMap<Integer, Integer> right = new HashMap<>();
        int j = height.length-1;
        int rightMax = 0;
        while(j>=0) {
            right.put(j, rightMax);
            if(height[j]>rightMax) {
                rightMax = height[j]; 
            }
            j--;
        }

        int area = 0;
        int leftMax = 0;
        for(int k=0; k<height.length; k++) {
            int curr = Math.min(leftMax, right.get(k)) - height[k];
            area = area + (curr<0 ? 0:curr);
            if(height[k]>leftMax) {
                leftMax = height[k];
            }
        }
        
        return area;  
    }
}