package edu.northwestern.shu;

import java.util.HashMap;
import java.util.Map;

public class GoogleInterview {
	public static void main(String[] args) {
		System.out.println(new GoogleInterview().solution("eceba", 2));
	}
	
	public int solution(String s, int M) {
		Map<Character, Integer> count = new HashMap<Character, Integer>();
		int start = 0, end = 0, maxLength = 0;
		while (end < s.length()) {
			char c = s.charAt(end);
			if (count.containsKey(c) || count.size() < M) {
				if (count.containsKey(c)) {
					count.put(c, count.get(c) + 1);
				} else{
					count.put(c, 1);
				}
				end++;
			} else {
				if (maxLength < end - start) maxLength = end - start;
				c = s.charAt(start);
				count.put(c, count.get(c) - 1);
				if (count.get(c) == 0) count.remove(c);
				start++;
			}
		}
		return maxLength;
	}
}