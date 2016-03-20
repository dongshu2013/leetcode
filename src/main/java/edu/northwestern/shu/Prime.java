package edu.northwestern.shu;

public class Prime {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	public int countPrime(int N) {
		boolean[] visited = new boolean[N + 1];
		int count = 0;
		for (int i = 2; i <= N; i++) {
		  if (!visited[i]) {
		  	count++;
		  	for (int j = 2; j <= N / i; j++) {
		  		visited[i * j] = true;
		  	}
		  }
		}
		return count;
	}

	public int countMutablePrime(int N) {
		boolean[] visited = new boolean[N + 1];
		int count = 0;
		for (int i = 2; i <= N; i++) {
		  if (!visited[i]) {
		  	if (N % i == 0) {
		  		for (int j = 2; j <= (N / i); j++) {
			  		visited[i * j] = true;
			  	}
				} else {
					count++;
			  	continue;
				}
		  }
		}
		return count;
	}
}
