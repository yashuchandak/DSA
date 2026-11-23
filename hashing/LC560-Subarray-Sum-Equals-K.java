HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        for(int n: nums) {
            sum = sum + n;
            if(sum == k) {
                count++;
            }
            count = count + map.getOrDefault(sum-k, 0);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return count;