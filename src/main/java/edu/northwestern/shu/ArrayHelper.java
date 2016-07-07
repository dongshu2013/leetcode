package edu.northwestern.shu;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import edu.northwestern.shu.helper.Pair;
import edu.northwestern.shu.helper.TreeNode;

public class ArrayHelper {
  public void moveZeroes(int[] nums) {
    int i = 0;
    while (i < nums.length && nums[i] != 0)
      i++;
    int j = i;

    while (true) {
      while (i < nums.length && nums[i] != 0)
        i++;
      while (j < nums.length && nums[j] == 0)
        j++;
      if (j < nums.length) {
        nums[i] = nums[j];
        nums[j] = 0;
      } else {
        break;
      }
    }
  }

  public int majorityElement(int[] nums) {
    int index = 0;
    int count = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[index] == nums[i]) {
        count++;
      } else {
        count--;
        if (count == 0) {
          index = i;
          count = 1;
        }
      }
    }
    return nums[index];
  }

  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> dict = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; i++) {
      if (dict.containsKey(nums[i]) && (i - dict.get(nums[i]) <= k)) {
        return true;
      }
      dict.put(nums[i], i);
    }
    return false;
  }

  public int maxProfit(int[] prices) {
    int buy = 0;
    int maxProfit = 0;
    for (int i = 1; i < prices.length; i++) {
      int newProfit = prices[i] - prices[buy];
      if (newProfit < 0) {
        buy = i;
      }
      if (maxProfit < newProfit) {
        maxProfit = newProfit;
      }
    }
    return maxProfit;
  }

  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int p1 = 0;
    int p2 = nums[0];
    int p = p2;
    for (int i = 1; i < nums.length; i++) {
      p = Math.max(p1 + nums[i], p2);
      p1 = p2;
      p2 = p;
    }
    return p;
  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1;
    int j = n - 1;
    for (int k = m + n - 1; k >= 0 && j >= 0; k--) {
      if (i < 0 || nums1[i] < nums2[j]) {
        nums1[k] = nums2[j];
        j--;
      } else {
        nums1[k] = nums1[i];
        i--;
      }
    }
  }

  public int[] plusOne(int[] digits) {
    int carry = 1;
    for (int i = digits.length - 1; i >= 0; i--) {
      int d = digits[i] + carry;
      digits[i] = d % 10;
      carry = d / 10;
    }

    if (carry == 0) {
      return digits;
    } else {
      int[] result = new int[digits.length + 1];
      result[0] = 1;
      for (int i = 1; i < result.length; i++) {
        result[i] = digits[i - 1];
      }
      return result;
    }
  }

  public boolean isValidSudoku(char[][] board) {
    Set<Character> s = new HashSet<Character>();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.')
          continue;
        if (s.contains(board[i][j])) {
          return false;
        } else {
          s.add(board[i][j]);
        }
      }
      s.clear();
    }

    for (int j = 0; j < 9; j++) {
      for (int i = 0; i < 9; i++) {
        if (board[i][j] == '.')
          continue;
        if (s.contains(board[i][j])) {
          return false;
        } else {
          s.add(board[i][j]);
        }
      }
      s.clear();
    }

    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j += 3) {
        for (int ii = i; ii < i + 3; ii++) {
          for (int jj = j; jj < j + 3; jj++) {
            if (board[ii][jj] == '.')
              continue;
            if (s.contains(board[ii][jj])) {
              return false;
            } else {
              s.add(board[ii][jj]);
            }
          }
        }
        s.clear();
      }
    }
    return true;
  }

  public boolean isValidSudoku2(char[][] board) {
    BitSet bs = new BitSet(243);
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char val = board[i][j];
        if (val != '.') {
          int h = i * 9 + val - '1';
          if (!checkBit(bs, h))
            return false;
          int v = j * 9 + val - '1' + 81;
          if (!checkBit(bs, v))
            return false;
          int s = ((i / 3) * 3 + (j / 3)) * 9 + val - '1' + 162;
          if (!checkBit(bs, s))
            return false;
        }
      }
    }
    return true;
  }

  public String longestCommonPrefix(String[] strs) {
    String prefix = "";
    if (strs.length == 0) {
      return prefix;
    }

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < strs.length; i++) {
      if (strs[i].length() < min) {
        min = strs[i].length();
      }
    }

    for (int i = 0; i < min; i++) {
      char c = strs[0].charAt(i);
      for (int j = 1; j < strs.length; j++) {
        if (strs[j].charAt(i) != c) {
          return prefix;
        }
      }
      prefix += c;
    }
    return prefix;
  }

  public void rotate(int[] nums, int k) {
    k %= nums.length;
    if (k <= 0 || k == nums.length) {
      return;
    }

    int[] cache = new int[k];
    for (int i = nums.length - 1; i >= 0; i--) {
      int j = i + k;
      if (j >= nums.length) {
        cache[j - nums.length] = nums[i];
      } else {
        nums[j] = nums[i];
      }
    }

    for (int i = 0; i < k; i++) {
      nums[i] = cache[i];
    }
  }

  public int[] singleNumber(int[] nums) {
    int[] result = new int[2];

    int n = 0;
    for (int i = 0; i < nums.length; i++) {
      n ^= nums[i];
    }

    int mask = n & ~(n - 1);
    for (int i = 0; i < nums.length; i++) {
      if ((nums[i] & mask) == 0) {
        result[0] ^= nums[i];
      } else {
        result[1] ^= nums[i];
      }
    }

    return result;
  }

  public int singleNumber3(int[] nums) {
    int ans = 0;
    for (int i = 0; i < 32; i++) {
      int sum = 0;
      int mask = (1 << i);
      for (int j = 0; j < nums.length; j++) {
        if ((mask & nums[j]) != 0) {
          sum++;
          sum %= 3;
        }
      }
      if (sum == 1) {
        ans |= mask;
      }
    }
    return ans;
  }

  private boolean checkBit(BitSet bs, int pos) {
    if (bs.get(pos)) {
      return false;
    } else {
      bs.set(pos);
      return true;
    }
  }

  public int[] productExceptSelf(int[] nums) {
    int[] output = new int[nums.length];
    int mul = 1;
    for (int i = 0; i < nums.length; i++) {
      mul *= nums[i];
      output[i] = mul;
    }

    mul = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      if (i == 0) {
        output[i] = mul;
      } else {
        output[i] = output[i - 1] * mul;
      }
      mul *= nums[i];
    }
    return output;
  }

  public int missingNumber(int[] nums) {
    int total = nums.length * (nums.length + 1) / 2;
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    return total - sum;
  }

  public int maxProduct(int[] nums) {
    int max = Integer.MIN_VALUE;

    int m1 = 1;
    int m2 = 1;
    for (int i = 0; i < nums.length; i++) {
      int mm1 = m1 * nums[i];
      int mm2 = m2 * nums[i];
      m1 = Math.max(Math.max(mm1, mm2), nums[i]);
      m2 = Math.min(Math.min(mm1, mm2), nums[i]);
      if (max < m1) {
        max = m1;
      }
    }
    return max;
  }

  public int maxProfitV2(int[] prices) {
    int profit = 0;
    for (int i = 1; i < prices.length; i++) {
      int delta = prices[i] - prices[i - 1];
      if (delta > 0) {
        profit += delta;
      }
    }
    return profit;
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    result.add(new ArrayList<Integer>());
    for (int i = 0; i < nums.length; i++) {
      int len = result.size();
      for (int j = 0; j < len; j++) {
        List<Integer> temp = new ArrayList<Integer>(result.get(j));
        temp.add(nums[i]);
        result.add(temp);
      }
    }
    return result;
  }

  public List<Integer> topKFrequent(int[] nums, int k) {
    List<Integer> result = new ArrayList<Integer>();

    Map<Integer, Integer> counters = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; i++) {
      if (counters.containsKey(nums[i])) {
        counters.put(nums[i], counters.get(nums[i]) + 1);
      } else {
        counters.put(nums[i], 1);
      }
    }

    PriorityQueue<Pair> p = new PriorityQueue<Pair>(1, new Comparator<Pair>() {
      public int compare(Pair a, Pair b) {
        return b.value - a.value;
      }
    });
    for (Integer key : counters.keySet()) {
      p.add(new Pair(key, counters.get(key)));
    }

    for (int i = 0; i < k; i++) {
      result.add(p.poll().key);
    }
    return result;
  }

  public int maxProduct(String[] words) {
    if (words.length <= 1) {
      return 0;
    }

    int[] product = new int[words.length];
    product[0] = 0;
    int[] hash = new int[words.length];
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words[i].length(); j++) {
        hash[i] |= (1 << (words[i].charAt(j) - 'a'));
      }

      if (i >= 1) {
        product[i] = product[i - 1];
        for (int j = 0; j < i; j++) {
          if ((hash[i] & hash[j]) == 0) {
            product[i] = Math.max(product[i],
                words[i].length() * words[j].length());
          }
        }
      }
    }

    return product[words.length - 1];
  }

  public int searchInsert(int[] nums, int target) {
    int i = 0;
    int j = nums.length - 1;
    int middle = 0;
    while (i < j) {
      middle = (i + j) / 2;
      if (nums[middle] == target) {
        return middle;
      } else if (nums[middle] < target) {
        i = middle + 1;
      } else if (nums[middle] > target) {
        j = middle - 1;
      }
    }

    if (nums[i] >= target)
      return i;
    else
      return i + 1;
  }

  public int numTrees(int n) {
    int[] sol = new int[n + 1];
    sol[0] = 1;
    for (int i = 1; i <= n; i++) {
      for (int pivot = 1; pivot <= i; pivot++) {
        sol[i] += (sol[pivot - 1] * sol[i - pivot]);
      }
    }
    return sol[n];
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    return build(nums, 0, nums.length - 1);
  }

  public int maxProfitWithCoolDown1(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }

    int[] buy = new int[prices.length];
    int[] sell = new int[prices.length];
    buy[0] = -prices[0];
    sell[0] = 0;
    int max = 0;
    for (int i = 1; i < prices.length; i++) {
      int delta = prices[i] - prices[i - 1];
      sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1] + delta);
      if (max < sell[i]) {
        max = sell[i];
      }
      if (i > 1) {
        buy[i] = Math.max(sell[i - 2] - prices[i], buy[i - 1] - delta);
      } else {
        buy[i] = Math.max(-prices[i], buy[i - 1] - delta);
      }
    }
    return max;
  }

  public int maxProfitWithCoolDown2(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }

    int[] hold = new int[prices.length];
    int[] nhold = new int[prices.length];
    hold[0] = -prices[0];
    hold[1] = Math.max(-prices[0], -prices[1]);
    nhold[0] = 0;
    nhold[1] = Math.max(prices[1] - prices[0], 0);

    for (int i = 2; i < prices.length; i++) {
      hold[i] = Math.max(hold[i - 1], nhold[i - 2] - prices[i]);
      nhold[i] = Math.max(nhold[i - 1], hold[i - 1] + prices[i]);
    }
    return nhold[prices.length - 1];
  }

  public TreeNode build(int[] nums, int left, int right) {
    if (left > right) {
      return null;
    }
    int root = (left + right) / 2;
    TreeNode r = new TreeNode(nums[root]);
    r.left = build(nums, left, root - 1);
    r.right = build(nums, root + 1, right);
    return r;
  }

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (k > 9) {
      return result;
    }
    dfs(k, n, 1, new ArrayList<Integer>(), result);
    return result;
  }

  public void dfs(int k, int n, int s, List<Integer> path,
      List<List<Integer>> result) {
    if (k == 0 && n == 0) {
      result.add(new ArrayList<Integer>(path));
      return;
    }

    for (int j = s; j <= 9; j++) {
      if (n < j * k || n > 9 * k) {
        break;
      }
      path.add(j);
      dfs(k - 1, n - j, j + 1, path, result);
      path.remove(path.size() - 1);
    }
  }

  public int maxSubArray(int[] nums) {
    int sum = 0;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (sum > 0) {
        sum += nums[i];
      } else {
        sum = nums[i];
      }

      if (sum > max) {
        max = sum;
      }
    }
    return max;
  }

  public int findMin(int[] nums) {
    if (nums[0] < nums[nums.length - 1]) {
      return nums[0];
    }
    int i = 0;
    int j = nums.length - 1;
    while (i < j - 1) {
      int m = (i + j) / 2;
      if (nums[m] > nums[i]) {
        i = m;
      } else if (nums[m] < nums[i]) {
        j = m;
      }
    }
    return nums[j];
  }

  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> l = new ArrayList<Integer>();
    int n = matrix.length;
    if (n == 0) {
      return l;
    }

    int m = matrix[0].length;
    for (int i = 0; i <= Math.min((m - 1) / 2, (n - 1) / 2); i++) {
      int up = i;
      int down = n - i - 1;
      int left = i;
      int right = m - i - 1;

      for (int j = left; j <= right && up <= down; j++) {
        l.add(matrix[up][j]);
      }
      up++;

      for (int j = up; j <= down && left <= right; j++) {
        l.add(matrix[j][right]);
      }
      right--;

      for (int j = right; j >= left && down >= up; j--) {
        l.add(matrix[down][j]);
      }
      down--;

      for (int j = down; j >= up && right >= left; j--) {
        l.add(matrix[j][left]);
      }
    }

    return l;
  }

  public void rotate(int[][] matrix) {
    int n = matrix.length;
    int temp = 0;
    for (int i = 0; i < n / 2; i++) {
      for (int j = i; j < n - i - 1; j++) {
        temp = matrix[i][j];
        matrix[i][j] = matrix[n - j - 1][i];
        matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
        matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
        matrix[j][n - i - 1] = temp;
      }
    }
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    int n = matrix.length;
    if (n == 0) {
      return false;
    }

    int m = matrix[0].length;
    return search(0, m - 1, 0, n - 1, matrix, target);
  }

  private boolean search(int left, int right, int up, int down, int[][] matrix,
      int target) {
    if (left > right || up > down) {
      return false;
    }

    int row = (up + down) / 2;
    int col = (left + right) / 2;
    if (matrix[row][col] == target) {
      return true;
    }

    if (matrix[row][col] < target) {
      return search(col + 1, right, row + 1, down, matrix, target)
          || search(left, col, row + 1, down, matrix, target)
          || search(col + 1, right, up, row, matrix, target);
    } else {
      return search(left, col - 1, up, row - 1, matrix, target)
          || search(left, col - 1, row, down, matrix, target)
          || search(col, right, up, row - 1, matrix, target);
    }
  }

  public boolean searchMatrix2(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
      return false;
    int i = 0;
    int j = matrix[0].length - 1;
    while (i < matrix.length && j >= 0) {
      if (matrix[i][j] < target) {
        i++;
      } else if (matrix[i][j] > target) {
        j--;
      } else {
        return true;
      }
    }
    return false;
  }

  public void sortColors(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    int i = 0;
    while (i <= right) {
      int temp = nums[i];
      if (nums[i] == 0) {
        nums[i++] = nums[left];
        nums[left++] = temp;
      } else if (nums[i] == 2) {
        nums[i] = nums[right];
        nums[right--] = temp;
      } else {
        i++;
      }
    }
  }

  public int maxArea(int[] height) {
    if (height.length < 2) {
      return 0;
    }

    int maxArea = 0;
    int area = 0;
    int lh = 0;
    int rh = height.length - 1;
    int temp = 0;
    while (lh < rh) {
      if (height[lh] > height[rh]) {
        area = (rh - lh) * height[rh];
        temp = height[rh];
        while (lh < rh && height[rh] <= temp)
          rh--;
      } else {
        area = (rh - lh) * height[lh];
        temp = height[lh];
        while (lh < rh && height[lh] <= temp)
          lh++;
      }
      if (area > maxArea) {
        maxArea = area;
      }
    }
    return maxArea;
  }

  public int nthSuperUglyNumber(int n, int[] primes) {
    int[] result = new int[n + 1];
    int k = primes.length;
    int[] index = new int[k];
    result[0] = 1;
    int count = 1;

    while (count < n) {
      int min = Integer.MAX_VALUE;
      int minIndex = -1;
      for (int i = 0; i < k; i++) {
        int temp = primes[i] * result[index[i]];
        if (temp < min) {
          min = temp;
          minIndex = i;
        }
      }

      if (min > result[count - 1]) {
        result[count] = min;
        count++;
      }
      index[minIndex]++;
    }

    return result[n - 1];
  }

  public boolean increasingTriplet(int[] nums) {
    int[] c = new int[2];
    c[0] = Integer.MAX_VALUE;
    c[1] = Integer.MAX_VALUE;

    for (int i = 0; i < nums.length; i++) {
      if (c[0] >= nums[i]) {
        c[0] = nums[i];
        continue;
      }
      if (c[1] >= nums[i]) {
        c[1] = nums[i];
        continue;
      }
      return true;
    }
    return false;
  }

  public void gameOfLife(int[][] board) {
    int n = board.length;
    if (n == 0) {
      return;
    }
    int m = board[0].length;

    int[] drow = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dcol = {-1, 0, 1, -1, 1, -1, 0, 1};
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
          int row = i + drow[k];
          int col = j + dcol[k];
          if (row >= 0 && row < n && col >= 0 && col < m
              && (board[row][col] == 1 || board[row][col] == 2)) {
            cnt++;
          }
        }

        if (board[i][j] == 0 && cnt == 3) {
          board[i][j] = 3;
        } else if (board[i][j] == 1 && (cnt < 2 || cnt > 3)) {
          board[i][j] = 2;
        }
      }
    }

    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        board[i][j] %= 2;
      }
    }
  }

  public boolean searchMatrixI(int[][] matrix, int target) {
    int n = matrix.length;
    if (n == 0) {
      return false;
    }
    int row = binarySearch(matrix, target, 0, 1);
    int col = binarySearch(matrix, target, row, 0);
    return matrix[row][col] == target;
  }

  public int binarySearch(int[][] matrix, int target, int index, int dim) {
    int i = 0;
    int j = (dim == 0) ? matrix[0].length - 1 : matrix.length - 1;
    while (i <= j) {
      int mid = (i + j) / 2;
      int val = (dim == 0) ? matrix[index][mid] : matrix[mid][index];
      if (val == target) {
        return mid;
      } else if (val < target) {
        i = mid + 1;
      } else {
        j = mid - 1;
      }
    }
    return Math.max(0, j);
  }

  public int findKthLargest2(int[] nums, int k) {
    PriorityQueue<Integer> p = new PriorityQueue<Integer>();
    for (int i = 0; i < nums.length; i++) {
      p.add(nums[i]);
      if (p.size() > k) {
        p.poll();
      }
    }
    return p.poll();
  }

  public int findKthLargest(int[] nums, int k) {
    if (nums.length == 0 || k <= 0)
      return -1;
    int n = nums.length;

    int i = 0;
    int j = n - 1;
    while (i <= j) {
      int wall = quickSort(i, j, nums);
      if (wall + 1 == k) {
        return nums[wall];
      } else if (wall + 1 < k) {
        i = wall + 1;
      } else {
        j = wall - 1;
      }
    }
    return nums[i];
  }

  public int quickSort(int start, int end, int[] nums) {
    int wall = start;
    for (int cur = start; cur < end; cur++) {
      if (nums[cur] > nums[end]) {
        swap(nums, cur, wall++);
      }
    }
    swap(nums, wall, end);
    return wall;
  }

  private void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public void setZeroes(int[][] matrix) {
    int n = matrix.length;
    if (n == 0) {
      return;
    }

    int m = matrix[0].length;
    boolean firstRow = false;
    boolean firstCol = false;
    for (int i = 0; i < n; i++) {
      if (matrix[i][0] == 0) {
        firstCol = true;
      }
    }

    for (int i = 0; i < m; i++) {
      if (matrix[0][i] == 0) {
        firstRow = true;
      }
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }

    if (firstCol == true) {
      for (int i = 0; i < n; i++) {
        matrix[i][0] = 0;
      }
    }

    if (firstRow == true) {
      for (int i = 0; i < m; i++) {
        matrix[0][i] = 0;
      }
    }
  }

  public int findPeakElement(int[] nums) {
    int i = 0;
    int j = nums.length - 1;
    while (i < j) {
        if (i == j - 1) {
            return nums[i] > nums[j] ? i : j;
        }

        int mid = (i + j) / 2;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid+1]) {
            return mid;
        } else if (nums[mid] < nums[mid - 1]) {
            j = mid - 1;
        } else if (nums[mid] < nums[mid + 1]) {
            i = mid + 1;
        }
    }
    return i;
  }
  
  public int removeDuplicates(int[] nums) {
    int cnt = 0;
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        if (j == 0 || nums[j] == nums[j - 1]) {
            cnt++;
        } else {
            cnt = 1;
        }

        if (cnt <= 2) {
            nums[i++] = nums[j];
        }
    }
    
    return i;
  }
  
  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 5, 6, 4};
    ArrayHelper ah = new ArrayHelper();
    System.out.println(ah.findKthLargest(nums, 2));
  }
}
