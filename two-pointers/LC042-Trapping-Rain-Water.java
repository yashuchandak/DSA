class Solution {

    // Approach 1: Horizontal-level simulation.
    // Time: O(n * H), where H is the maximum height.
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

    // Approach 2: Jump between boundary levels.
    // Time: O(n^2), Space: O(1).
    // Note: This implementation modifies the input array.
    public int trap1(int[] height) {
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

    // Approach 3: Precompute maximum height to the right using a HashMap.
    // Time: O(n), Space: O(n).
    // Learning note: HashMap is unnecessary for contiguous array indices.
    // An int[] would be simpler, faster, and have less memory overhead.
    public int trap2(int[] height) {
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

    // Approach 4: Optimal two-pointer solution.
    // Time: O(n), Space: O(1).
    // Process the shorter side: the opposite boundary is at least as tall,
    // so water on this side is determined by its own running maximum.
    public int trap3(int[] height) {
        int area = 0;
        int leftMax = 0;
        int rightMax = 0;
        int i = 0; 
        int j = height.length-1;
        while(i<=j) {
            if(height[i]<height[j]) {
                if(height[i]>leftMax) leftMax = height[i];        
                area = area + leftMax - height[i];
                i++;
            } else {
                if(height[j]>rightMax) rightMax = height[j];
                area = area + rightMax - height[j];
                j--;
            }
        }
        return area;
    }
}
