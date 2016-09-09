package edu.northwestern.shu;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring76 {
	public static void main(String[] args) {
		MinimumWindowSubstring76 m = new MinimumWindowSubstring76();
		String s = "a";
		String t = "a";
		System.out.println(m.minWindow(s, t));
	}

	public String minWindow(String s, String t) {
        Map<Character, Integer> tCount = count(t);
        Map<Character, Integer> sCount = new HashMap<Character, Integer>();
        int start = 0;
        int end = start;
        int minStart = 0;
        int minEnd = s.length();
        int count = 0;
        while (end < s.length() || count == t.length()) {
            if (count < t.length()) {
                if (tCount.containsKey(s.charAt(end))) {
                    increaseOne(sCount, s.charAt(end));
                    if (sCount.get(s.charAt(end)) <= tCount.get(s.charAt(end))) {
                        count++;
                    }
                }
                end++;
            } else {    	
                if (minEnd - minStart > end - start) {
                    minStart = start;
                    minEnd = end;
                }

                if (sCount.containsKey(s.charAt(start))) {
                    decreaseOne(sCount, s.charAt(start));
                    if (sCount.get(s.charAt(start)) < tCount.get(s.charAt(start))) {
                        count--;
                    }
                }
                start++;
            }
        }

        if (start == 0 && count < t.length()) {
            return "";
        }

        return s.substring(minStart, minEnd);
    }

    private Map<Character, Integer> count(String t) {
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            increaseOne(m, t.charAt(i));
        }
        return m;
    }

    private void increaseOne(Map<Character, Integer> m, char key) {
        if (m.containsKey(key)) {
            m.put(key, m.get(key) + 1);
        } else {
            m.put(key, 1);
        }
    }

    private void decreaseOne(Map<Character, Integer> m, char key) {
    	m.put(key, m.get(key) - 1);
    }
}
