package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.iterators.ForwardIterator;

public interface OrderedSet<Data extends Comparable<? super Data>> extends Set<Data> {
  
  //Min
  default Data Min() {
    if (IsEmpty()) {
      throw new java.util.NoSuchElementException("The Set is empty!");
    }
    ForwardIterator<Data> it = FIterator();
    Data min = it.GetCurrent();
    it.Next();
    while (it.IsValid()) {
      Data current = it.GetCurrent();
      if (current != null && (min == null || current.compareTo(min) < 0)) {
        min = current;
      }
      it.Next();
    }
    return min;
  }
  
  default void RemoveMin() {
    MinNRemove();
  }
  
  default Data MinNRemove() {
    Data min = Min();
    Remove(min);
    return min;
  }
  
  //Max
  default Data Max() {
    if (IsEmpty()) {
      throw new java.util.NoSuchElementException("The Set is empty!");
    }
    ForwardIterator<Data> it = FIterator();
    Data max = it.GetCurrent();
    it.Next();
    while (it.IsValid()) {
      Data current = it.GetCurrent();
      if (current != null && (max == null || current.compareTo(max) > 0)) {
        max = current;
      }
      it.Next();
    }
    return max;
  }
  
  default void RemoveMax() {
    MaxNRemove();
  }
  
  default Data MaxNRemove() {
    Data max = Max();
    Remove(max);
    return max;
  }
  
  //Predecessor & Successor
  default Data Predecessor(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    Data pred = null;
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      Data current = it.GetCurrent();
      if (current != null && current.compareTo(dat) < 0) {
        if (pred == null || current.compareTo(pred) > 0) {
          pred = current;
        }
      }
      it.Next();
    }
    return pred;
  }
  
  default void RemovePredecessor(Data dat) {
    PredecessorNRemove(dat);
  }
  
  default Data PredecessorNRemove(Data dat) {
    Data pred = Predecessor(dat);
    if (pred != null) {
      Remove(pred);
    }
    return pred;
  }
  
  default Data Successor(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    Data succ = null;
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      Data current = it.GetCurrent();
      if (current != null && current.compareTo(dat) > 0) {
        if (succ == null || current.compareTo(succ) < 0) {
          succ = current;
        }
      }
      it.Next();
    }
    return succ;
  }
  
  default void RemoveSuccessor(Data dat) {
    SuccessorNRemove(dat);
  }
  
  default Data SuccessorNRemove(Data dat) {
    Data succ = Successor(dat);
    if (succ != null) {
      Remove(succ);
    }
    return succ;
  }
}