package edu.northwestern.shu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MinimumHeightTrees310 {    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 0) return new ArrayList<Integer>();

        Map<Integer, Integer> degree = new HashMap<Integer, Integer>();
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        List<Integer> roots = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            degree.put(i, 0);
            graph.put(i, new ArrayList<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dest = edges[i][1];
            degree.put(src, degree.get(src) + 1);
            degree.put(dest, degree.get(dest) + 1);
            graph.get(src).add(dest);
            graph.get(dest).add(src);
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (degree.get(i) == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            roots.clear();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int node = queue.poll();
                roots.add(node);
                for (int neighbor : graph.get(node)) {
                    degree.put(neighbor, degree.get(neighbor) - 1);
                    if (degree.get(neighbor) == 1) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return roots;
    }

    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if (n == 0) return new ArrayList<Integer>();
        return selectMinHeightRoots(runFloyd(n, edges));
    }

    private List<Integer> selectMinHeightRoots(int[][] distances) {
        List<Integer> roots = new ArrayList<Integer>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distances.length; i++) {
            int height = Arrays.stream(distances[i]).max().getAsInt();
            if (height < min) {
                min = height;
                roots.clear();
                roots.add(i);
            } else if (height == min) {
                roots.add(i);
            }
        }
        return roots;
    }

    private int[][] runFloyd(int n, int[][] edges) {
        int[][] distances = initializeDistanceMatrix(n, edges);
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        distances[j][i] = distances[i][j];
                    }
                }
            }
        }
        return distances;
    }
    
    private int[][] initializeDistanceMatrix(int n, int[][] edges) {
        int[][] distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = n;
            }
        }

        for (int i = 0; i< n; i++) {
            distances[i][i] = 0;
        }

        for (int i = 0; i < edges.length; i++) {
            distances[edges[i][0]][edges[i][1]] = 1;
            distances[edges[i][1]][edges[i][0]] = 1;
        }

        return distances;
    }

    public static void main(String[] args) {
    	int[][] edges = {};
    	MinimumHeightTrees310 m = new MinimumHeightTrees310();
    	System.out.println(m.findMinHeightTrees(1, edges));
    }
}