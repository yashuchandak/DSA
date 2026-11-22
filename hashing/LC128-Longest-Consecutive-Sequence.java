// Learning notes:
// 1. Start counting only when n - 1 is absent from the set. This means n is
//    the beginning of a consecutive sequence. Other numbers in that sequence
//    are skipped, so we don't traverse the same sequence repeatedly.
//
// 2. Iterate over the HashSet, not the original nums array. The set removes
//    duplicates. Iterating over nums could start and count the same sequence
//    repeatedly when the input contains duplicate values, causing TLE.
//
// 3. Why does set.add(n) work when the set stores Integer?
//    Java autoboxes the primitive int n into an Integer object automatically.
//    This is equivalent in effect to set.add(Integer.valueOf(n)).
//    The reverse conversion, Integer to int, is called unboxing.
//
// Submitted solution:
HashSet<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }
        int max = 0;
        for(int n : set) {
            if(set.contains(n-1)) {
                continue;
            }
            int i = n;
            int len = 1;
            while(set.contains(i+1)) {
                len = len+1;
                i++;
            }
            if(len>max) {
                max = len;
            }
        }
        return max;
