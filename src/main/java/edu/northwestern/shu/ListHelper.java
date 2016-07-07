package edu.northwestern.shu;

import java.util.ArrayList;
import java.util.List;

import edu.northwestern.shu.helper.ListNode;

public class ListHelper {
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode fast = head.next.next;
    ListNode slow = head.next;
    while (fast != slow && fast != null && fast.next != null && slow != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    return !(fast == null || fast.next == null);
  }

  public ListNode removeElements(ListNode head, int val) {
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
      if (cur.val == val) {
        cur = cur.next;
        if (pre == null) {
          head = cur;
        } else {
          pre.next = cur;
        }
      } else {
        cur = cur.next;
        if (pre == null) {
          pre = head;
        } else {
          pre = pre.next;
        }

      }
    }
    return head;
  }

  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return head;
    }

    int val = head.val;
    ListNode pre = head;
    ListNode cur = head.next;
    while (cur != null) {
      if (cur.val == val) {
        pre.next = cur.next;
        cur = cur.next;
      } else {
        val = cur.val;
        pre = cur;
        cur = cur.next;
      }
    }

    return head;
  }

  public ListNode reverseList(ListNode head) {
    ListNode temp = new ListNode(0);
    temp.next = head;

    while (head != null && head.next != null) {
      ListNode n = head.next;
      head.next = n.next;
      n.next = temp.next;
      temp.next = n;
    }

    return temp.next;
  }

  public List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> pre = new ArrayList<Integer>();

    for (int i = 0; i < numRows; i++) {
      List<Integer> cur = new ArrayList<Integer>();
      cur.add(1);
      for (int j = 0; j < pre.size(); j++) {
        if (j == pre.size() - 1) {
          cur.add(pre.get(j));
        } else {
          cur.add(pre.get(j) + pre.get(j + 1));
        }
      }
      result.add(cur);
      pre = cur;
    }
    return result;
  }

  public List<Integer> getPascalTriangleRow(int rowIndex) {
    List<Integer> cur = new ArrayList<Integer>();
    for (int i = 0; i <= rowIndex; i++) {
      int t = 0;
      for (int j = 0; j < cur.size(); j++) {
        int v = t + cur.get(j);
        t = cur.get(j);
        cur.set(j, v);
      }
      cur.add(1);
    }
    return cur;
  }

  public List<Integer> getPascalTriangleRow2(int rowIndex) {
    List<Integer> cur = new ArrayList<Integer>();
    cur.add(1);
    for (int i = 1; i <= rowIndex; i++) {
      cur.add((int) ((long) cur.get(i - 1) * (rowIndex - i + 1) / i));
    }
    return cur;
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(0);
    ListNode cur = head;
    while (l1 != null || l2 != null) {
        if (l1 == null) {
            cur.next = l2;
            l2 = null;
        } else if (l2 == null) {
            cur.next = l1;
            l1 = null;
        } else if (l1.val < l2.val) {
            cur.next = l1;
            l1 = l1.next;
        } else {
            cur.next = l2;
            l2 = l2.next;
        }
        cur = cur.next;
    }  
    return head.next;
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    ListNode a = headA;
    ListNode b = headB;
    int distance = length(headA) - length(headB);
    if (distance < 0) {
        a = headB;
        b = headA;
    }

    for (int i = 0; i < Math.abs(distance); i++) {
        a = a.next;
    }

    while (a != b) {
        a = a.next;
        b = b.next;
    }

    return a;
  }
  
  public ListNode removeNthFromEnd(ListNode head, int n) {
    int l = length(head);
    if (l < n) return head;
    if (n == l) return head.next;

    n = l - n;
    ListNode h = head;
    for (int i = 0; i < n - 1; i++) {
        h = h.next;
    }
    
    h.next = h.next.next;
    return head;
  }

  private int length(ListNode head) {
    ListNode h = head;
    int count = 0;
    while(h != null) {
        count++;
        h = h.next;
    }
    return count;
  }
}
