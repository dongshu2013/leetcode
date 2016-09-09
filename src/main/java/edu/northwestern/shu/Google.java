package edu.northwestern.shu;

import java.util.LinkedList;

public class Google {
	 public static void main(String[] args) {
		 System.out.println(solution("dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  file1.txt\ndir2\n file2.gif"));
	 }

	 public static int solution1(int[] A) {
		 long sum = 0;
		 for (int i = 0; i < A.length; i++) {
			 sum += A[i];
		 }
		 
		 long left = 0;
		 for (int i = 0; i < A.length; i++) {
			 long right = sum - left - A[i];
			 if (right == left) {
				 return i;
			 }
			 left += A[i];
		 }

		 return -1;
	 }
	 
	 public static int solution2(int X) {
		 String strX = String.valueOf(X);
		 int min = Integer.MAX_VALUE;
		 for (int i = 0; i < strX.length() - 1; i++) {
			 int indexToRemove = strX.charAt(i) > strX.charAt(i+1) ? i+1 : i;
			 String newStrX = strX.substring(0, indexToRemove) + strX.substring(indexToRemove+1);
			 int newX = Integer.valueOf(newStrX);
			 if (newX < min) {
				 min = newX;
			 }
		 }
		 return min;
	 }
	 
	 public static int solution(String S) {
		 LinkedList<String> files = new LinkedList<String>();
		 String[] lines = S.split("\n");
		 int length = 0;
		 for (int i = 0; i < lines.length; i++) {
			 String fname = lines[i].trim();
			 int depth = lines[i].indexOf(fname);
			 while (files.size() > depth) {
				 files.pop();
			 }
			 
			 if (depth >= files.size()) {
				 files.push(fname);
			 }

			 if (isImage(fname)){
				 for (String dir : files) {
					 length += (1 + dir.length());
				 }
			 }
		 }
		 return length % 1000000007;
	 }

	 private static boolean isImage(String filename) {
		 return filename.endsWith(".jpeg") ||
				 filename.endsWith(".png") ||
				 filename.endsWith(".gif");
	 }
}
