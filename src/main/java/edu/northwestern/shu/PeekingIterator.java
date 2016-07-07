package edu.northwestern.shu;

import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {

  private Integer cache = null;
  private Iterator<Integer> ite;

  public PeekingIterator(Iterator<Integer> iterator) {
      // initialize any member here.
      ite = iterator;
      if (ite.hasNext()) {
          cache = ite.next();
      }
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
      return cache;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
      Integer temp = cache;
      cache = ite.hasNext() ? ite.next() : null;
      return temp;
  }

  @Override
  public boolean hasNext() {
      return cache != null;
  }
}