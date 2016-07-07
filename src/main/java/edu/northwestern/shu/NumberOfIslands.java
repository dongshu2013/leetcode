package edu.northwestern.shu;

import java.util.LinkedList;
import java.util.List;

public class NumberOfIslands {
  public static class Coord {
    public int i;
    public int j;
    public Coord(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }

  public void traversal(List<Coord> queue, char[][] grid, boolean[][] visited) {
    int row = grid.length;
    int col = grid[0].length;
    while (queue.size() > 0) {
      Coord c = queue.remove(0);
      if (c.i < 0 || c.j < 0 || c.i >= row || c.j <= col ||
          visited[c.i][c.j] || grid[c.i][c.j] == '0') {
        continue;
      }
      visited[c.i][c.j] = true;
      queue.add(new Coord(c.i - 1, c.j));
      queue.add(new Coord(c.i + 1, c.j));
      queue.add(new Coord(c.i, c.j - 1));
      queue.add(new Coord(c.i, c.j + 1));
    }
  }

  public int numIslands(char[][] grid) {
    if (grid.length == 0) {
      return 0;
    }

    int row = grid.length;
    int col = grid[0].length;
    List<Coord> queue = new LinkedList<Coord>();
    boolean[][] visited = new boolean[row][col];

    int count = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == '1' && !visited[i][j]) {
          count += 1;
          queue.add(new Coord(i, j));
          traversal(queue, grid, visited);
        }
      }
    }
    return count;
  }
  
  public static void main(String[] args) {
    
  }
}
