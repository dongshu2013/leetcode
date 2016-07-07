package edu.northwestern.shu;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
  Queue<Integer> q1 = new LinkedList<Integer>();
  Queue<Integer> q2 = new LinkedList<Integer>();
  
  // Push element x onto stack.
  public void push(int x) {
      q1.add(x);
  }

  // Removes the element on top of the stack.
  public void pop() {
      if (empty()) return;
      
      while (q1.size() > 1) {
          q2.add(q1.poll());
      }
      q1.poll();
      
      Queue<Integer> temp = q1;
      q1 = q2;
      q2 = temp;
  }

  // Get the top element.
  public int top() {
      if (empty()) return 0;

      while (q1.size() > 1) {
          q2.add(q1.poll());
      }

      int x = q1.peek();
      q2.add(q1.poll());

      Queue<Integer> temp = q1;
      q1 = q2;
      q2 = temp;
      
      return x;
  }

  // Return whether the stack is empty.
  public boolean empty() {
      return q1.peek() == null;
  }
  
  public static void main(String[] args) {
    StackUsingQueue s = new StackUsingQueue();
    s.push(1);
    System.out.println(s.top());
  }
}
