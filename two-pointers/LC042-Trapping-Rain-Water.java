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