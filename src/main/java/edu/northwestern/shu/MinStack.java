package edu.northwestern.shu;

import java.util.Stack;

public class MinStack {
  Stack<Integer> stack = new Stack<>();
  Stack<Integer> minStack = new Stack<>();

  /** initialize your data structure here. */
  public MinStack() {
      minStack.push(Integer.MAX_VALUE);
  }
  
  public void push(int x) {
      stack.push(x);
      if (x <= getMin()) {
          minStack.push(x);
      }
  }

  public void pop() {
      if(minStack.peek().equals(stack.pop())) {
          minStack.pop();
      }
  }

  public int top() {
      return stack.peek();
  }

  public int getMin() {
      return minStack.peek();
  }
  
  public static void main(String[] args) {
    MinStack s = new MinStack();
    s.push(512);
    s.push(-1024);
    s.push(-1024);
    s.push(512);
    s.pop();
    s.pop();
    s.pop();
    System.out.println(s.getMin());
  }
  
}
