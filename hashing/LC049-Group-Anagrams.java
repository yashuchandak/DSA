// Learning notes:
// 1. Starting point: Use a HashMap where the key represents an anagram group
//    and the value is the list of original strings belonging to that group.
//    Sorting each word gives all its anagrams the same key.
//
// 2. Initial attempt used HashMap<char[], List<String>>. That did not work
//    because Java arrays use reference-based equals() and hashCode(), so two
//    different char[] arrays with the same characters are treated as different keys.
//
//    Why: String overrides Object's equals() and hashCode() to compare contents;
//    arrays inherit Object's implementations, which compare object identity.
//
// 3. Fix: Convert the sorted char[] into a String. String keys use
//    content-based equals() and hashCode(), so matching anagrams share a key.
//
// Submitted solution:
HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] temp = s.toCharArray();
            Arrays.sort(temp);
            String tempstr = new String(temp);
            if(map.containsKey(tempstr)) {
                map.get(tempstr).add(s);
            } else {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(s);
                map.put(tempstr, tempList);
            }
        }
        return map.values().stream().toList();

// Possible improvement (not applied to your solution):
// computeIfAbsent() can create the list only when the key is missing, then
// return the list so the string can be added in one expression:
// map.computeIfAbsent(tempstr, key -> new ArrayList<>()).add(s);
